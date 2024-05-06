package br.com.estacionamento.service;

import br.com.estacionamento.builder.EstabelecimentoRequestDTOBuilder;
import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.model.Estabelecimento;
import br.com.estacionamento.repository.EstabelecimentoRepository;
import br.com.estacionamento.service.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstabelecimentoServiceTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private EstabelecimentoService estabelecimentoService;

    @Test
    void whenEstabelecimentoInformedThenItShouldBeCreated() throws RegraNegocioException {

        ModelMapper mapper = new ModelMapper();

        EstabelecimentoRequestDTO expectedEstabelecimentoDTO = EstabelecimentoRequestDTOBuilder.builder().build().toEstabelecimentoRequestDTO();
        Estabelecimento expectedSavedEstabelecimento = mapper.map(expectedEstabelecimentoDTO, Estabelecimento.class);
        assertNotNull(expectedSavedEstabelecimento);
        lenient().when(estabelecimentoRepository.findByName(expectedEstabelecimentoDTO.getNome())).thenReturn(Optional.empty());
        when(estabelecimentoRepository.save(expectedSavedEstabelecimento)).thenReturn(expectedSavedEstabelecimento);

        EstabelecimentoResponseDTO createdEstabelecimentoDTO = estabelecimentoService.cadastrar(expectedEstabelecimentoDTO);
        assertNotNull(createdEstabelecimentoDTO);

        assertThat(createdEstabelecimentoDTO.getNome(), is(equalTo(expectedEstabelecimentoDTO.getNome())));
        assertThat(createdEstabelecimentoDTO.getCnpj(), is(equalTo(expectedEstabelecimentoDTO.getCnpj())));
        assertThat(createdEstabelecimentoDTO.getEndereco(), is(equalTo(expectedEstabelecimentoDTO.getEndereco())));
        assertThat(createdEstabelecimentoDTO.getTelefone(), is(equalTo(expectedEstabelecimentoDTO.getTelefone())));
        assertThat(createdEstabelecimentoDTO.getQuantidadeVagasCarros(), is(equalTo(expectedEstabelecimentoDTO.getQuantidadeVagasCarros())));
        assertThat(createdEstabelecimentoDTO.getQuantidadeVagasMotos(), is(equalTo(expectedEstabelecimentoDTO.getQuantidadeVagasMotos())));


    }

}

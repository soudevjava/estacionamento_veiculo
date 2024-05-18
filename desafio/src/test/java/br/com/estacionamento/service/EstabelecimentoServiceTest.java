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

    @InjectMocks
    private ModelMapper mapper;

    @Test
    void whenEstabelecimentoInformedThenItShouldBeCreated() throws RegraNegocioException {

        EstabelecimentoRequestDTO expectedEstabelecimentoDTO = EstabelecimentoRequestDTOBuilder.builder().build().toEstabelecimentoRequestDTO();
        Estabelecimento expectedSavedEstabelecimento = mapper.map(expectedEstabelecimentoDTO, Estabelecimento.class);
        assertNotNull(expectedSavedEstabelecimento);
        lenient().when(estabelecimentoRepository.findByNome(expectedEstabelecimentoDTO.getNome())).thenReturn(Optional.empty());
        when(estabelecimentoRepository.save(expectedSavedEstabelecimento)).thenReturn(expectedSavedEstabelecimento);

        EstabelecimentoResponseDTO createdEstabelecimentoDTO = estabelecimentoService.cadastrar(expectedEstabelecimentoDTO);
        assertNotNull(createdEstabelecimentoDTO);

        assertThat(createdEstabelecimentoDTO.getNome(), is(equalTo(expectedEstabelecimentoDTO.getNome())));
        assertThat(createdEstabelecimentoDTO.getCnpj(), is(equalTo(expectedEstabelecimentoDTO.getCnpj())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getCep(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getCep())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getBairro(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getBairro())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getLogradouro(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getLogradouro())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getNumero(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getNumero())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getCidade(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getCidade())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getEstado(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getEstado())));
        assertThat(createdEstabelecimentoDTO.getTelefone(), is(equalTo(expectedEstabelecimentoDTO.getTelefone())));
        assertThat(createdEstabelecimentoDTO.getQuantidadeVagasCarros(), is(equalTo(expectedEstabelecimentoDTO.getQuantidadeVagasCarros())));
        assertThat(createdEstabelecimentoDTO.getQuantidadeVagasMotos(), is(equalTo(expectedEstabelecimentoDTO.getQuantidadeVagasMotos())));


    }

}

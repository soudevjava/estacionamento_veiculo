package br.com.estacionamento.service;

import br.com.estacionamento.builder.VeiculoRequestDTOBuilder;
import br.com.estacionamento.dtos.request.VeiculoRequestDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.repository.VeiculoRepository;
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
public class VeiculoServiceOldTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    @InjectMocks
    private ModelMapper mapper;





    @Test
    void whenVeiculoInformedThenItShouldBeCreated() throws RegraNegocioException {

        VeiculoRequestDTO expectedVeiculoDTO = VeiculoRequestDTOBuilder.builder().build().toVeiculoRequestDTO();
        Veiculo expectedSavedVeiculo = mapper.map(expectedVeiculoDTO,Veiculo.class);
        assertNotNull(expectedSavedVeiculo);

        lenient().when(veiculoRepository.buscarVeiculoPorPlaca(expectedVeiculoDTO.getPlaca())).thenReturn(Optional.empty());
        when(veiculoRepository.save(expectedSavedVeiculo)).thenReturn(expectedSavedVeiculo);
        VeiculoResponseDTO createdVeiculoDTO = veiculoService.cadastrar(expectedVeiculoDTO);
        assertNotNull(createdVeiculoDTO);

        assertThat(createdVeiculoDTO.getMarca(), is(equalTo(expectedVeiculoDTO.getMarca())));
        assertThat(createdVeiculoDTO.getModelo(),is(equalTo(expectedVeiculoDTO.getModelo())));
        assertThat(createdVeiculoDTO.getCor(),is(equalTo(expectedVeiculoDTO.getCor())));
        assertThat(createdVeiculoDTO.getPlaca(),is(equalTo(expectedVeiculoDTO.getPlaca())));
        assertThat(createdVeiculoDTO.getTipo(),is(equalTo(expectedVeiculoDTO.getTipo())));
        assertThat(createdVeiculoDTO.getIdEstabelecimento(),is(equalTo(expectedVeiculoDTO.getIdEstabelecimento())));




    }



}

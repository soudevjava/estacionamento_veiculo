package br.com.estacionamento.service;

import br.com.estacionamento.builder.RegistroEstacionamentoRequestDTOBuilder;
import br.com.estacionamento.dtos.request.RegistroEstacionamentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.dtos.response.RegistroEstacionamentoResponseDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import br.com.estacionamento.model.RegistroEstacionamento;
import br.com.estacionamento.model.enums.TipoRegistro;
import br.com.estacionamento.repository.RegistroEstacionamentoRepository;
import br.com.estacionamento.service.exception.RegraNegocioException;
import org.apache.coyote.Response;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.temporal.ChronoUnit;

import static org.apache.commons.lang3.Validate.isAssignableFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RegistroEstacionamentoServiceTest {

    @Mock
    private RegistroEstacionamentoRepository registroEstacionamentoRepository;

    @InjectMocks
    private RegistroEstacionamentoService registroEstacionamentoService;

    @Mock
    private EstabelecimentoService estabelecimentoService;

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private ModelMapper mapper;


    @Test
    void whenRegistroEstacionamentoInformedThenItShouldBeCreated() throws RegraNegocioException{

        RegistroEstacionamentoRequestDTO expecteEstacionamentoDTO = RegistroEstacionamentoRequestDTOBuilder.builder().build().toRegistroEstacionamentoRequestDTO();

        TipoRegistro tipoRegistro = TipoRegistro.ENTRADA;
        RegistroEstacionamento expectedSavedEstacionamento = mapper.map(expecteEstacionamentoDTO,RegistroEstacionamento.class);
        assertNotNull(expectedSavedEstacionamento);
        lenient().when(registroEstacionamentoRepository.save(any())).thenReturn(expectedSavedEstacionamento);
        EstabelecimentoResponseDTO estabelecimentoResponseDTO = new EstabelecimentoResponseDTO();
        estabelecimentoResponseDTO.setId(expecteEstacionamentoDTO.getEstabelecimentoId());
        when(estabelecimentoService.listarPorId(anyLong())).thenReturn(estabelecimentoResponseDTO);

        VeiculoResponseDTO veiculoResponseDTO = new VeiculoResponseDTO();
        veiculoResponseDTO.setId(expecteEstacionamentoDTO.getIdVeiculo());
        when(veiculoService.listarPorId(anyLong())).thenReturn(veiculoResponseDTO);

        RegistroEstacionamentoResponseDTO createdEstacionamentoDTO = registroEstacionamentoService.registrar(expecteEstacionamentoDTO,tipoRegistro);
        RegistroEstacionamentoResponseDTO registrarEntradaEstacionamentoDTO = registroEstacionamentoService.registrarEntrada(expecteEstacionamentoDTO);
        RegistroEstacionamentoResponseDTO registrarSaidaEstacionamentoDTO = registroEstacionamentoService.registrarSaida(expecteEstacionamentoDTO);

        assertNotNull(createdEstacionamentoDTO);
        assertNotNull(registrarEntradaEstacionamentoDTO);
        assertNotNull(registrarSaidaEstacionamentoDTO);

        assertThat(RegistroEstacionamento.class, is(expectedSavedEstacionamento.getClass()));
        assertThat(createdEstacionamentoDTO.getEstabelecimento().getId(), is(equalTo(expecteEstacionamentoDTO.getEstabelecimentoId())));
        assertThat(createdEstacionamentoDTO.getVeiculo().getId(), is(equalTo(expecteEstacionamentoDTO.getIdVeiculo())));


    }


}

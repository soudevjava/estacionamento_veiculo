package br.com.estacionamento.controller;

import br.com.estacionamento.builder.EstabelecimentoRequestDTOBuilder;
import br.com.estacionamento.builder.EstabelecimentoResponseDTOBuilder;
import br.com.estacionamento.builder.RegistroEstacionamentoRequestDTOBuilder;
import br.com.estacionamento.builder.RegistroEstacionamentoResponseDTOBuilder;
import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.request.RegistroEstacionamentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.dtos.response.RegistroEstacionamentoResponseDTO;
import br.com.estacionamento.service.EstabelecimentoService;
import br.com.estacionamento.service.RegistroEstacionamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static br.com.estacionamento.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RegistroEstacionamentoControllerTest {

    private static final String REGISTRO_ESTACIONAMENTO_ENTRADA_API_URL_PATH = "http://localhost:8080/registro/estacionamento/entrada";
    private static final String REGISTRO_ESTACIONAMENTO_SAIDA_API_URL_PATH = "http://localhost:8080/registro/estacionamento/saida";

    private MockMvc mockMvc;

    @Mock
    private RegistroEstacionamentoService registroEstacionamentoService;

    @InjectMocks
    private RegistroEstacionamentoController registroEstacionamentoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(registroEstacionamentoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAEstabelecimentoIsCreated() throws Exception {
        RegistroEstacionamentoRequestDTO requestDTO = RegistroEstacionamentoRequestDTOBuilder.builder().build().toRegistroEstacionamentoRequestDTO();
        RegistroEstacionamentoResponseDTO responseDTO = RegistroEstacionamentoResponseDTOBuilder.builder().build().toRegistroEstacionamentoResponseDTO();

        lenient().when(registroEstacionamentoService.registrarEntrada(requestDTO)).thenReturn(responseDTO);
        lenient().when(registroEstacionamentoService.registrarSaida(requestDTO)).thenReturn(responseDTO);


        mockMvc.perform(post(REGISTRO_ESTACIONAMENTO_ENTRADA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(responseDTO.getId().intValue())))
                .andExpect(jsonPath("$.estabelecimento.id", is(responseDTO.getEstabelecimento().getId().intValue())))
                .andExpect(jsonPath("$.estabelecimento.nome", is(responseDTO.getEstabelecimento().getNome())))
                .andExpect(jsonPath("$.estabelecimento.cnpj", is(responseDTO.getEstabelecimento().getCnpj())))
                .andExpect(jsonPath("$.veiculo.id", is(responseDTO.getVeiculo().getId().intValue())))
                .andExpect(jsonPath("$.veiculo.marca", is(responseDTO.getVeiculo().getMarca())))
                .andExpect(jsonPath("$.veiculo.modelo", is(responseDTO.getVeiculo().getModelo())))
                .andExpect(jsonPath("$.data", is(responseDTO.getData().toString())))
                .andExpect(jsonPath("$.tipoRegistro", is(responseDTO.getTipoRegistro())));

        mockMvc.perform(post(REGISTRO_ESTACIONAMENTO_SAIDA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(responseDTO.getId().intValue())))
                .andExpect(jsonPath("$.estabelecimento.id", is(responseDTO.getEstabelecimento().getId().intValue())))
                .andExpect(jsonPath("$.estabelecimento.nome", is(responseDTO.getEstabelecimento().getNome())))
                .andExpect(jsonPath("$.estabelecimento.cnpj", is(responseDTO.getEstabelecimento().getCnpj())))
                .andExpect(jsonPath("$.veiculo.id", is(responseDTO.getVeiculo().getId().intValue())))
                .andExpect(jsonPath("$.veiculo.marca", is(responseDTO.getVeiculo().getMarca())))
                .andExpect(jsonPath("$.veiculo.modelo", is(responseDTO.getVeiculo().getModelo())))
                .andExpect(jsonPath("$.data", is(responseDTO.getData().toString())))
                .andExpect(jsonPath("$.tipoRegistro", is(responseDTO.getTipoRegistro())));
    }

    }




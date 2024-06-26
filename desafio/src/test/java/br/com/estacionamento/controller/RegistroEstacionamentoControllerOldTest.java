package br.com.estacionamento.controller;

import br.com.estacionamento.builder.RegistroEstacionamentoRequestDTOBuilder;
import br.com.estacionamento.builder.RegistroEstacionamentoResponseDTOBuilder;
import br.com.estacionamento.dtos.request.RegistroEstacionamentoRequestDTO;
import br.com.estacionamento.dtos.response.RegistroEstacionamentoResponseDTO;
import br.com.estacionamento.service.RegistroEstacionamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.estacionamento.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RegistroEstacionamentoControllerOldTest {

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
        
        ResponseEntity<RegistroEstacionamentoResponseDTO> responseEntrada = registroEstacionamentoController.registrarEntrada(requestDTO);
        ResponseEntity<RegistroEstacionamentoResponseDTO> responseSaida = registroEstacionamentoController.registrarSaida(requestDTO);

        assertNotNull(responseEntrada);
        assertNotNull(responseSaida);

        assertEquals(responseDTO, responseEntrada.getBody());
        assertNotNull(responseEntrada.getBody());
        assertEquals(HttpStatus.CREATED,responseEntrada.getStatusCode());

        assertEquals(responseDTO, responseSaida.getBody());
        assertEquals(HttpStatus.CREATED,responseSaida.getStatusCode());
        assertNotNull(responseSaida.getBody());

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




package br.com.estacionamento.controller;

import br.com.estacionamento.builder.VeiculoRequestDTOBuilder;
import br.com.estacionamento.builder.VeiculoResponseDTOBuilder;
import br.com.estacionamento.dtos.request.VeiculoRequestDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import br.com.estacionamento.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
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

import static br.com.estacionamento.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

    private static final String VEICULO_API_URL_PATH = "http://localhost:8080/veiculo";

    private MockMvc mockMvc;

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private VeiculoController veiculoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAVeiculoIsCreated() throws Exception {

        VeiculoRequestDTO requestDTO = VeiculoRequestDTOBuilder.builder().build().toVeiculoRequestDTO();

        VeiculoResponseDTO responseDTO = VeiculoResponseDTOBuilder.builder().build().toVeiculoResponseDTO();
        assertNotNull(responseDTO);
        when(veiculoService.cadastrar(requestDTO)).thenReturn(responseDTO);

        assertEquals(requestDTO.getMarca(), responseDTO.getMarca());
        assertEquals(requestDTO.getModelo(), responseDTO.getModelo());
        assertEquals(requestDTO.getCor(), responseDTO.getCor());
        assertEquals(requestDTO.getPlaca(), responseDTO.getPlaca());
        assertEquals(requestDTO.getTipo(), responseDTO.getTipo());


        mockMvc.perform(post(VEICULO_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(responseDTO.getId().intValue())))
                .andExpect(jsonPath("$.marca", is(responseDTO.getMarca())))
                .andExpect(jsonPath("$.modelo", is(responseDTO.getModelo())))
                .andExpect(jsonPath("$.cor", is(responseDTO.getCor())))
                .andExpect(jsonPath("$.placa", is(responseDTO.getPlaca())))
                .andExpect(jsonPath("$.tipo", is(responseDTO.getTipo())));

    }
}
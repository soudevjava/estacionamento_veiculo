package br.com.estacionamento.controller;

import br.com.estacionamento.builder.EstabelecimentoRequestDTOBuilder;
import br.com.estacionamento.builder.EstabelecimentoResponseDTOBuilder;
import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.service.EstabelecimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.estacionamento.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EstabelecimentoControllerTest {

    private static final String ESTABELECIMENTO_API_URL_PATH = "http://localhost:8080/estabelecimento";

    private MockMvc mockMvc;

    @Mock
    private EstabelecimentoService estabelecimentoService;

    @InjectMocks
    private EstabelecimentoController estabelecimentoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estabelecimentoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAEstabelecimentoIsCreated() throws Exception {

        EstabelecimentoRequestDTO requestDTO = EstabelecimentoRequestDTOBuilder.builder().build().toEstabelecimentoRequestDTO();

        EstabelecimentoResponseDTO responseDTO = EstabelecimentoResponseDTOBuilder.builder().build().toEstabelecimentoResponseDTO();


        when(estabelecimentoService.cadastrar(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(post(ESTABELECIMENTO_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(responseDTO.getId().intValue())))
                .andExpect(jsonPath("$.nome", is(responseDTO.getNome())))
                .andExpect(jsonPath("$.cnpj", is(responseDTO.getCnpj())))
                .andExpect(jsonPath("$.endereco.cep", is(responseDTO.getEndereco().getCep())))
                .andExpect(jsonPath("$.endereco.logradouro", is(responseDTO.getEndereco().getLogradouro())))
                .andExpect(jsonPath("$.endereco.bairro", is(responseDTO.getEndereco().getBairro())))
                .andExpect(jsonPath("$.endereco.numero", is(responseDTO.getEndereco().getNumero())))
                .andExpect(jsonPath("$.endereco.complemento", is(responseDTO.getEndereco().getComplemento())))
                .andExpect(jsonPath("$.endereco.cidade", is(responseDTO.getEndereco().getCidade())))
                .andExpect(jsonPath("$.endereco.estado", is(responseDTO.getEndereco().getEstado())))
                .andExpect(jsonPath("$.telefone", is(responseDTO.getTelefone())))
                .andExpect(jsonPath("$.quantidadeVagasMotos", is(responseDTO.getQuantidadeVagasMotos())))
                .andExpect(jsonPath("$.quantidadeVagasCarros", is(responseDTO.getQuantidadeVagasCarros())));

    }
}
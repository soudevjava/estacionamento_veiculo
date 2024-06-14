package br.com.estacionamento.controller;

import br.com.estacionamento.builder.EstabelecimentoRequestDTOBuilder;
import br.com.estacionamento.builder.EstabelecimentoResponseDTOBuilder;
import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.service.EstabelecimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static br.com.estacionamento.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EstabelecimentoControllerOldTest {

    private static final String ESTABELECIMENTO_API_URL_PATH = "http://localhost:8080/estabelecimento";

    private static final String ESTABELECIMENTO_API_URL_PATH_ID = "http://localhost:8080/estabelecimento/{id}";


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



    @Test
    void WhenFindAllThenReturnAnListOfUsers() throws Exception {

        Pageable pageable = PageRequest.of(0,10);
        assertNotNull(pageable);

        EstabelecimentoResponseDTO responseDTO = EstabelecimentoResponseDTOBuilder.builder().build().toEstabelecimentoResponseDTO();
        assertNotNull(responseDTO);
        assertEquals(EstabelecimentoResponseDTO.class,responseDTO.getClass());

        Page<EstabelecimentoResponseDTO> responsePage = new PageImpl<>(Collections.singletonList(responseDTO),pageable,1);
        assertNotNull(responsePage);

        lenient().when(estabelecimentoService.listar(pageable)).thenReturn(responsePage);

        ResponseEntity<Page<EstabelecimentoResponseDTO>> response = estabelecimentoController.listar(pageable);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PageImpl.class, response.getBody().getClass());
        assertEquals(10,response.getBody().getPageable().getPageSize());
        assertEquals(0,response.getBody().getPageable().getPageNumber());
        assertEquals(responsePage, response.getBody());
        assertThrows(RuntimeException.class,()->{
            throw new RuntimeException("Erro ao listar o estabelecimento");
        });

        mockMvc.perform(get(ESTABELECIMENTO_API_URL_PATH)
                        .param("page","0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageable.pageNumber",is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                .andExpect(jsonPath("$.pageable.sort.empty",is(true)))
                .andExpect(jsonPath("$.pageable.sort.sorted",is(false)))
                .andExpect(jsonPath("pageable.sort.unsorted",is(true)))
                .andExpect(jsonPath("pageable.offset",is(0)))
                .andExpect(jsonPath("pageable.unpaged",is(false)))
                .andExpect(jsonPath("pageable.paged",is(true)))
                .andExpect(jsonPath("$.last",is(true)))
                .andExpect(jsonPath("$.totalPages",is(1)))
                .andExpect(jsonPath("$.totalElements",is(1)))
                .andExpect(jsonPath("$.size",is(10)))
                .andExpect(jsonPath("$.number",is(0)))
                .andExpect(jsonPath("$.sort.empty",is(true)))
                .andExpect(jsonPath("$.sort.sorted",is(false)))
                .andExpect(jsonPath("$.sort.unsorted",is(true)))
                .andExpect(jsonPath("$.first",is(true)))
                .andExpect(jsonPath("$.numberOfElements",is(1)))
                .andExpect(jsonPath("$.empty",is(false)));
    }

    @Test
    void WhenUpdaThenReturnId() throws Exception {
        EstabelecimentoResponseDTO responsDTO = EstabelecimentoResponseDTOBuilder.builder().build().toEstabelecimentoResponseDTO();
        Long id = 1L;

        when(estabelecimentoService.listarPorId(id)).thenReturn(responsDTO);

        ResponseEntity<EstabelecimentoResponseDTO> response = estabelecimentoController.detalhar(id);

        assertAll(
                ()->assertNotNull(response),
                ()->assertEquals(EstabelecimentoResponseDTO.class,responsDTO.getClass()),
                ()->assertEquals(ResponseEntity.class,response.getClass()),
                ()->assertEquals(HttpStatus.OK,response.getStatusCode())
        );

        mockMvc.perform(get(ESTABELECIMENTO_API_URL_PATH_ID,responsDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
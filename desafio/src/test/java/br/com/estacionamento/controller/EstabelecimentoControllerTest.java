package br.com.estacionamento.controller;


import br.com.estacionamento.model.Estabelecimento;
import br.com.estacionamento.service.EstabelecimentoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class EstabelecimentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EstabelecimentoService estabelecimentoService;

    @Mock
    private Estabelecimento estabelecimento;

    @Test
    void deveriaRetornar201AoCadastrarUmEstabelecimentoSemErros() throws Exception {

        // ARRANGE
        String json = """
                                
                {
                   "nome": "Exemplo Empório",
                   "cnpj": "45.740.817/1872-35",
                   "endereco": {
                     "cep": "12345-678",
                     "logradouro": "Rua das Flores",
                     "bairro": "Centro",
                     "numero": "123",
                     "complemento": "Sala 101",
                     "cidade": "Cidade Exemplo",
                     "estado": "EX"
                   },
                   "telefone": "(89) 7504-2176",
                   "quantidadeVagasMotos": 10,
                   "quantidadeVagasCarros": 20
                 }
                 
                                
                """;
        var response = mvc.perform(
                post("/estabelecimento")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    void deveriaRetornar400AoCadastrarUmEstabelecimentoComErros() throws Exception {

        // ARRANGE
        String json = "{}";

        var response = mvc.perform(
                post("/estabelecimento")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }


    @Test
    void deveriaRetornar404AoCadastrarUmEstabelecimentoComErros() throws Exception {

        // ARRANGE
        String json = """
                
                {
                   "nome": "Exemplo Empório",
                   "cnpj": "45.740.817/1872-35",
                   "endereco": {
                     "cep": "12345-678",
                     "logradouro": "Rua das Flores",
                     "bairro": "Centro",
                     "numero": "123",
                     "complemento": "Sala 101",
                     "cidade": "Cidade Exemplo",
                     "estado": "EX"
                   },
                   "telefone": "(89) 7504-2176",
                   "quantidadeVagasMotos": 10,
                   "quantidadeVagasCarros": 20
                 }
                 
                
                """;

        var response = mvc.perform(
                get("/estabelecimento/1/")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(404, response.getStatus());

        }
    }
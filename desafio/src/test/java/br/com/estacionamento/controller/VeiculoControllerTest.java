package br.com.estacionamento.controller;

import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.service.VeiculoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private VeiculoService veiculoService;

    @Mock
    private Veiculo veiculo;


    @Test
    void deveriaRetornar201AoCadastrarUmVeiculoSemErros() throws Exception {

        //ARRANGE

        String json = """
                {
                  "marca": "Toyota",
                  "modelo": "Corolla",
                  "cor": "Prata",
                  "placa": "YYT-8885",
                  "tipo": "MOTO",
                  "idEstabelecimento": 1
                }
                
                """;
        var response = mvc.perform(
                post("/veiculo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());
    }



    @Test
    void deveriaRetornar404AoCadastrarUmVeiculoComErros() throws Exception {

        //ARRANGE
        //

        String json = """
                {
                  "marca": "Toyota",
                  "modelo": "Corolla",
                  "cor": "Prata",
                  "placa": "YYT-8885",
                  "tipo": "MOTO",
                  "idEstabelecimento": 1
                }
                
                """;
        var response = mvc.perform(
                post("/veiculo/1/")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(404, response.getStatus());
    }





    @Test
    void deveriaRetornar400AoCadastrarUmVeiculoComErros() throws Exception {

        // ARRANGE
        String json = "{}";

        var response = mvc.perform(
                post("/veiculo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

}

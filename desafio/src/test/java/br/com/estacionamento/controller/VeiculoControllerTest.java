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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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



    @Test
    void  deveriaAtualizarUmVeiculoSemErros() throws Exception{

        Long id =1L;

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
                put("/veiculo/" + id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());

    }


    @Test
    void  deveriaNaoAtualizarUmVeiculoSemErros() throws Exception{

        Long id =1L;

        String json = """
                {
                  "marca": "Toyota",
                  "modelo": "Corolla",
                  "cor": "Prata",
                  "placa": "YYT8885",
                  "tipo": "MOTO",
                  "idEstabelecimento": 1
                }
                
                """;
        var response = mvc.perform(
                put("/veiculo/" + id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());

    }







    @Test
    void deveriaExcluirUmVeiculoSemErros() throws Exception {

        Long id = 1L;

        var response = mvc.perform(
                delete("/veiculo/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(204, response.getStatus());

    }


    @Test
    void deveriaConsultarUmaPlacaSemErros() throws Exception {

        // ARRANGE

        String cep  = "YYT-8885";

        var response = mvc.perform(
                get("/veiculo/find/{placa}", cep)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());

    }


    @Test
    void deveriaListarVeiculosSemErros() throws Exception {

        String json = """
                {
                  "page": 0,
                  "size": 1,
                  "sort": [
                    "string"
                  ]
                }
                """;

        var response = mvc.perform(
                get("/veiculo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());

    }

}

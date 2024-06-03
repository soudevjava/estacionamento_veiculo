package br.com.estacionamento.service.exception.exceptionHandlers;

import br.com.estacionamento.service.exception.EstabelecimentoNotFoundException;
import br.com.estacionamento.service.exception.RegraNegocioException;
import br.com.estacionamento.service.exception.StandardError;
import br.com.estacionamento.service.exception.VeiculoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    public static final String VEICULO_NAO_ENCONTRADO = "Veiculo Nao encontrado";
    public static final String ESTABLECIMENTO_NAO_ENCONTRADO="Estabelecimento não encontrado";
    public static final String JA_EXISTE_UM_VEICULO_CADASTRADO_COM_A_PLACA_S = "Já existe um Veiculo cadastrado com a placa %s";


    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void veiculoNotFoundException() {
        ResponseEntity<StandardError> response = exceptionHandler
                .veiculoNotFoundException(new VeiculoNotFoundException(VEICULO_NAO_ENCONTRADO),new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(VEICULO_NAO_ENCONTRADO,response.getBody().getError());
        assertEquals(404,response.getBody().getStatus());
        assertNotEquals("/veiculo/2",response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(),response.getBody().getTimestamp());

    }

    @Test
    void estabelecimentoNotFoundException() {
        ResponseEntity<StandardError> response = exceptionHandler
                .estabelecimentoNotFoundException(new EstabelecimentoNotFoundException(ESTABLECIMENTO_NAO_ENCONTRADO),new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(ESTABLECIMENTO_NAO_ENCONTRADO,response.getBody().getError());
        assertEquals(404,response.getBody().getStatus());
        assertNotEquals("/estabelecimento/2",response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(),response.getBody().getTimestamp());

    }

    @Test
    void regraNegocioException() {

        ResponseEntity<StandardError> response = exceptionHandler
                .regraNegocioException(new RegraNegocioException(JA_EXISTE_UM_VEICULO_CADASTRADO_COM_A_PLACA_S),new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(JA_EXISTE_UM_VEICULO_CADASTRADO_COM_A_PLACA_S,response.getBody().getError());
        assertEquals(400,response.getBody().getStatus());
        assertNotEquals("/veiculo/2",response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(),response.getBody().getTimestamp());
        
    }
}
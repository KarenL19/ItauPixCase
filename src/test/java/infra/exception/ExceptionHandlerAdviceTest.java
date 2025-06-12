package infra.exception;

import com.store.itaupixcase.infra.exception.advice.ExceptionHandlerAdvice;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExceptionHandlerAdviceTest {

    private final ExceptionHandlerAdvice advice = new ExceptionHandlerAdvice();

    @Test
    void testHandleValidationExceptions() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(List.of(new ObjectError("field", "Mensagem de erro")));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Map<String, Object>> response = advice.handleValidationExceptions(ex);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(422, body.get("statusCode"));
        assertEquals(List.of("Mensagem de erro"), body.get("message"));
        assertEquals("ERROR_VALIDATION", body.get("errorCode"));
        assertNotNull(body.get("timestamp"));
        assertNotNull(body.get("uuid"));
    }

    @Test
    void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Argumento inválido");

        ResponseEntity<Map<String, Object>> response = advice.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(422, body.get("statusCode"));
        assertEquals("Argumento inválido", body.get("message"));
        assertEquals("ILLEGAL_ARGUMENT", body.get("errorCode"));
        assertNotNull(body.get("timestamp"));
        assertNotNull(body.get("uuid"));
    }

    @Test
    void testHandleNoSuchElementException() {
        NoSuchElementException ex = new NoSuchElementException("Não encontrado");

        ResponseEntity<Map<String, Object>> response = advice.handleNoSuchElementException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(404, body.get("statusCode"));
        assertEquals("Não encontrado", body.get("message"));
        assertEquals("NOT_FOUND", body.get("errorCode"));
        assertNotNull(body.get("timestamp"));
        assertNotNull(body.get("uuid"));
    }
}
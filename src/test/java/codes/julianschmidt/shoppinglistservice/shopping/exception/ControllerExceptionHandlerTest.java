package codes.julianschmidt.shoppinglistservice.shopping.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

class ControllerExceptionHandlerTest {

    ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void globalExceptionHandler() {
        Exception dummyException = new RuntimeException("a problem occurred");
        WebRequest dummyRequest = Mockito.mock(WebRequest.class);
        Mockito.when(dummyRequest.getDescription(false)).thenReturn("my description");

        ResponseEntity<ErrorMessage> result = handler.globalExceptionHandler(dummyException, dummyRequest);

        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR)));
        assertThat(result.getBody(), notNullValue());
        assertThat(result.getBody().getStatusCode(), is(equalTo(500)));
        assertThat(result.getBody().getMessage(), Matchers.containsString("a problem occurred"));
        assertThat(result.getBody().getDescription(), Matchers.containsString("my description"));
    }

}

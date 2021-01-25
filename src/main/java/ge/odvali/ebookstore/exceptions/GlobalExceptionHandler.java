package ge.odvali.ebookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
		@ExceptionHandler
		public final ResponseEntity handleGeneralException(UnauthorizedException ex, WebRequest request){
				return new ResponseEntity<String>("please provide a bearer token!",HttpStatus.UNAUTHORIZED);
		}


}

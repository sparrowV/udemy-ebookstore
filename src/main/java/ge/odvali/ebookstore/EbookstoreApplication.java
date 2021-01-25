package ge.odvali.ebookstore;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.security.krb5.internal.APOptions;

@SpringBootApplication
@ApiResponses(value = {
	@ApiResponse(responseCode = "401", description = "please provide a bearer token!")})
public class EbookstoreApplication {

		public static void main(String[] args) {
				SpringApplication.run(EbookstoreApplication.class, args);
		}
}

package ge.odvali.ebookstore;

import ge.odvali.ebookstore.soap.SampleSoapImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.security.krb5.internal.APOptions;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class EbookstoreApplication {
		@Autowired
		private Bus bus;

		@Bean
		public Endpoint endpoint(){
			Endpoint endpoint = new EndpointImpl(bus,new SampleSoapImpl());
			endpoint.publish("/sample-soap-service");
			return endpoint;
		}

		public static void main(String[] args) {
				SpringApplication.run(EbookstoreApplication.class, args);
		}


}

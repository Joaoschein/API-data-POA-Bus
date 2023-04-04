package Panvel.com.br.DataPOABus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Data POA Bus",
		version = "1.0",
		description = "Endpoints para consulta de linhas e itinerarios dos onibus de Porto Alegre"
))
public class DataPoaBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataPoaBusApplication.class, args);
	}

}

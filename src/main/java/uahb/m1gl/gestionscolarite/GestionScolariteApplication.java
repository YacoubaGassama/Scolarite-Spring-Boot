package uahb.m1gl.gestionscolarite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
public class GestionScolariteApplication {

	public static void main(String[] args) {

		SpringApplication.run(GestionScolariteApplication.class, args);
	}

}

package co.software.colombia.pruebatecnica.bean;


import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;

@Configuration
public class ConfigurationBean {
    //Aplicando el patrón de inversión de control, este bean representa el archivo usado como repositorio
    @SneakyThrows
    @Bean
    public File fileRepository(){
        return new File("movies.csv");
    }
}

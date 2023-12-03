package br.com.app.conatus;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ConatusCoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConatusCoreServiceApplication.class, args);
	}
	
    @PostConstruct
    public void init(){
      TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
    }

}

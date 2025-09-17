package com.sebastianmolina.hangmangame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HangmanGameApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HangmanGameApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Api funcionando bien creo ");
    }
}

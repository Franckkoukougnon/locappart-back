package com.cda.locappartback;

import com.cda.locappartback.entity.Categorie;
import com.cda.locappartback.repository.AppartementRepo;
import com.cda.locappartback.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocappartBackApplication {


	public static void main(String[] args) {
		SpringApplication.run(LocappartBackApplication.class, args);
	}


}

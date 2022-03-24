package br.com.kimae.testjparelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestjparelationApplication implements CommandLineRunner {

	@Autowired
	RootRepository rootRepository;


	public static void main(String[] args) {
		SpringApplication.run(TestjparelationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

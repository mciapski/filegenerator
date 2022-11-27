package com.edrone.filegenerator;


import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@Import(MainConfiguration.class)
public class FilegeneratorApplication {

	public static void main(String[] args) {

		SpringApplication.run(FilegeneratorApplication.class, args);


	}

}

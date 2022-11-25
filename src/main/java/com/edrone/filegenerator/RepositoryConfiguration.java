package com.edrone.filegenerator;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.edrone.filegenerator")
public class RepositoryConfiguration {
}

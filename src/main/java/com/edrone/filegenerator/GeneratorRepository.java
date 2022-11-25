package com.edrone.filegenerator;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


public interface GeneratorRepository extends JpaRepository<GeneratedString, BigInteger> {
}

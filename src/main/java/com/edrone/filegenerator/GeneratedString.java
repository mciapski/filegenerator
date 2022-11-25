package com.edrone.filegenerator;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;
import java.math.BigInteger;


@Data
@AllArgsConstructor
@Entity
@Table(name="generatedstrings")
public class GeneratedString {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public BigInteger id;
    @Column(name="generated_string")
    public String generatedString;

}

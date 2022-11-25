package com.edrone.filegenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.math.BigInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="generatedstrings")
public class GeneratedString implements Comparable<GeneratedString>{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;
    @Column(name="generated_string")
    public String generatedString;


    @Override
    public int compareTo(GeneratedString o) {
        return this.getId() - o.id;
    }
}

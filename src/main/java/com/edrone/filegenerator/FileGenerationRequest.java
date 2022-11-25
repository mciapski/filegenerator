package com.edrone.filegenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public record FileGenerationRequest(String alphabet, int wordCount, int minLength, int maxLength) {

}

package com.edrone.filegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class GeneratorController {

    //   @Autowired
//   GeneratorRepository generatorRepository;
    @Autowired
    FileGenerationService fileGenerationService;

    @PostMapping("/api/generate_file")
    public List<GeneratedString> startGenerationJob(@RequestBody FileGenerationRequest request) throws NotEnoughCharsException {
        boolean test = fileGenerationService.checkIfCharactersQuantityEnough(request.alphabet(), request.wordCount(), request.minLength(), request.maxLength());

        if (!test) {

            throw new NotEnoughCharsException("Za mała ilość znaków");
        }
        return List.of(new GeneratedString(BigInteger.ONE, "Dramat"));
    }
}

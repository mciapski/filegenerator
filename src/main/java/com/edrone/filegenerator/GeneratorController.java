package com.edrone.filegenerator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {

    @PostMapping("/api/generate_file")
    public ResponseEntity startGenerationJob(@RequestParam String wordInput, @RequestParam Integer requestedQuantityOfWords) throws Exception {
        int possibleQuantityOfWords=1;
        for (int i = 1; i<wordInput.length(); i++){
            possibleQuantityOfWords*=i;
        }
        if(requestedQuantityOfWords>possibleQuantityOfWords){
            throw new NotEnoughCharsException("Za mała ilość znaków");
        }

        return ResponseEntity.ok().build();
    }


}

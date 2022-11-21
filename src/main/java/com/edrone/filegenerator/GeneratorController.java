package com.edrone.filegenerator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {

    @PostMapping("/api/generate_file")
    public ResponseEntity startGenerationJob(){
        return ResponseEntity.badRequest().build();
    }


}

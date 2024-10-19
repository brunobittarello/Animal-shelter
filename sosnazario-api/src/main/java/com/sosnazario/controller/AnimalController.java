package com.sosnazario.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sosnazario.dto.AnimalSearchDto;
import com.sosnazario.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnimalController {

    Logger logger = LoggerFactory.getLogger(AnimalController.class);

    @Autowired
    private AnimalService animalService;

    @GetMapping("/animal/all")
    Iterable<Animal> all() {
        return animalService.findAll(false);
    }

    @GetMapping("/animal/{id}")
    Animal animalById(@PathVariable Long id) {
        return animalService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // https://www.baeldung.com/spring-request-param
    @GetMapping("/animal/query")
    Iterable<Animal> animalQuery(@RequestParam Map<String,String> allParams) {
        logger.info("Parameters are " + allParams.entrySet());

        AnimalSearchDto animal = new AnimalSearchDto();
        if (allParams.containsKey("age")) {
            animal.setAgeOfBirth(Integer.valueOf(allParams.get("age")));
        }
        if (allParams.containsKey("type")) {
            animal.setType(allParams.get("type").charAt(0));
        }
        if (allParams.containsKey("gender")) {
            animal.setGender(allParams.get("gender").charAt(0));
        }
        if (allParams.containsKey("size")) {
            animal.setSize(allParams.get("size").charAt(0));
        }
        
        return animalService.findByExample(animal);
    }

    @PostMapping("/animal")
    Animal save(@RequestBody Animal animal) {
        logger.info("An INFO Message");
        return animalService.create(animal);
    }

    @PostMapping("/animal/media/{id}")
    Animal save2(@PathVariable Long id, @RequestBody AnimalMedia media) {
        Animal animal = animalService.addMedia(id, media);
        if (animal == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return animal;
    }

}
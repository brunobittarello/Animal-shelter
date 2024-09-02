package com.sosnazario.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sosnazario.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/animal")
    Animal save(@RequestBody Animal animal) {
        logger.info("An INFO Message");
        return animalService.create(animal);
    }

    @PostMapping("/animal/media/{id}")
    Animal save2(@PathVariable Long id) {
        AnimalMedia animalMedia = new AnimalMedia();
        animalMedia.setFile("foto.png");
        Animal animal = animalService.addMedia(id, animalMedia);
        if (animal == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return animal;
    }

}
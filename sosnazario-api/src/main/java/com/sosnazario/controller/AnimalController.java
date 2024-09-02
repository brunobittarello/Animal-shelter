package com.sosnazario.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Set;
import com.sosnazario.model.*;
import com.sosnazario.repository.AnimalRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;

@RestController
@RequestMapping("/api")
public class AnimalController {

    Logger logger = LoggerFactory.getLogger(AnimalController.class);
    private AnimalRespository animalRespository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public AnimalController(AnimalRespository animalRespository) {
        this.animalRespository = animalRespository;
    }

    @GetMapping("/user/all")
    Iterable<AnimalModel> all() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedAnimalFilter");
        filter.setParameter("isDeleted", false);
        Iterable<AnimalModel> animals =  animalRespository.findAll();
        session.disableFilter("deletedAnimalFilter");
        return animals;
    }

    @GetMapping("/user/{id}")
    AnimalModel animalById(@PathVariable Long id) {
        return animalRespository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user/save")
    AnimalModel save(@RequestBody AnimalModel animal) {
        logger.info("An INFO Message");
        return animalRespository.save(animal);
    }

    @PostMapping("/user/save2/{id}")
    AnimalModel save2(@PathVariable Long id) {
        AnimalModel animal = animalRespository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AnimalMediaModel animalMedia = new AnimalMediaModel();
        animalMedia.setFile("foto.png");

        // Set<AnimalMediaModel> animalMediaSet = new HashSet();
        // animalMediaSet.add(animalMedia);
        // animal.setMedialist(animalMediaSet);

        animal.getMedialist().add(animalMedia);
        animal.setSize('m');
        animal.setLastModifiedDate();
        logger.info("An INFO Message " + animal.getName() + " " + animalMedia.getFile());
        return animalRespository.save(animal);
    }

}
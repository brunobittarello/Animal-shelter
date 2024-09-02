package com.sosnazario.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sosnazario.model.*;
import com.sosnazario.repository.AnimalRespository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;

@Service
public class AnimalService {

    Logger logger = LoggerFactory.getLogger(AnimalService.class);

    @Autowired
    private AnimalRespository animalRespository;

    @Autowired
    private EntityManager entityManager;

    public Optional<AnimalModel> findById(Long id) {
        return animalRespository.findById(id);
    }

    public AnimalModel create(AnimalModel animal) {
        return animalRespository.save(animal);
    }

    public void remove(Long id){
        animalRespository.deleteById(id);
    }

    public AnimalModel addMedia(Long id, AnimalMediaModel media) {
        Optional<AnimalModel> optAnimal = animalRespository.findById(id);
        if (optAnimal.isPresent() == false)
            return null;

        AnimalModel animal = optAnimal.get();
        animal.getMedialist().add(media);
        animal.setSize('m');
        animal.setLastModifiedDate();
        logger.info("An INFO Message " + animal.getName() + " " + media.getFile());
        return animalRespository.save(animal);
    }

    public Iterable<AnimalModel> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedAnimalFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<AnimalModel> animals =  animalRespository.findAll();
        session.disableFilter("deletedAnimalFilter");
        return animals;
    }
}
package com.animalshelter.repository;

import com.animalshelter.model.Animal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRespository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a WHERE "
        + "(:age is null or a.yearOfBirth = :age) and "
        + "(:type is null or a.type = :type) and "
        + "(:gender is null or a.gender = :gender) and "
        + "(:size is null or a.size = :size)")
    Iterable<Animal> findByAgeAndTypeAndGenderAndSize(
        @Param("age") Integer age, @Param("type") Character type, @Param("gender") Character gender, @Param("size") Character size);
}
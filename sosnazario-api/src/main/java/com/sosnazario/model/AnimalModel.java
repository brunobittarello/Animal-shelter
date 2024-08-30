package com.sosnazario.model;

import java.time.Instant;
import java.util.Set;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class AnimalModel {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int ageOfBirth;
    private char type;
    private char gender;
    private char size;
    private boolean isHidden;
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @OneToMany( targetEntity=AnimalMediaModel.class,cascade=CascadeType.ALL )
    private Set medialist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeOfBirth() {
        return ageOfBirth;
    }

    public void setAgeOfBirth(int ageOfBirth) {
        this.ageOfBirth = ageOfBirth;
    }
    
    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
    
    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    
    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }
    
    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Set getMedialist() {
        return medialist;
    }

    public void setMedialist(Set medialist) {
        this.medialist = medialist;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate() {
        this.lastModifiedDate = Instant.now();
    }
}
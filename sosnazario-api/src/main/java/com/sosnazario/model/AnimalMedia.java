package com.sosnazario.model;

//https://github.com/search?q=repo%3Aeugenp%2Ftutorials+%40CreatedDate&type=code

import jakarta.persistence.*;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AnimalMedia {

    @Id
    @GeneratedValue
    private Long id;
    private String file;
    private boolean isHidden;
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
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
package com.example.firstapi.models;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass //--> This annotation is used to tell spring that this is not a table but used only as a parent class.
public class BaseModel {
    @Id
    private Long id ;
    private Date createAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;
}

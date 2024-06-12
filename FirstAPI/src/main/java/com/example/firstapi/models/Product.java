package com.example.firstapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price ;
    @ManyToOne
    private Category category ;
    private String description;
    private String imageURL;

    //*****************************************************************************************
    //                              @ManyToOne Cardinality :
    //                              --------------------------
    //    1     : 1         --> one Product can have only one category(See Left to right)
    // Product : Category
    //   m    : 1        --> One Category can have many products (Now ,See right to left )
    //----------------
    //   m   : 1      --> Total is m (many) to 1 (one)
    //----------------
    //*******************************************************************************************

}

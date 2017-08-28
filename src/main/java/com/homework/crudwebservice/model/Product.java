package com.homework.crudwebservice.model;

import javax.persistence.*;

@Entity
public class Product
{
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String name;
    @Column
    private String description;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

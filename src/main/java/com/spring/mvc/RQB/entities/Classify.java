package com.spring.mvc.RQB.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Classify implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private Boolean tx; // transaction
    
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="classify", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("classify")
    private Set<Item> items;

    public Classify() {
    }

    public Classify(String name, Boolean tx) {
        this.name = name;
        this.tx = tx;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Boolean getTx() {
        return tx;
    }

    public void setTx(Boolean tx) {
        this.tx = tx;
    }

    

    @Override
    public String toString() {
        return "Classify{" + "id=" + id + ", name=" + name + ", tx=" + tx + '}';
    }
    
    
}

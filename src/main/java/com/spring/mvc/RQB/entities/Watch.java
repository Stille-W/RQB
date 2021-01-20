package com.spring.mvc.RQB.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Watch implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private String name;
    
    @ManyToOne()
    @JoinColumn(name = "requestor_id", referencedColumnName = "id")
    @JsonIgnoreProperties("watchs")
    private Requestor requestor;
    
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "watch_item", 
            joinColumns = {
                @JoinColumn(name = "watch_id", 
                            nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                @JoinColumn(name = "item_id", 
                            nullable = false, updatable = false)
            }
    )
    private Set<Item> items = new LinkedHashSet<>();

    public Watch() {
    }

    public Watch(String name, Requestor requestor) {
        this.name = name;
        this.requestor = requestor;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
    
    public Set<Item> addItem(Item item) {
        items.add(item);
        return items;
    }
    
    public Set<Item> removeItem(Item item) {
        items.remove(item);
        return items;
    }

    public Requestor getRequestor() {
        return requestor;
    }

    public void setRequestor(Requestor requestor) {
        this.requestor = requestor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Watch{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
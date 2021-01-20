package com.spring.mvc.RQB.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RQB implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private Integer amount;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @ManyToOne()
    @JoinColumn(name = "requestor_id", referencedColumnName = "id")
    @JsonIgnoreProperties("rqbs")
    private Requestor requestor;
    
    @OneToOne()
    @JoinColumn(name = "item_id", 
                foreignKey = @ForeignKey(name="item_fk", 
                                         value = ConstraintMode.CONSTRAINT))
    private Item item;
    
    public RQB() {
    }

    public RQB(Integer amount, Requestor requestor, Item item) {
        this.amount = amount;
        this.requestor = requestor;
        this.item = item;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Requestor getRequestor() {
        return requestor;
    }

    public void setRequestor(Requestor requestor) {
        this.requestor = requestor;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}

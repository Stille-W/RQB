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

@Entity
public class Requestor implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
    private String email;
    
    @Column
    private Integer balance;
    
    @Column
    private String code; //email驗證碼
    
    @Column
    private Boolean pass; //email驗證碼對>>pass = true   
    
    
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="requestor", fetch = FetchType.EAGER)
    //@JsonIgnoreProperties("requestor")
    private Set<RQB> rqbs;
    
    //@JsonIgnoreProperties("requestor")>>在watch裡不要顯示requestor
    //這樣資料才不會重複
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="requestor", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("requestor")
    private Set<Watch> watchs;
    
    public Requestor() {
    }

    public Requestor(String username, String password, String email, Integer balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
        pass = true;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RQB> getRqbs() {
        return rqbs;
    }

    public void setRqbs(Set<RQB> rqbs) {
        this.rqbs = rqbs;
    }
    

    public Set<Watch> getWatchs() {
        return watchs;
    }

    public void setWatchs(Set<Watch> watchs) {
        this.watchs = watchs;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Investor{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", balance=" + balance + ", code=" + code + ", pass=" + pass + '}';
    }
    
}

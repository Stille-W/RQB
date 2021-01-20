package com.spring.mvc.RQB.controller;


import com.spring.mvc.RQB.entities.Requestor;
import com.spring.mvc.RQB.entities.Item;
import com.spring.mvc.RQB.entities.RQB;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.mvc.RQB.repository.RequestorRepository;
import com.spring.mvc.RQB.repository.ItemRepository;
import com.spring.mvc.RQB.repository.RQBRepository;

@RestController
@RequestMapping("/RQB/order")
public class OrderController {
    
    @Autowired
    private RequestorRepository requestorRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private RQBRepository rqbRepository;
    
    // 購買
    @GetMapping(value = {"/buy/{item_id}/{amount}"})
    @Transactional
    public String buy(HttpSession session, @PathVariable("item_id") Integer item_id, @PathVariable("amount") Integer amount) {
        Requestor login_requestor = (Requestor)session.getAttribute("requestor");
        Requestor requestor = requestorRepository.findOne(login_requestor.getId());
        if(requestor == null) return "Requestor None";
        Item ts = itemRepository.findOne(item_id);
        if(ts == null) return "Item None";
        
        int buyTotalCost = (int)(ts.getPrice().doubleValue() * amount);
        
        int balance = requestor.getBalance() - buyTotalCost;
        if(balance < 0) {
            return "Insufficient balance";
        }
        requestor.setBalance(balance);
        
        RQB rqb = new RQB();
        rqb.setRequestor(requestor);
        rqb.setItem(ts);
        rqb.setAmount(amount);
        rqb.setDate(new Date());
        
        requestorRepository.saveAndFlush(requestor);
        rqbRepository.saveAndFlush(rqb);
        
        return rqb.getId() + "";
    }
    
    
    
}
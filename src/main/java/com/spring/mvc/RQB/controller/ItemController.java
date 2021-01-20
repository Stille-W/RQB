package com.spring.mvc.RQB.controller;

import com.spring.mvc.RQB.entities.Classify;
import com.spring.mvc.RQB.entities.Item;
import com.spring.mvc.RQB.service.RQBService;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RQB/item")
public class ItemController {

    @Autowired
    private RQBService service;

    @PostMapping(value = {"/", "/add"})
    @Transactional
    public Item add(@RequestBody Map<String, String> map) {
        Classify classify = service.getClassifyRepository().findOne(Integer.parseInt(map.get("classify_id")));
        Item ts = new Item();
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        service.getItemRepository().save(ts);
        return ts;
    }
    
    @PutMapping(value = {"/", "/update"})
    @Transactional
    public Item update(@RequestBody Map<String, String> map) {
        Classify classify = service.getClassifyRepository().findOne(Integer.parseInt(map.get("classify_id")));
        Item ts = service.getItemRepository().findOne(Integer.parseInt(map.get("id")));
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        //service.gettStockRepository().update(Long.parseLong(map.get("id")), map.get("name"), map.get("symbol"));
        return ts;
    }
    
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    @Transactional
    public Boolean delete(@PathVariable("id") Integer id) {
        service.getItemRepository().delete(id);
        return true;
    }
    
    @GetMapping(value = {"/{id}", "/get/{id}"})
    @Transactional
    public Item get(@PathVariable("id") Integer id) {
        Item item = service.getItemRepository().findOne(id);
        return item;
    }

    @GetMapping(value = {"/", "/query"})
    public Iterable<Item> query() {
        return service.getItemRepository().findAll();
    }

}

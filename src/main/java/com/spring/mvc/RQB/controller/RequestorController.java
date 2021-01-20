package com.spring.mvc.RQB.controller;


import com.spring.mvc.RQB.entities.Requestor;
import com.spring.mvc.RQB.entities.Watch;
import com.spring.mvc.RQB.service.EmailService;
import com.spring.mvc.RQB.service.RQBService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
@RequestMapping("/RQB/requestor")
public class RequestorController {
    
    @Autowired
    private RQBService service;
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping(value = {"/", "/query"})
    public List<Requestor> query() {
        return service.getRequestorRepository().findAll();
    }
    
    //單筆查詢（根據id）
    @GetMapping(value = {"/{id}"})
    public Requestor get(@PathVariable("id") Optional<Integer> id){
        Requestor requestor = service.getRequestorRepository().findOne(id.get());
        return requestor;
    }
    
    //單筆修改（根據id）
    @PutMapping(value = {"/{id}"})
    //新增或修改都可以加入@Transactional
    @Transactional
    public Boolean update(@PathVariable("id") Optional<Integer> id, @RequestBody Map<String, String> jsonMap){
        //該筆資料是否存在
        if(get(id) == null){
            return false;
        }
        //修改資料
        service.getRequestorRepository().update(
                id.get(), 
                jsonMap.get("username"), 
                jsonMap.get("password"), 
                jsonMap.get("email"), 
                Integer.parseInt(jsonMap.get("balance")));                                        
        return true;
    }
    
    //單筆刪除（根據id）
    @DeleteMapping(value = {"/{id}"})
    @Transactional
    public Boolean delete(@PathVariable("id") Optional<Integer> id){
        //是否有id
        if(!id.isPresent()){
            return false;
        }
        //該筆資料是否存在
        if(get(id) == null){
            return false;
        }
        //刪除資料
        service.getRequestorRepository().delete(id.get());
        return true;
    }
    
    
    //新增
    //前端是json 後端是Map來接
    @PostMapping(value = {"/","/add"})
    public Requestor add(@RequestBody Map<String, String> jsonMap){
        Requestor requestor = new Requestor();
        //"username">>吃表單name="username"的
        requestor.setUsername(jsonMap.get("username"));
        requestor.setPassword(jsonMap.get("password"));
        requestor.setEmail(jsonMap.get("email"));
        requestor.setBalance(Integer.parseInt(jsonMap.get("balance")));
        requestor.setPass(Boolean.FALSE);
        //設定認證碼
        requestor.setCode(Integer.toHexString(requestor.hashCode()));
        
        //存檔Investor
        service.getRequestorRepository().save(requestor);
        //存檔Watch
        Watch watch = new Watch(requestor.getUsername()+"的委託單", requestor);
        service.getWatchRepository().save(watch);
        
        //發送認證信件
        emailService.send(requestor);
        
        return requestor;
    }
    
}

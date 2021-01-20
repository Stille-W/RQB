package com.spring.mvc.RQB.controller;

import com.spring.mvc.RQB.entities.Requestor;
import com.spring.mvc.RQB.service.RQBService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.mvc.RQB.repository.RequestorRepository;

@Controller
@RequestMapping("/RQB/verify")
public class VerifyController {
    
    @Autowired
    RQBService service;
    
    @GetMapping("/{id}/{code}")
    @Transactional
    public String verify(@PathVariable("id") Optional<Integer> id,
                        @PathVariable("code") Optional<String> code,
                        HttpSession session){
        String message = "ERROR";
        Requestor requestor = service.getRequestorRepository().findOne(id.get());
        if(requestor != null && requestor.getCode().equals(code.get())){
            service.getRequestorRepository().updatePass(id.get(), Boolean.TRUE);
            message = "SUCCESS";
        }
        session.setAttribute("message", message);
        return "redirect:/RQB/verify.jsp";
    }
    
}
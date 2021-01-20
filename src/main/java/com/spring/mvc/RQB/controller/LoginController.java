package com.spring.mvc.RQB.controller;


import com.spring.mvc.RQB.entities.Requestor;
import com.spring.mvc.RQB.service.RQBService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/RQB")
public class LoginController {
    
    @Autowired
    private RQBService service;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session){
        Requestor requestor = service.getRequestorRepository().getRequestor(username);
        if(requestor != null && requestor.getPassword().equals(password)){
            session.setAttribute("requestor", requestor);
            session.setAttribute("watch_id", requestor.getWatchs().iterator().next().getId());;
            return "redirect:/RQB/index.jsp";
        }
        session.invalidate();
        return "redirect:/RQB/login.jsp";
    }
    
    @GetMapping("/logout")
    public  String logout(HttpSession session){
      session.invalidate();
        return "redirect:/RQB/login.jsp";
    }
}

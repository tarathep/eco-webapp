package com.tarathep.eco.controller;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import com.tarathep.eco.model.*;
import com.tarathep.eco.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.ui.Model;

@Controller
public class AppController {

    @Autowired
    private LoginRepository loginRepository;

    Form form;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String home(Model model) {
        form = new Form();

        model.addAttribute("form", form);
        return "home";
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute Form form) {


        String password = md5(form.getPassword());
        String del_password = md5(form.getDel_password());

        if(password == null || del_password ==null){
            form.setResult("register_error");
        }else{
            form.setResult("register_success");
        }

        Login login = new Login();
        login.setUsername(form.getUsername());
        login.setPassword(password);
        login.setDeletepass(del_password);
        login.setBanned(0);
        login.setGmlevel(200);
        login.setBank(100000);
        login.setVshop_points(100000);
        login.setUsed_vshop_points(0);
        //login.setLastip();
    
        loginRepository.save(login);

        return "home";
    }

    public String md5(String plantText){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plantText.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();   
        }catch(Exception e){
              return null;
        }
    }

}
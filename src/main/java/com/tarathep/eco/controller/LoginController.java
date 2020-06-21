package com.tarathep.eco.controller;

import java.util.Collection;

import com.tarathep.eco.model.Login;
import com.tarathep.eco.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/login")
public class LoginController {
  @Autowired
  private LoginRepository loginRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String username , @RequestParam String password) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Login login = new Login();
    login.setUsername(username);
    login.setPassword(password);
    login.setDeletepass(password);
    login.setBanned(0);
    login.setGmlevel(200);
    login.setBank(100000);
    login.setVshop_points(100000);
    login.setUsed_vshop_points(0);
    //login.setLastip();

    loginRepository.save(login);
  
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Login> getAllUsers() {
    // This returns a JSON or XML with the users.
    
    return loginRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.GET,path = "{username}")
  public @ResponseBody Login getUser(@PathVariable("username") String username){

    return loginRepository.findByUsername(username);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/delete/{username}")
  public @ResponseBody String deleteUser(@PathVariable("username") String username){

    Iterable<Login> values = loginRepository.findAllByUsername(username);

    if (values instanceof Collection<?>) {
      Collection<Login> collection  = ((Collection<Login>)values);

      if(collection.size()>0){
        for (Login login : collection) {
          try{
            loginRepository.deleteById(login.getAccount_id());
            return login.getUsername() +" had been removed";
          }catch(Exception e){
            return e.getMessage();
          }
        }
      }
    }
    return "Can't find "+username+" ECO account !";
  }
}
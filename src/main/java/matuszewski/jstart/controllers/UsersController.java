package matuszewski.jstart.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import matuszewski.jstart.model.City;
import matuszewski.jstart.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {


    //Odczyt pojedyńczy
    @GetMapping
    public String get(Model _model,@ModelAttribute Users model){
        _model.addAttribute("user" , new Users());

        Long id = model.getLibarycardnumber();
        System.out.println("Get http://localhost:8080/api/users/" + id);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/users/" + id);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            Users audiobook = new ObjectMapper().readValue(connection.getInputStream(), Users.class);
            System.out.println("Get one " + audiobook.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user";
    }


    @GetMapping(path = "/delete")
    public String delete(Model _model,@ModelAttribute Users model){
        _model.addAttribute("user" , new Users());

        Long id = model.getLibarycardnumber();
        System.out.println("Delete http://localhost:8080/api/users/" + id);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/users/" + id);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();
            System.out.println("Delete");
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "user";
    }

    @GetMapping(path = "/update")
    public String update(Model _model, @ModelAttribute Users model){
        _model.addAttribute("user",new Users());
        Long id = model.getLibarycardnumber();
        String firstname = model.getFirstname();
        String lastname = model.getLastname();

        System.out.println("Update http://localhost:8080/api/users"+id+"?firstname="+firstname+"&lastname="+lastname);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/users"+id+"?firstname="+firstname+"&lastname="+lastname);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();
            System.out.println("Update");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user";
    }

    @GetMapping(path = "/all")
    public String getAll(Model _model){

        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/users");
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Users.class);
            List<Users> lista = mapper.readValue(connection.getInputStream(), type);

            _model.addAttribute("userList" , lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "userTable";
    }


    @PostMapping
    public String addCity(Model _model,@ModelAttribute Users model){

        _model.addAttribute("user" , new Users());

        String firstname = model.getFirstname();
        String lastname = model.getLastname();

        System.out.println("Add http://localhost:8080/api/users?firstname="+firstname+"&lastname="+lastname);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/users?firstname="+firstname+"&lastname="+lastname);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.getResponseMessage();

            System.out.println("Add" + firstname + lastname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user";
    }

    @GetMapping(path = "/count")
    public String count(RedirectAttributes redirectAttr){

        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/users");
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Users.class);
            List<Users> lista = mapper.readValue(connection.getInputStream(), type);

            redirectAttr.addFlashAttribute("messageUsers",lista.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }

}


package matuszewski.jstart.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import matuszewski.jstart.model.City;
import matuszewski.jstart.model.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;



@Controller
@RequestMapping("/orders")
public class OrderController {

    //Odczyt pojedyńczy
    @GetMapping
    public String get(Model _model,@ModelAttribute Orders model){
        _model.addAttribute("order" , new Orders());

        Long id = model.getOrderid();
        System.out.println("Get http://localhost:8080/api/orders/" + id);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/orders/" + id);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            Orders audiobook = new ObjectMapper().readValue(connection.getInputStream(), Orders.class);
            System.out.println("Get one " + audiobook.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "order";
    }


    @GetMapping(path = "/delete")
    public String delete(Model _model,@ModelAttribute Orders model){
        _model.addAttribute("order" , new Orders());

        Long id = model.getOrderid();
        System.out.println("Delete http://localhost:8080/api/orders/" + id);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/orders/" + id);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();
            System.out.println("Delete");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "order";
    }


    @GetMapping(path = "/update")
    public String update(Model _model, @ModelAttribute Orders model){
        _model.addAttribute("order",new Orders());
        Long id = model.getOrderid();
        String author = model.getAuthor();
        String title = model.getTitle();

        System.out.println("Update http://localhost:8080/api/orders/"+ id +"?author=" + author + "&title=" + title);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/orders/"+ id +"?author=" + author + "&title=" + title);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();
            System.out.println("Update");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "order";
    }

    @GetMapping(path = "/all")
    public String getAll(Model _model){

        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/orders");
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Orders.class);
            List<Orders> lista = mapper.readValue(connection.getInputStream(), type);

            _model.addAttribute("userList" , lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "userTable";
    }


    @PostMapping
    public String addCity(Model _model,@ModelAttribute Orders model){

        _model.addAttribute("order" , new Orders());

        String author = model.getAuthor();
        String title = model.getTitle();

        System.out.println("Add http://localhost:8080/api/orders?author="+author+"&title="+title);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/orders?author="+author+"&title="+title);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.getResponseMessage();

            System.out.println("Add" + author + title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "order";
    }

    @GetMapping(path = "/count")
    public String count(RedirectAttributes redirectAttr){

        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/orders");
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Orders.class);
            List<Orders> lista = mapper.readValue(connection.getInputStream(), type);

            redirectAttr.addFlashAttribute("messageUsers",lista.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/orders/home";
    }
}

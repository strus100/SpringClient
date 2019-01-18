package matuszewski.jstart.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import matuszewski.jstart.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/borrows")
public class BorrowController {

    @RequestMapping("/home")
    public String home(){
        return "borrow";
    }

    //Odczyt pojedyńczy
    @GetMapping("/{id}")
    public String proba1(Model model,@PathVariable Long id){
        model.addAttribute("cityModel",new City());
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/cities/id");

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            City city = new ObjectMapper().readValue(connection.getInputStream(), City.class);

            System.out.println(city.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }


    @GetMapping("/del/{id}")
    public String delete(Model model,@PathVariable Long id){
        model.addAttribute("cityModel",new City());
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/cities/" + id);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }


    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable Long id,@ModelAttribute City cityModel){

        String name = cityModel.getName();
        int population = cityModel.getPopulation();

        model.addAttribute("cityModel",new City());
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/cities/test/"+ id +"?name=" + name + "&population=" + population);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping
    public String lista(Model model){

        model.addAttribute("cityModel",new City());
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/cities");

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, City.class);
            List<City> users = mapper.readValue(connection.getInputStream(), type);

            System.out.println(users);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }


    @PostMapping
    public String addCity(@ModelAttribute City cityModel){
        System.out.println("client działą");
        String name = cityModel.getName();
        int population = cityModel.getPopulation();

        try {
        //Połącznie z restApi servera
        URL url = new URL("http://localhost:8080/api/cities/test/?name=" + name + "&population=" + population);


        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
            connection.getResponseMessage();


         } catch (IOException e) {
        e.printStackTrace();
    }

        return "redirect:/borrows/home";
    }
}

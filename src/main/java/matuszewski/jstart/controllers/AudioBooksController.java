package matuszewski.jstart.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import matuszewski.jstart.model.Audiobooks;
import matuszewski.jstart.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/audiobooks")
public class AudioBooksController {

    @RequestMapping("/home")
    public String home(){
        return "audiobook";
    }

    //Odczyt pojedyńczy
    @GetMapping
    public String get(Model _model,@ModelAttribute Audiobooks model){
        _model.addAttribute("audiobook" , new Audiobooks());

        Long id = model.getBookid();
        System.out.println("Get http://localhost:8080/api/audiobooks/" + id);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/audiobooks/" + id);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            Audiobooks audiobook = new ObjectMapper().readValue(connection.getInputStream(), Audiobooks.class);
            System.out.println("Get one " + audiobook.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "audiobook";
    }


    @GetMapping(path = "/delete")
    public String delete(Model _model,@ModelAttribute Audiobooks model){

        _model.addAttribute("audiobook" , new Audiobooks());


        Long id = model.getBookid();

        System.out.println(
                "Delete http://localhost:8080/api/audiobooks/" + id
        );
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/audiobooks/" + id);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();

            System.out.println("Delete");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "audiobook";
    }


    @GetMapping(path = "/update")
    public String update(Model _model, @ModelAttribute Audiobooks model){

        _model.addAttribute("audiobook",new Audiobooks());
        Long id = model.getBookid();
        String author = model.getAuthor();
        String title = model.getTitle();

        System.out.println("Update http://localhost:8080/api/audiobooks/"+ id +"?author=" + author + "&title=" + title);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/audiobooks/"+ id +"?author=" + author + "&title=" + title);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getResponseMessage();
            System.out.println("Update");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "audiobook";
    }

    @GetMapping(path = "/all")
    public String getAll(Model _model){

        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/audiobooks");
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Audiobooks.class);
            List<Audiobooks> lista = mapper.readValue(connection.getInputStream(), type);


            _model.addAttribute("audiobookList" , lista);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "audiobookTable";
    }


    @PostMapping
    public String addCity(Model _model,@ModelAttribute Audiobooks model){

        _model.addAttribute("audiobook" , new Audiobooks());

        String author = model.getAuthor();
        String title = model.getTitle();

        System.out.println("Add http://localhost:8080/api/audiobooks?author="+author+"&title="+title);
        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/audiobooks?author="+author+"&title="+title);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.getResponseMessage();

            System.out.println("Add" + author + title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "audiobook";
    }

    @GetMapping(path = "/count")
    public String count(RedirectAttributes redirectAttr){



        try {
            //Połącznie z restApi servera
            URL url = new URL("http://localhost:8080/api/audiobooks");
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Audiobooks.class);
            List<Audiobooks> lista = mapper.readValue(connection.getInputStream(), type);

            redirectAttr.addFlashAttribute("messageAudio",lista.size());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/audiobooks/home";
    }

}

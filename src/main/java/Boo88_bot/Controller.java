package Boo88_bot;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final Bot bot;

    public Controller(Bot bot) {
        this.bot = bot;
    }

    public List <String> getUsers(int quantity) {
        String url = "";
        if(quantity!=0) url="https://reqres.in//api/users?per_page="+quantity;
        else url="https://reqres.in//api/users";
        List <String> userList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        Json users = restTemplate.getForObject(url, Json.class);
        assert users != null;
         for(Data user:users.getData()){
             userList.add("Name: " + user.getFirstName() + " " + user.getLastName() + "\nEmail: " + user.getEmail());
        }
         return userList;
    }
}
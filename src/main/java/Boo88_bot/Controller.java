package Boo88_bot;

import Boo88_bot.JsonData.Json1;
import Boo88_bot.JsonData.Json2;
import Boo88_bot.JsonData.Header1;
import Boo88_bot.JsonData.Header2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private final Bot bot;

    public Controller(Bot bot) {
        this.bot = bot;
    }

    public List<String> getUsers(int quantity) {
        String url = "";
        if (quantity != 0) url = "https://reqres.in//api/users?per_page=" + quantity;
        else url = "https://reqres.in//api/users";
        List<String> userList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        Header1 users = restTemplate.getForObject(url, Header1.class);
        assert users != null;
        for (Json1 user : users.getData()) {
            userList.add("Name: " + user.getFirstName() + " " + user.getLastName() + "\nEmail: " + user.getEmail());
        }
        return userList;
    }

    public String getUser(int id) {
        String url = "https://reqres.in//api/users/" + id;
        RestTemplate restTemplate = new RestTemplate();
        if (id > 0) {
            Header2 user = restTemplate.getForObject(url, Header2.class);
            assert user != null;
            Json2 data = user.getData();
            return "ID: " + data.getId() +
                    "\nName: " + data.getFirstName() + " " + data.getLastName() +
                    "\nEmail: " + data.getEmail() + "\n" + data.getAvatar();
        } else return "Неправильный формат сообщения. Верная команда \"/get_user N\", где N равен ID человека";
    }

    public String createUser(String first_name, String last_name) {
        String url = "https://reqres.in//api/users";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> json = new HashMap<>();
        json.put("username", first_name + " " + last_name);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(json);
        String response = restTemplate.postForObject(url, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "Create user successfully\n" + "Name: " + actualObj.get("username").asText() +
                "\nID: " + actualObj.get("id").asText();

    }
}
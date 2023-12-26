package com.example.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    // Asumiendo que tienes una conexión a MongoDB ya configurada
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = mongoClient.getDatabase("practica2");
    MongoCollection<Document> collection = database.getCollection("users");

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
        Document query = new Document();
        query.append("userName", userName)
                .append("password", Document.parse(password)); // Vulnerabilidad aquí

        // Ejecución de la consulta
        Document user = collection.find(query).first();


        if (user != null) {
            return "welcome";
        } else {
            model.addAttribute("loginError", true);
            return "login";
        }
    }
}

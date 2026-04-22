package main;

import entities.Users;
import Services.UsersService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);
        UsersService us = new UsersService();

        try {
            //us.ajouter(new Users("Na3im","Mon3em.jamil@faf.com","JJjuje635z","ROLE_CONTENT_CREATOR","12536848",now,20,0));
            //us.supprimer(24);
            us.modifier(new Users("Mon3emJamil","Mon3em.wisdom@faf.com","JJddd84a5z","ROLE_CONTENT_CREATOR","12859476",now,22,0));
            System.out.println(us.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
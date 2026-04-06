package org.Per.Controllers.Mains;

import org.Per.Controllers.Entities.Personne;
import org.Per.Controllers.Services.PersonneService;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PersonneService ps = new PersonneService();
        try {
            ps.ajouter(new Personne("Moetez","Groun",30));
            //ps.supprimer(4);
            System.out.println(ps.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
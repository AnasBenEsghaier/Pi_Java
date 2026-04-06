package org.Per.Controllers.Services;

import org.Per.Controllers.Entities.Personne;
import org.Per.Controllers.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneService implements PersonneInterface<Personne>{
    Connection con;

    public PersonneService() {
        con = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Personne personne) throws SQLException {
        String sql = "INSERT INTO `personne`(`nom`, `prenom`, `age`) VALUES ('"+personne.getNom()+"','"+personne.getPrenom()+"',"+personne.getAge()+")";
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
        System.out.println("Personne ajoutée avec succes!");


    }

    @Override
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM `personne` WHERE `id`=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        System.out.println("Personne suupprimer");


    }

    @Override
    public List<Personne> afficher() throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM personne";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Personne personne = new Personne();
            personne.setId(rs.getInt("id"));
            personne.setNom(rs.getString("nom"));
            personne.setPrenom(rs.getString("prenom"));
            personne.setAge(rs.getInt("age"));
            personnes.add(personne);
        }
        return personnes;



    }

    @Override
    public void modifier(int id) throws SQLException {


    }
}

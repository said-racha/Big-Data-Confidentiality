/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 *
 * @author user
 */
public class patient {
    private int id_patient;
    private String nom;
    private String prenom;
    private String tel1;
    private String tel2;
    private String nom_med;
    private String prenom_med;
    private String email;

    public patient(int id_patient,String nom, String prenom, String tel1, String tel2, String nom_med, String prenom_med) {
        this.id_patient = id_patient;
        this.nom = nom;
        this.prenom = prenom;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.nom_med = nom_med;
        this.prenom_med = prenom_med;
    }
    
     public patient(String email, int id_patient,String nom, String prenom, String tel1, String nom_med, String prenom_med) {
        this.id_patient = id_patient;
        this.nom = nom;
        this.prenom = prenom;
        this.tel1 = tel1;
        this.email = email;
        this.nom_med = nom_med;
        this.prenom_med = prenom_med;
    }

    public String getEmail() {
        return email;
    }

     
    public int getId_patient() {
        return id_patient;
    }
    

    public String getNom() {
        return nom;
    }

    public String getNom_med() {
        return nom_med;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPrenom_med() {
        return prenom_med;
    }

    public String getTel1() {
        return tel1;
    }

    public String getTel2() {
        return tel2;
    }
    
    
    
}

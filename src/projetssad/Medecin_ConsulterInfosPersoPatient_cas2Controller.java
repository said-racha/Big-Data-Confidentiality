/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetssad;

import classes.patient;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Medecin_ConsulterInfosPersoPatient_cas2Controller implements Initializable {

    @FXML TableView<patient> table_pat;
    @FXML TableColumn id_patient;
    @FXML TableColumn nom;
    @FXML TableColumn prenom;
    @FXML TableColumn tel;
    @FXML TableColumn email;
    @FXML TableColumn nom_med;
    @FXML TableColumn prenom_med;
    @FXML TextField nom_hopital;
    @FXML TextField nom_service;
    
    String nomMed="",prenomMed="";
    
    static int idP;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            id_patient.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tel.setCellValueFactory(new PropertyValueFactory<>("tel1"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            nom_med.setCellValueFactory(new PropertyValueFactory<>("nom_med"));
            prenom_med.setCellValueFactory(new PropertyValueFactory<>("prenom_med"));
            
            Connection cnx = Main.connection;
            ArrayList<patient> liste_patient= new ArrayList<>();
            
            Statement myStmt = cnx.createStatement();
            Statement myStmt2 = cnx.createStatement();
            
            String idMedecin=AuthentificationController.idMedecin; 
			
            
            ResultSet myRs = myStmt.executeQuery("SELECT `idPatient`, `nom`, `prenom`,`tel1`, `email`,`idMedecin` FROM `patient` WHERE idMedecin="+idMedecin+" AND idHopital="+AuthentificationController.idH_Medecin+" AND idService="+AuthentificationController.idS_Medecin+"");
                    
            
            while(myRs.next())
            {
                ResultSet myRs2 = myStmt2.executeQuery("SELECT `nom`, `prenom` FROM `medecin` WHERE `idMedecin` = "+myRs.getInt("idMedecin")+"");
                
                while(myRs2.next()){
                    nomMed = myRs2.getString("nom");
                    prenomMed = myRs2.getString("prenom");
                }
                patient p = new patient(myRs.getString("email"),myRs.getInt("idPatient"),myRs.getString("nom"),myRs.getString("prenom"),myRs.getString("tel1"),nomMed,prenomMed);
                liste_patient.add(p);
                
            }
            
            ObservableList<patient> listObservable = FXCollections.observableArrayList(liste_patient);
            table_pat.setItems(listObservable);
            table_pat.getSortOrder().add(nom);
            
            Statement myStmt3 = cnx.createStatement();
            
            //Récupérer le nom de l'hopital et du service
            ResultSet Rs = myStmt3.executeQuery("Select h.nomHopital , s.nomService From hopital h, service s Where h.idHopital = "+AuthentificationController.idH_Medecin+" AND s.idService = "+AuthentificationController.idS_Medecin+" ");
            if (Rs.next())
            {
                nom_hopital.setText(Rs.getString("nomHopital"));
                nom_service.setText(Rs.getString("nomService"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Medecin_ConsulterInfosPersoPatient_cas2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
        @FXML
    void Authentification(ActionEvent event) throws IOException {

    	Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        stage.setTitle("Acceuil");
    }
    
    @FXML
    void Retour(ActionEvent event) throws IOException {

    	Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_acceuil2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        stage.setTitle("Menu cas2");
    }    
    
    
}

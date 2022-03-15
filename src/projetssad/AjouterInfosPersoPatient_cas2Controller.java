/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetssad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterInfosPersoPatient_cas2Controller implements Initializable {
    @FXML TextField nom;
    @FXML TextField prenom;
    @FXML TextField tel;
    @FXML TextField email;
    @FXML DatePicker date_naiss;
    @FXML ComboBox groupe_sanguin;
    @FXML ComboBox genre;
    @FXML TextField nom_med;
    @FXML TextField prenom_med;
    @FXML TextField nom_hopital;
    @FXML TextField nom_service;
    
    int idMed;
    
     public void ajouterPatient(ActionEvent event) throws SQLException, IOException
    {
         //Verifier qu'aucun champ n'est vide

    	if(nom.getText().isEmpty() || prenom.getText().isEmpty() || tel.getText().isEmpty() || email.getText().isEmpty() || date_naiss.getValue()==null || nom_med.getText().isEmpty() || prenom_med.getText().isEmpty() || groupe_sanguin.getValue()==null || genre.getValue()==null )
    	{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!!");
            alert.show();
        }else{
            Connection cnx = Main.connection; 
            Statement myStmt = cnx.createStatement();
           
            //Récupérer l'id du médecin
            ResultSet myRs = myStmt.executeQuery("SELECT idMedecin FROM medecin WHERE prenom = '"+prenom_med.getText()+"' AND nom = '"+nom_med.getText()+"' AND idHopital = "+AuthentificationController.idH_Secretaire+" AND idService = "+AuthentificationController.idS_Secretaire+" "); 
            if (myRs.next())
            {
                idMed = myRs.getInt("idMedecin");
                
                //Insérer le patient
                myStmt.execute("INSERT INTO `patient`(`nom`, `prenom`, `email`, `tel1`, `dateN`, `grpsanguin`, `genre`, `idHopital`, `idService`, `idMedecin`) VALUES ('"
                                    +nom.getText()
                                    + "','" + prenom.getText()
                                    + "','" + email.getText()
                                    + "','" + tel.getText()
                                    + "','" + date_naiss.getValue()
                                    + "','" + groupe_sanguin.getValue()
                                    + "','" + genre.getValue()
                                    + "'," + AuthentificationController.idH_Secretaire
                                    + "," + AuthentificationController.idS_Secretaire
                                    + "," + idMed
                                    + ");");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alerte");
                alert.setHeaderText(null);
                alert.setContentText("Inscription effectuée avec succés !");
                alert.show();
                
                nom.setText("");
                prenom.setText("");
                email.setText("");
                tel.setText("");
                nom_med.setText("");
                prenom_med.setText("");
                date_naiss.setValue(null);
                groupe_sanguin.setValue(null);
                genre.setValue(null);
            }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Veuillez entrer un médecin existant dans votre service!");
                        alert.show();
            }
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
        Parent parent = FXMLLoader.load(getClass().getResource("Menu2Secretaire_cas2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        stage.setTitle("Menu cas2");
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            genre.getItems().addAll("Homme", "Femme");
            groupe_sanguin.getItems().addAll("O-POS","O-NEG","A-POS","A-NEG","B-POS","B-NEG","AB-POS","AB-NEG");
            
            Connection cnx = Main.connection;
            Statement myStmt2 = cnx.createStatement();
            
            //Récupérer le nom de l'hopital et du service
            ResultSet myRs2 = myStmt2.executeQuery("Select h.nomHopital , s.nomService From hopital h, service s Where h.idHopital = "+AuthentificationController.idH_Secretaire+" AND s.idService = "+AuthentificationController.idS_Secretaire+" ");
            
            if (myRs2.next())
            {
                nom_hopital.setText(myRs2.getString("nomHopital"));
                nom_service.setText(myRs2.getString("nomService"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterInfosPersoPatient_cas2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

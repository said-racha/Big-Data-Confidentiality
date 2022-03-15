/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetssad;

import classes.AES;
import classes.RSA;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Admin_AjouterMedecinController implements Initializable {
    
    @FXML TextField email;
    @FXML DatePicker dateNaiss;
    @FXML TextField login;
    @FXML PasswordField mdp;
    @FXML PasswordField mdpConfirmer;
    @FXML TextField prenom;
    @FXML TextField nom;
    
  //*********************************Module de cryptage*********************************
    String mykey ="lv39eptJvuhAq5srTo7mFqiZcuwXq1n0"; //32 caractères * 8 bits=256 (une clé peut etre sur 128, 192 et 256 bits)
    SecretKey key = new SecretKeySpec(mykey.getBytes(), "AES");
    AES objetAES=new AES(key);
    
    //************************************************************************************

    
    public void ajouterMedecin(ActionEvent event) throws SQLException, IOException
    {
        //Verifier qu'aucun champ n'est vide
    	if(email.getText().isEmpty() || login.getText().isEmpty() || mdp.getText().isEmpty() || mdpConfirmer.getText().isEmpty() || dateNaiss.getValue()==null)
    	{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!!");
            alert.show();
        }else if( !(mdp.getText().equals(mdpConfirmer.getText())) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez confirmer votre mot de passe!!");
            alert.show();
        }else{
            
            Connection cnx = Main.connection; 
            Statement myStmt = cnx.createStatement();
            
             myStmt.execute("INSERT INTO `medecin` (`email`, `nomUser`, `mdp`,`prenom`, `nom`, `datenaiss`, `idService`, `idHopital`) VALUES ('"
                                +email.getText()
                                + "','" + login.getText()
                                + "','" + objetAES.encrypt(mdp.getText())
                                + "','" + prenom.getText()
                                + "','" + nom.getText()
                                + "','" + dateNaiss.getValue()
                                + "'," + Menu1AdminController.id_Service
                                + "," + AuthentificationController.idH_Admin
                                + ");");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Inscription effectuée avec succés !");
            alert.show();
            
            nom.setText("");
            prenom.setText("");
            email.setText("");
            login.setText("");
            mdp.setText("");
            mdpConfirmer.setText("");
            dateNaiss.setValue(null);
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
        Parent parent = FXMLLoader.load(getClass().getResource("Menu1Admin.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        stage.setTitle("Menu");
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

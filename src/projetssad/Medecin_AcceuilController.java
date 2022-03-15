package projetssad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Medecin_AcceuilController implements Initializable{
	
    @FXML TextField nom_hopital;
    @FXML TextField nom_service;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Connection cnx = Main.connection;
		
		try {
		Statement myStmt3 = cnx.createStatement();
        
		//Récupérer le nom de l'hopital et du service
        ResultSet Rs = myStmt3.executeQuery("Select h.nomHopital , s.nomService From hopital h, service s Where h.idHopital = "+AuthentificationController.idH_Medecin+" AND s.idService = "+AuthentificationController.idS_Medecin+" ");
        if (Rs.next())
        {
            nom_hopital.setText(Rs.getString("nomHopital"));
            nom_service.setText(Rs.getString("nomService"));
        }
        
		}catch (Exception e) {
			
		}
	}
	
	
	

	@FXML
	private Button home_btn;
	
	@FXML
    void authentification(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        stage.setTitle("Menu");
    }
	
	@FXML
    void consulterInfoPerso1(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_ConsulterInfosPersoPatient_cas1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        
    }
	
	@FXML
    void consulterInfoMed1(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_consulterInfoMed1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
       
    }
	
	@FXML
    void inscrireInfoMed1(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_inscrireInfoMed1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        
    }
	
	@FXML
    void consulterInfoMedExterne1(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_consulterInfoMedExterne_idh.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
    }
	
	
	//Cas2/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@FXML
    void consulterInfoPerso2(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_ConsulterInfosPersoPatient_cas2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        
    }
	
	@FXML
    void consulterInfoMed2(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_consulterInfoMed2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
       
    }
	
	@FXML
    void inscrireInfoMed2(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_inscrireInfoMed2.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        
    }
	
	@FXML
    void consulterInfoMedExterne2(ActionEvent event) throws IOException {
		
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_consulterInfoMedExterne_idh.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
    }

	
	
	
    
}

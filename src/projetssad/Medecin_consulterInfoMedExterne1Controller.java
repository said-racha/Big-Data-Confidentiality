package projetssad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import classes.AES;
import classes.consultation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Medecin_consulterInfoMedExterne1Controller implements Initializable{
	
	@FXML TextField nom_hopital;
    @FXML TextField nom_service;
	
	
	@FXML
    private TextField idConsultation;//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public static String idConsultStatic; //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	
	
	@FXML
    private TextField idP;
	
	@FXML
    private TableView<consultation> infoMedTable;

    @FXML
    private TableColumn<String, String> gsCol;

    @FXML
    private TableColumn<String, String> tailleCol;

    @FXML
    private TableColumn<String, String> poidsCol;

    @FXML
    private TableColumn<String, String> obsCol;

    @FXML
    private TableColumn<String, String> dateConsuCol;
    

  //*********************************Module de cryptage*********************************
    String mykey ="lv39eptJvuhAq5srTo7mFqiZcuwXq1n0"; //32 caract�res * 8 bits=256 (une cl� peut etre sur 128, 192 et 256 bits)
    SecretKey key = new SecretKeySpec(mykey.getBytes(), "AES");
    AES objetAES=new AES(key);
    
  //************************************************************************************

    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<consultation> Liste_infoMed= ListeInfoMed();
		infoMedTable.setItems(Liste_infoMed);
		
		Connection cnx = Main.connection;
		
		try {
			Statement myStmt3 = cnx.createStatement();
		 
			//Récupérer le nom de l'hopital et du service
		 ResultSet Rs = myStmt3.executeQuery("Select h.nomHopital , s.nomService From hopital h, service s Where h.idHopital = "+Medecin_consulterInfoMedExterne_idhController.idHstatic+" AND s.idService = "+Medecin_consulterInfoMedExterne_idhController.idSstatic+" ");
		 if (Rs.next())
		 {
		     nom_hopital.setText(Rs.getString("nomHopital"));
		     nom_service.setText(Rs.getString("nomService"));
		 }
		
		}catch (Exception e) {
			
		}
	}
	
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
    void retour(ActionEvent event) throws IOException {
		Main.stage.close(); 
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_acceuil1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        
    }
	
	@FXML
	void consulterDetail (ActionEvent event) throws IOException {
		Main.stage.close(); 
		idConsultStatic=idConsultation.getText(); //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_consulterDetailInfoMedExterne1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        
    }
	
	
	public ObservableList<consultation> ListeInfoMed()
	{
		
		Connection cnx = Main.connection;
		
		Statement myStmt = null;
		try {
			myStmt = cnx.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        ResultSet myRs = null,myRs2 = null;
		try {
			myRs = myStmt.executeQuery("Select * FROM consultation");
			
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ObservableList<consultation> infoMedList = FXCollections.observableArrayList();
        
 long startTime=System.currentTimeMillis();
        	
        
        try {
			while(myRs.next()){
				
				String idPatient = myRs.getString("idPatient");
				
				String sql="Select * FROM patient WHERE idPatient ='"+idPatient+"' and idHopital ='"+Medecin_consulterInfoMedExterne_idhController.idHstatic+"' and idService='"+Medecin_consulterInfoMedExterne_idhController.idSstatic+"'";
				PreparedStatement preparedStmt = cnx.prepareStatement(sql);
			    
			    myRs2 = preparedStmt.executeQuery(sql); //where idPatient in listePatientIdMed
				
			    consultation consultation = null;
			    
				if(myRs2.next())
				{
					consultation=new consultation();
					consultation.setGrpSanguin( myRs2.getString("grpsanguin"));
					
					
					consultation.setTaille( objetAES.decrypt(myRs.getString("taille"))); 
					consultation.setPoids(objetAES.decrypt(myRs.getString("poids")));
					consultation.setObservation(objetAES.decrypt(myRs.getString("observation")));
					consultation.setDateConsult(myRs.getString("dateConsult"));
					
					infoMedList.add(consultation);
					
				}
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        gsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("grpSanguin")
				);
        
        tailleCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("taille")
				);
		poidsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("poids")
				);
		obsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("observation")
				);
		dateConsuCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("dateConsult")
				);
		
System.out.println("Temps de decryptage :"+ (System.currentTimeMillis()-startTime)+" ms");
		
		
		return infoMedList; 
		
	}
	
	
	@FXML
	void rechercher (ActionEvent event) throws IOException {
		
		Connection cnx = Main.connection;
		
		Statement myStmt = null;
		try {
			myStmt = cnx.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        ResultSet myRs = null,myRs2 = null;
		try {
			String idPstring=idP.getText(); 
			myRs = myStmt.executeQuery("Select * FROM consultation WHERE idPatient ='"+idPstring+"'"); 
			
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ObservableList<consultation> infoMedList = FXCollections.observableArrayList();
		
        
        try {
			while(myRs.next()){
				
				String idPatient = myRs.getString("idPatient"); 

				String sql="Select * FROM patient WHERE idPatient ='"+idPatient+"' and idHopital ='"+Medecin_consulterInfoMedExterne_idhController.idHstatic+"' and idService='"+Medecin_consulterInfoMedExterne_idhController.idSstatic+"'";
				PreparedStatement preparedStmt = cnx.prepareStatement(sql);
			    
			    myRs2 = preparedStmt.executeQuery(sql); //where idPatient in listePatientIdMed
				
			    consultation consultation = null;
			    
				if(myRs2.next())
				{
					consultation=new consultation();
					consultation.setGrpSanguin( myRs2.getString("grpsanguin"));
					
					
					consultation.setTaille( objetAES.decrypt(myRs.getString("taille"))); 
					consultation.setPoids(objetAES.decrypt(myRs.getString("poids")));
					consultation.setObservation(objetAES.decrypt(myRs.getString("observation")));
					consultation.setDateConsult(myRs.getString("dateConsult"));
					
					
					infoMedList.add(consultation);
					
				}
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        gsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("grpSanguin")
				);
        
        tailleCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("taille")
				);
		poidsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("poids")
				);
		obsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("observation")
				);
		dateConsuCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("dateConsult")
				);
		
		
		infoMedTable.setItems(infoMedList);
		
    }
	
	@FXML
	void rechercherConsultation (ActionEvent event) throws IOException {
		
		Connection cnx = Main.connection;
		
		Statement myStmt = null;
		try {
			myStmt = cnx.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        ResultSet myRs = null,myRs2 = null;
		try {
			String idConsultationString=idConsultation.getText(); 
			myRs = myStmt.executeQuery("Select * FROM consultation WHERE idConsultation ='"+idConsultationString+"'"); 
			
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ObservableList<consultation> infoMedList = FXCollections.observableArrayList();
		
        
        try {
			while(myRs.next()){
				
				String idPatient = myRs.getString("idPatient"); 
				//=============================================================================================
				String sql="Select * FROM patient WHERE idPatient ='"+idPatient+"' and idHopital ='"+Medecin_consulterInfoMedExterne_idhController.idHstatic+"' and idService='"+Medecin_consulterInfoMedExterne_idhController.idSstatic+"'";
				
				PreparedStatement preparedStmt = cnx.prepareStatement(sql);
			    
			    myRs2 = preparedStmt.executeQuery(sql); //where idPatient in listePatientIdMed
				
			    consultation consultation = null;
			    
				if(myRs2.next())
				{
					consultation=new consultation();
					consultation.setGrpSanguin( myRs2.getString("grpsanguin"));
					
					
					consultation.setTaille( objetAES.decrypt(myRs.getString("taille"))); 
					consultation.setPoids(objetAES.decrypt(myRs.getString("poids")));
					consultation.setObservation(objetAES.decrypt(myRs.getString("observation")));
					consultation.setDateConsult(myRs.getString("dateConsult"));
					
					infoMedList.add(consultation);
					
				}
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        gsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("grpSanguin")
				);
        
        tailleCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("taille")
				);
		poidsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("poids")
				);
		obsCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("observation")
				);
		dateConsuCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("dateConsult")
				);
		
		
		infoMedTable.setItems(infoMedList);
		
    }
	
	
	
	
}

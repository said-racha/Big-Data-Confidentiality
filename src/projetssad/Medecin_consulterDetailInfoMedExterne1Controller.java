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

public class Medecin_consulterDetailInfoMedExterne1Controller implements Initializable{
	
	@FXML TextField nom_hopital;
    @FXML TextField nom_service;
	
    
	@FXML
    private TableView<consultation> antecedentTable;

    @FXML
    private TableColumn<String, String> antecedentCol;
    
    
    @FXML
    private TableView<consultation> bilanTable;

    @FXML
    private TableColumn<String, String> analyseCol;

    @FXML
    private TableColumn<String, String> resultCol;
    

	@FXML
    private TextField taille;

    @FXML
    private TextField obs;

    @FXML
    private TextField poids;

    @FXML
    private TextField gs;
	
    
  //*********************************Module de cryptage*********************************
    String mykey ="lv39eptJvuhAq5srTo7mFqiZcuwXq1n0"; //32 caract�res * 8 bits=256 (une cl� peut etre sur 128, 192 et 256 bits)
    SecretKey key = new SecretKeySpec(mykey.getBytes(), "AES");
    AES objetAES=new AES(key);
    
	   
    //************************************************************************************

    
    
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
        Parent parent = FXMLLoader.load(getClass().getResource("Medecin_consulterInfoMedExterne1.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Main.stage = stage;
        
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	//***********************************Afficher les textFields***********************************************
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
			String idConsultString=Medecin_consulterInfoMedExterne1Controller.idConsultStatic; 
			myRs = myStmt.executeQuery("Select * FROM consultation WHERE idConsultation ='"+idConsultString+"'"); 
			
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ObservableList<consultation> infoMedList = FXCollections.observableArrayList();
		
        
  long startTime=System.currentTimeMillis();
        
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
					gs.setText( myRs2.getString("grpsanguin"));
					
					
					taille.setText( objetAES.decrypt(myRs.getString("taille"))); 
					poids.setText(objetAES.decrypt(myRs.getString("poids")));
					obs.setText(objetAES.decrypt(myRs.getString("observation")));
					
				}
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
      //***********************************Afficher les ant�c�dents***********************************************
        ObservableList<consultation> Liste_antecedent= Liste_antecedent();
        antecedentTable.setItems(Liste_antecedent);
		
      //***********************************Afficher les bilans***********************************************
        ObservableList<consultation> Liste_bilan= Liste_bilan();
        bilanTable.setItems(Liste_bilan);
        
System.out.println("Temps de decryptage :"+ (System.currentTimeMillis()-startTime)+" ms");
	

		
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
	
	
	
	
	public ObservableList<consultation> Liste_antecedent()
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
			String idConsultString=Medecin_consulterInfoMedExterne1Controller.idConsultStatic; 
			myRs = myStmt.executeQuery("Select * FROM consultationantecedents WHERE idConsultation ='"+idConsultString+"'"); 
			
			
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ObservableList<consultation> infoAntecedent = FXCollections.observableArrayList();
		
        
        try {
			while(myRs.next()){
				
				consultation consultation=new consultation();
				consultation.setObservation( objetAES.decrypt(myRs.getString("antecedent"))); 
				
				infoAntecedent.add(consultation);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        antecedentCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("observation")
				);
        
       
		return infoAntecedent; 
		
	}
	
	
	public ObservableList<consultation> Liste_bilan()
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
			String idConsultString=Medecin_consulterInfoMedExterne1Controller.idConsultStatic; 
			myRs = myStmt.executeQuery("Select * FROM consultationbilans WHERE idConsultation ='"+idConsultString+"'"); 
			
			
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ObservableList<consultation> infoBlian = FXCollections.observableArrayList();
		
        
        try {
			while(myRs.next()){
				
				consultation consultation=new consultation();
				consultation.setObservation( objetAES.decrypt(myRs.getString("analyse"))); 
				consultation.setDiagnostic(objetAES.decrypt( myRs.getString("resultat"))); 
				

				infoBlian.add(consultation);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        analyseCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("observation")
				);
        resultCol.setCellValueFactory(
			    new PropertyValueFactory<String,String>("diagnostic")
				);
        
       
		return infoBlian; 
		
	}

	
	
}

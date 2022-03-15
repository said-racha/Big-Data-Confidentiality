package projetssad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import classes.AES;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;


public class Medecin_inscrireInfoMed1Controller implements Initializable{
	
	@FXML TextField nom_hopital;
    @FXML TextField nom_service;
	
	
	@FXML
    private TextField taille;

    @FXML
    private TextField observation;

    @FXML
    private TextField poids;

    @FXML
    private TextField idP;
    
	@FXML private TableView<String> MaladiesChroniquesTable;
	@FXML private TableColumn<String, String> MaladiesChroniquesCol; //col pr colonne
	private static ArrayList<String> MaladiesChroniquesListe= new ArrayList<String>();
	
	@FXML private TableView<String> BilanTable;
	@FXML private TableColumn<String, String> resultatCol;
	private static ArrayList<String> resultatListe= new ArrayList<String>();
	
    @FXML private TableColumn<String, String> analyseCol;
    private static ArrayList<String> analyseListe= new ArrayList<String>();
	
    
    //*********************************Module de cryptage*********************************
    String mykey ="lv39eptJvuhAq5srTo7mFqiZcuwXq1n0"; //32 caract�res * 8 bits=256 (une cl� peut etre sur 128, 192 et 256 bits)
    SecretKey key = new SecretKeySpec(mykey.getBytes(), "AES");
    AES objetAES=new AES(key);
    
	    
    //************************************************************************************

	
    ObservableList<String> Liste_MaladiesChroniques= null;
    ObservableList<String> Liste_resultat = null;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Liste_MaladiesChroniques= ListeVide();
		MaladiesChroniquesTable.setItems(Liste_MaladiesChroniques);
		MaladiesChroniquesTable.setEditable(true); 
		MaladiesChroniquesCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		Liste_resultat= ListeVide2();
		BilanTable.setItems(Liste_resultat);
		BilanTable.setEditable(true); 
		resultatCol.setCellFactory(TextFieldTableCell.forTableColumn());
		analyseCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
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
    void ajouterConsultation(ActionEvent event) throws IOException, SQLException {
		
		Connection cnx = Main.connection;
		
		
	    String sql="insert into consultation (taille, poids, observation, dateConsult, idPatient) values (?,?,?,?,?)";
	    long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis); 
	    
	    PreparedStatement preparedStmt = cnx.prepareStatement(sql);
	    
long startTime=System.currentTimeMillis();
	    
	    preparedStmt.setString(1, objetAES.encrypt(taille.getText()));
	    preparedStmt.setString(2, objetAES.encrypt(poids.getText()));
	    preparedStmt.setString(3, objetAES.encrypt(observation.getText()));
	    
	    preparedStmt.setString(4, String.valueOf(date));
	    preparedStmt.setString(5, idP.getText());
	    
	    preparedStmt.execute();
	    
	    

	    //ajouter les listes dans la bdd
	    Statement myStmt = cnx.createStatement(); 
        ResultSet myRs = myStmt.executeQuery("SELECT max(idConsultation) FROM consultation");
        String idConsultation, sql2;
        PreparedStatement preparedStmt2;
        
        if(myRs.next()){
        	idConsultation=myRs.getString("max(idConsultation)");
        	
        	for(String mc : MaladiesChroniquesListe) {
        		
        		sql2="insert into consultationantecedents (idConsultation, antecedent) values (?,?)";
        	    
        		preparedStmt2= cnx.prepareStatement(sql2);
        	    
        	    preparedStmt2.setString(1, idConsultation);
        	    preparedStmt2.setString(2, objetAES.encrypt(mc));
        	    
        	    preparedStmt2.execute();
        	}
        	
        	for(int i=0; i<analyseListe.size();i++) {
        		
        		sql2="insert into consultationbilans (idConsultation, analyse, resultat) values (?,?,?)";
        	    
        		preparedStmt2= cnx.prepareStatement(sql2);
        	    
        	    preparedStmt2.setString(1, idConsultation);
        	    preparedStmt2.setString(2, objetAES.encrypt(analyseListe.get(i)));
        	    preparedStmt2.setString(3, objetAES.encrypt(resultatListe.get(i)));
        	    
        	    preparedStmt2.execute();
        	}
        	
        	
        }


 System.out.println("Temps de cryptage :"+ (System.currentTimeMillis()-startTime)+" ms");

	    //message de confirmation
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Alerte");
	    alert.setHeaderText(null);
	    alert.setContentText("Ajout effectuée avec succés !");
	    alert.show();
	    
	    //reinitialiser les variables
	    Liste_MaladiesChroniques= ListeVide(); MaladiesChroniquesTable.setItems(Liste_MaladiesChroniques); MaladiesChroniquesCol.setCellFactory(TextFieldTableCell.forTableColumn());
	    
	    Liste_resultat= ListeVide2(); BilanTable.setItems(Liste_resultat);
	    resultatCol.setCellFactory(TextFieldTableCell.forTableColumn());
		analyseCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
	    
	    MaladiesChroniquesListe= new ArrayList<String>(); 
	    resultatListe= new ArrayList<String>();
	    analyseListe= new ArrayList<String>();
	    
	    taille.setText(""); observation.setText(""); poids.setText(""); idP.setText("");
	    
	    
	}

 	

	
	public void IntroduireMaladiesChroniques(CellEditEvent  edditedCell)
	{
		if(edditedCell.getNewValue().toString() !="")
			MaladiesChroniquesListe.add(edditedCell.getNewValue().toString());
	}
	
	public void IntroduireResultat(CellEditEvent  edditedCell)
	{
		if(edditedCell.getNewValue().toString() !="")
			resultatListe.add(edditedCell.getNewValue().toString());
	}
	
	public void IntroduireAnalyse(CellEditEvent  edditedCell)
	{
		if(edditedCell.getNewValue().toString() !="")
			analyseListe.add(edditedCell.getNewValue().toString());
	}

	
	
	//cr�e une liste vide afin que le medecin puisse la remplir avec les informations qu'il desire
	public ObservableList<String> ListeVide()
	{
		ObservableList<String> listeMaladiesChroniquesVide=null; listeMaladiesChroniquesVide= FXCollections.observableArrayList();
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		
		return listeMaladiesChroniquesVide; 
	}
	
	public ObservableList<String> ListeVide2()
	{
		ObservableList<String> listeMaladiesChroniquesVide=null; listeMaladiesChroniquesVide= FXCollections.observableArrayList();
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		listeMaladiesChroniquesVide.add(new String());
		
		return listeMaladiesChroniquesVide; 
	}
	
	
	
	
	
	
}

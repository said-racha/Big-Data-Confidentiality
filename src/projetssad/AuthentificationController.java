/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetssad;

import classes.AES;
import classes.RSA;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AuthentificationController implements Initializable {

    @FXML TextField email;
    @FXML TextField mdp;
    @FXML DatePicker date_naiss;
    @FXML ComboBox fonction;
    
  //*********************************Module de cryptage*********************************
    String mykey ="lv39eptJvuhAq5srTo7mFqiZcuwXq1n0"; //32 caractères * 8 bits=256 (une clé peut etre sur 128, 192 et 256 bits)
    SecretKey key = new SecretKeySpec(mykey.getBytes(), "AES");
    AES objetAES=new AES(key);
    
	    //"données cryptées :"+objetAES.encrypt(stringToEncrypt)
    
	  	//long startTime=System.currentTimeMillis();
	    //temps cryptage/ou décryptage : (System.currentTimeMillis()-startTime)+" ms"
    
    	//"données décryptées :"+objetAES.decrypt(encrypted_data)
    //************************************************************************************

    
    static int idH_Admin, idH_Secretaire, idS_Secretaire,idH_Medecin, idS_Medecin;
    static String idMedecin;
    
    public void SeConnecter(ActionEvent event) throws SQLException, IOException 
    {
        if( email.getText().isEmpty() || mdp.getText().isEmpty() || date_naiss.getValue()==null || fonction.getValue()==null)
        {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs!!");
                alert.show();
        }else{
                try {
                Connection cnx = Main.connection; 
                Statement myStmt = cnx.createStatement();
                int test=0;
                
                
                //---------------------------------------------Administrateur-------------------------------------------------------
                ResultSet myRs = myStmt.executeQuery("SELECT idHopital, `email`, `mdp`, `datenaiss` FROM `administrateur`"); 
                while (myRs.next())
                {
                    String date ="";
                    if(date_naiss.getValue()!=null){  
                        //Transformer LocalDate to String
                        date = date_naiss.getValue().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd")));
                    }

                    if( (email.getText().equals(myRs.getString("email"))) && (mdp.getText().equals(myRs.getString("mdp"))) && date.equals(myRs.getString("datenaiss")) ) 
                    {
                        test++;
                        if(fonction.getValue().equals("Administrateur"))
                        {
                            idH_Admin = myRs.getInt("idHopital");
                          
                          Main.stage.close();
                          Parent parent = FXMLLoader.load(getClass().getResource("Menu1Admin.fxml"));
                          Scene scene = new Scene(parent);
                          Stage stage = new Stage();
                          stage.setScene(scene);
                          stage.show();
                          stage.setResizable(false);
                          Main.stage = stage;
                          stage.setTitle("Menu"); 
                        }else{
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                           alert.setTitle("Test Connexion");
                           alert.setHeaderText(null);
                           alert.setContentText("Fonction incorrecte!");
                           alert.show(); 
                        }       
                    }
                }  

                //---------------------------------------------Medecin-------------------------------------------------------
               
                ResultSet myRs4 = myStmt.executeQuery("SELECT `email`, `mdp`, `datenaiss`,`idMedecin` FROM `medecin`"); 
                
                int wilaya2,b=0;
                while ((b==0) && (myRs4.next()))
                {
                	idMedecin=myRs4.getString("idMedecin");
                	
                    String date ="";
                    if(date_naiss.getValue()!=null){  
                        //Transformer LocalDate to String
                        date = date_naiss.getValue().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd"))); 
                        
                       
                    }

                    if( (email.getText().equals(myRs4.getString("email"))) && (mdp.getText().equals(objetAES.decrypt(myRs4.getString("mdp")))) && date.equals(myRs4.getString("datenaiss")) ) 
                    {
                    	test++; String email=myRs4.getString("email");
                        if(fonction.getValue().equals("Médecin"))
                        {
                        	ResultSet myRs5 = myStmt.executeQuery("SELECT wilaya, s.idHopital, s.idService FROM medecin s, Hopital h WHERE s.email = '"+email+"' AND s.idHopital = h.idHopital"); 
                            if(myRs5.next()){
                                idH_Medecin = myRs5.getInt("idHopital"); 
                                idS_Medecin = myRs5.getInt("idService"); 
                                wilaya2 = myRs5.getInt("wilaya");

                                //Cas 1 : 1<=Wilaya<=29  
                                //Cas 2 : 30<=Wilaya<=58
                                if(wilaya2<=29){
                                	b=1;
                                    Main.stage.close();
                                    Parent parent = FXMLLoader.load(getClass().getResource("Medecin_acceuil1.fxml"));
                                    Scene scene = new Scene(parent);
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.show();
                                    stage.setResizable(false);
                                    Main.stage = stage;
                                    stage.setTitle("Menu cas1"); 
                                }else{
                                	b=1;
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
                        }
                    } 
                }

                
              //---------------------------------------------Ministere-------------------------------------------------------
                ResultSet myRs6 = myStmt.executeQuery("SELECT `email`, `mdp`, `datenaiss` FROM `ministere`"); 
                int b2=0;
                
                
                while ((b2==0)&& myRs6.next())
                {
                	String date ="";
                    if(date_naiss.getValue()!=null){  
                        //Transformer LocalDate to String
                        date = date_naiss.getValue().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd"))); 
                        
                    }
                    
                    if( (email.getText().equals(myRs6.getString("email"))) && (mdp.getText().equals(myRs6.getString("mdp"))) && date.equals(myRs6.getString("datenaiss"))) 
                    {
                        test++;
                        if(fonction.getValue().equals("Ministère"))
                        {
                          b2=1;
                          Main.stage.close();
                          Parent parent = FXMLLoader.load(getClass().getResource("Ministere_acceuil.fxml"));
                          Scene scene = new Scene(parent);
                          Stage stage = new Stage();
                          stage.setScene(scene);
                          stage.show();
                          stage.setResizable(false);
                          Main.stage = stage;
                          stage.setTitle("Menu"); 
                        }else{
                           b2=1;
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                           alert.setTitle("Test Connexion");
                           alert.setHeaderText(null);
                           alert.setContentText("Fonction incorrecte!");
                           alert.show(); 
                        }       
                    }
                }  
                
                
                
              //---------------------------------------------SECRETAIRE-------------------------------------------------------
                
                ResultSet myRs2 = myStmt.executeQuery("SELECT `email`, `mdp`, `datenaiss` FROM `secretaire`"); 
                int wilaya;
                
                while (myRs2.next())
                {
                    String date ="";
                    if(date_naiss.getValue()!=null){  
                        date = date_naiss.getValue().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd")));
                    }
                    
                    
                    boolean testmdp = false;
                    for (String liste_mdp_sec : Admin_AjouterSecretaireController.liste_mdp_sec) {
                        
                        if (mdp.getText().equals(liste_mdp_sec)) {
                            testmdp = true;
                        }
                    }
                    
                    

                    if( (email.getText().equals(myRs2.getString("email"))) &&  testmdp == true && date.equals(myRs2.getString("datenaiss")) ) 
                    {
                        test++;
                        if(fonction.getValue().equals("Secrétaire"))
                        {
                            ResultSet myRs3 = myStmt.executeQuery("SELECT wilaya, s.idHopital, s.idService FROM Secretaire s, Hopital h WHERE s.email = '"+myRs2.getString("email")+"' AND s.idHopital = h.idHopital"); 
                            if(myRs3.next()){
                                idH_Secretaire = myRs3.getInt("idHopital");
                                idS_Secretaire = myRs3.getInt("idService");
                                wilaya = myRs3.getInt("wilaya");

                                //Cas 1 : 1<=Wilaya<=29  
                                //Cas 2 : 30<=Wilaya<=58
                                if(wilaya<=29){
                                    Main.stage.close();
                                    Parent parent = FXMLLoader.load(getClass().getResource("Menu2Secretaire_cas1.fxml"));
                                    Scene scene = new Scene(parent);
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.show();
                                    stage.setResizable(false);
                                    Main.stage = stage;
                                    stage.setTitle("Menu cas1"); 
                                }else{
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
                            }
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Test Connexion");
                            alert.setHeaderText(null);
                            alert.setContentText("Fonction incorrecte!");
                            alert.show(); 
                        }       
                    }
                }  
                
                
                

                if(test==0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alerte");
                    alert.setHeaderText(null);
                    alert.setContentText("Email, mot de passe ou date de naissance incorrect!");
                    alert.show();
                }
            }catch (Exception e)
            { }            
         }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          fonction.getItems().addAll("Médecin","Secrétaire","Administrateur","Ministère");
    }    
    
}

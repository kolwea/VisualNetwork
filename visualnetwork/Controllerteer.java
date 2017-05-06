/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class Controllerteer {
    
    private FXMLDocumentController main;
    private FXMLSearchDocumentController search;
    
    private ObservableList list;
    
    
    public Controllerteer(FXMLDocumentController a){
        this.main = a;

    }
    
    private void getSearchInfo(){        
        list = main.network.updateList();   
    }
        
        
    public void openSearch(){  
                getSearchInfo();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSearchDocument.fxml"));
                Parent root = loader.load();
                search = loader.getController();
                
               search.setList(list);
               search.setControllerteer(this);
                
                Scene give = new Scene(root,600,400);
                
                Stage stage = new Stage();
                stage.setScene(give);
                stage.show();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        public void openInfo(){      
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Info.fxml"));
                Parent root = loader.load();
                
                Scene give = new Scene(root,600,400);
                
                Stage stage = new Stage();
                stage.setScene(give);
                stage.show();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public void sendSearch(ArrayList<Neuron> a){
        main.searchWith(a);
    }
        
        
}

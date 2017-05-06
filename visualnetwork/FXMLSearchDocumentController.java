/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Kolbe
 */
public class FXMLSearchDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public ListView to;
    @FXML
    public ListView from;
    
    private Controllerteer cona;
    
    private ObservableList list;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setList(ObservableList a){
        to.setItems(a);
        from.setItems(a);
    }
    
    public void setControllerteer(Controllerteer a){
        cona=a;
    }
    
    @FXML
    private void getSearch(){
        Neuron frm = (Neuron)from.getSelectionModel().getSelectedItem();
        Neuron too = (Neuron)to.getSelectionModel().getSelectedItem();
        if(frm != null && too != null){
            System.out.println(frm.toString() + " " + too.toString());
            ArrayList me = new ArrayList();
            me.add(0,(Neuron)from.getSelectionModel().getSelectedItem());
            me.add(1,(Neuron)to.getSelectionModel().getSelectedItem());
            sendSearch(me);
        }
    }
    
    private void sendSearch(ArrayList<Neuron> a){
        cona.sendSearch(a);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Kolbe
 */
public class Graph{

    AnchorPane vizPane;
    
    Graph(){
        
    }
    
    public void setPane(AnchorPane pane){
        vizPane = pane;
    }
    
    public AnchorPane getPane(){
        return vizPane;
    }

   
}

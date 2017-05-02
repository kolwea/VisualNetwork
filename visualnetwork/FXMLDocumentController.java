/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private AnchorPane vizPane;
    
    @FXML
    private MenuBar menuBar;
    
    private Menu loadNet;
    
    private NeuralNetwork network;
    
    private Stage stage;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        network = new NeuralNetwork(vizPane);
        network.createFake();
        network.setup();
//        tryout.draw();
//        tryout.search("Red");
    }
    
    @FXML
    private void handleButton2(ActionEvent event) {
        System.out.println("You clicked me!");
//        network.search("Red");
        network.searchPaths("Sun", "Color");
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    @FXML
    private void save(Graph g){
        network.setStage(stage);
        network.saveGraph();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        menuBar = new MenuBar();
        loadNet = new Menu("Menu");
        network = new NeuralNetwork(vizPane);
        
        MenuItem test = new MenuItem("Load");
        MenuItem showPaths = new MenuItem("Search");
        MenuItem save = new MenuItem("Save");
        MenuItem open = new MenuItem("Open");

        loadNet.getItems().addAll(test,showPaths,save,open);
       
        
        test.setOnAction((ActionEvent event)-> {
            network.createFake();
        });
        
        showPaths.setOnAction((ActionEvent event) -> {
            network.searchPaths("Sun", "Color");
        });
        
        save.setOnAction((ActionEvent event) -> {
            network.saveGraph();
        });
        
        open.setOnAction((ActionEvent event) -> {
            network.openGraph();
        });
        
        menuBar.getMenus().add(loadNet);
        
        vizPane.getChildren().add(menuBar);
        
 
    }    

    
    
    
}

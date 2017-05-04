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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Kolbe
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private AnchorPane window;
    
    @FXML
    private AnchorPane vizPane;
    
    
    private AnchorPane currentMenuPane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane networkPane;
    @FXML
    private AnchorPane settingsPane;
    
    
    
    @FXML
    private Text networkLabel;
    
    private MenuBar menuBar;
    
    private NeuralNetwork network;
    
    private Stage stage;
    
    private boolean menuOpen;
  
    @FXML
    private void handleButton2(ActionEvent event) {
        toggleStage();
    }
    
    @FXML
    private void gotoNetworkPane(ActionEvent event){
    }
    
    private void toggleStage(){        
        if(menuOpen){
            settingsPane.setMinSize(window.getWidth()/4, window.getHeight());
            vizPane.setMinSize(window.getWidth()/4 * 3, window.getHeight());
        }
        else{
            settingsPane.setMinSize(0, window.getHeight());
            vizPane.setMinSize(window.getWidth(), window.getHeight());
        }
        menuOpen = !menuOpen;
    }
    
    
    private void goToNetworkPane(){
        networkPane.setMinWidth(settingsPane.getWidth());
        networkPane.setMinHeight(settingsPane.getHeight());
        settingsPane = networkPane;
    }
        
    
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        menuOpen = true;
        menuBar = new MenuBar();
        Menu loadNet = new Menu("File");
        network = new NeuralNetwork(vizPane);
        network.setLabel(networkLabel);
        
        MenuItem test = new MenuItem("Load Test ");
        MenuItem showPaths = new MenuItem("Show Path");
        MenuItem search = new MenuItem("Search");
        MenuItem save = new MenuItem("Save Network");
        MenuItem open = new MenuItem("Open Network");
        MenuItem saveN = new MenuItem("Save Neural Network");
        MenuItem openN = new MenuItem("Open Neural Network");

        loadNet.getItems().addAll(test,showPaths,search,save,open,saveN,openN);
       
        
        test.setOnAction((ActionEvent event)-> {
            network.createFake();
        });        
        
        search.setOnAction((ActionEvent event)-> {
            network.searchPaths(network.networkArray.get(0),"Forest","Color");
        });
        
        showPaths.setOnAction((ActionEvent event) -> {
//            network.searchPaths("Sun", "Color");
                network.showConex();
        });
        
        save.setOnAction((ActionEvent event) -> {
//            network.saveGraph();
        });
        
        open.setOnAction((ActionEvent event) -> {
            network.openGraph();
        });
        
        menuBar.getMenus().add(loadNet);
        
        settingsPane.getChildren().add(menuBar);
        
    }    
    
    

    
    
    
}

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane networkPane;
    @FXML
    private AnchorPane settingsPane;
    @FXML
    private VBox nodeList;
    @FXML 
    private VBox settingsBox;

            
    
    
    
    @FXML
    private Text networkLabel;
    
    private MenuBar menuBar;
    
    public NeuralNetwork network;
    
    private Stage stage;
    
    private boolean menuOpen;
    
    private ListView list;
  
    @FXML
    private void handleButton2(ActionEvent event) {
        toggleStage();
    }
    
    @FXML
    private void gotoNetworkPane(ActionEvent event){
    }
    
    private void toggleStage(){        
        if(menuOpen){
            settingsPane.setPrefSize(window.getWidth()/4, window.getHeight());
            vizPane.setPrefSize(window.getWidth()/4 * 3, window.getHeight());
        }
        else{
            settingsPane.setPrefSize(0, window.getHeight());
            vizPane.setPrefSize(window.getWidth(), window.getHeight());
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
//        network = new NeuralNetwork(vizPane);
        
        setupMenu();

        
    }    
    
    private void setupMenu(){
        menuOpen = false;
        
        //Create Menu
        menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(settingsPane.widthProperty());
        Menu fileM = new Menu("File");
        Menu networkM = new Menu("Network");
        Menu graphM = new Menu("View");
                    
        MenuItem create = new MenuItem("New");
        MenuItem save = new MenuItem("Save Graph");
        MenuItem open = new MenuItem("Open Graph");
        MenuItem saveN = new MenuItem("Save Network");
        MenuItem openN = new MenuItem("Open Network");
        fileM.getItems().addAll(create,save,open,saveN,openN);
        
        
        MenuItem add = new MenuItem("Add Node");
        MenuItem delete = new MenuItem("Delete Node");
        MenuItem search = new MenuItem("Search");
        MenuItem edit = new MenuItem("Edit Node");
        networkM.getItems().addAll(add,delete,search,edit);
        
        MenuItem info = new MenuItem("Info");
        graphM.getItems().add(info);
        
                
        create.setOnAction((ActionEvent event)-> {
            if(network == null)
                network = new NeuralNetwork(vizPane);
            network.createFake();
            //updateList();
        });   
        
        search.setOnAction((ActionEvent event)-> {            
            openSearch();
//            network.searchPaths(network.networkArray.get(0),"Forest","Color");
        });
        
        save.setOnAction((ActionEvent event) ->{
            if(network!=null)
            network.saveGraph(network.networkArray.get(0));
        });
        
        open.setOnAction((ActionEvent event) ->{
            if(network == null)
                network = new NeuralNetwork(vizPane);
            network.openGraph();
        });
        
        info.setOnAction((ActionEvent event)->{ 
            openInfo();
        });
        
        menuBar.getMenus().addAll(fileM,networkM,graphM);
        
        settingsPane.getChildren().add(menuBar);       
        
    }
    
    private void setupPanes(){
        settingsBox.setPrefWidth(settingsPane.getWidth()/4 * 3);
        settingsBox.setAlignment(Pos.CENTER);
        nodeList.setPrefWidth(settingsBox.getWidth()/4 *3);
       
    }
    
    private void updateList(){
        ObservableList hold = network.updateList();
        list = new ListView(hold);
        list.setPrefSize(200, 150);
        if(!nodeList.getChildren().contains(list))
            nodeList.getChildren().add(list);
    }
    
    public ObservableList<Neuron> getList(){
        return list.getItems();
    }
    
    public void openSearch(){
        if(network != null){
        Controllerteer hold = new Controllerteer(this);
        hold.openSearch();
        }
    }
    
    public void searchWith(ArrayList<Neuron> a){
        network.searchPaths(network.networkArray.get(0), a.get(0) , a.get(1));
    }
    
    public void openInfo(){
        Controllerteer hold = new Controllerteer(this);
        hold.openInfo();
    }
   
    
}

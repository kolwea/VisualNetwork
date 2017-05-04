/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class NeuralNetwork {
    private AnchorPane vizPane;
    private KeyFrame keyframe;
    private Timeline timeline;
    private Stage stage;
    private Text label;
    private int index;
    public ArrayList<Network> networkArray;
    
    public NeuralNetwork(AnchorPane pane){
        vizPane = pane;
        networkArray = new ArrayList();
        setup();
    }
    
    public void setPane(AnchorPane pane){
        vizPane = pane;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void setLabel(Text label){
        this.label = label;
    }
    
    public void createFake(){
        Network hold;
        hold = new Network("Test Graph");
        hold.setPane(vizPane);
        hold.createFake();
        this.addNetwork(hold);
        label.setText(hold.getName());
        hold.connectFake();
    }
    
    public void createNetwork(String text){
        Network hold = new Network(text);
        hold.setPane(vizPane);
        this.addNetwork(hold);
        label.setText(hold.getName());
    }
    
    public void setup(){
        setupTimeline();
        timeline.play();
    }
    
    public void search(Network network, String a){
        Query hold = new Query();
        hold.setGraph(network);
        hold.DFS(network.getNeuron(a));
    }
    
    public void searchPaths(Network network, String a, String b){
        Query hold = new Query();
        hold.setGraph(network);
        hold.DFSpath(network.getNeuron(a),network.getNeuron(b));
        hold.showSavedPaths();
    }

    private void setupTimeline(){
        keyframe = new KeyFrame(Duration.millis(100), (ActionEvent event) -> {
            for(Network network : networkArray)
                network.update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    private void addNetwork(Network addMe){
        networkArray.add(index,addMe);
        addNeuronsToScene(addMe);
        index++;
    }
    
    private void addNeuronsToScene(Network network){
        if(network == null)
            return;
        for(Neuron a : network.getVertices()){
            vizPane.getChildren().add(a.getBody());
        }
    }
    
    private void addConexToScene(Network network){
        if(network == null)
            return;
        for(Connection a : network.getConexs()){
            vizPane.getChildren().add(a.getBody());
        }
    }
    
    public void saveGraph(Network network){
        SaveGraph saveGraph = new SaveGraph();
        saveGraph.setStage(stage);
        saveGraph.save(network, network.getName());
    }
    
    public void openGraph(){
        OpenGraph openGraph = new OpenGraph();
        openGraph.setStage(stage);
        System.out.println("Stage is set!");
        Network hold = openGraph.open();
        if(hold != null){
            hold.setPane(vizPane);
            hold.initializeNeurons();
            System.out.println("Done loading!");
            addNetwork(hold);
        }
        else{
            System.out.println("Graph not opened!");
        }
    }
    
    public void showConex(){
        networkArray.get(0).createHeadConex((Particle)networkArray.get(0).getVertices().get(0), (Particle)networkArray.get(0).getVertices().get(1));
        networkArray.get(0).createHeadConex((Particle)networkArray.get(0).getVertices().get(1), (Particle)networkArray.get(0).getVertices().get(2));
    }

}

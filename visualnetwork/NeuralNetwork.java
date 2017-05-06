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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
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
    private int index;
    public ArrayList<Network> networkArray;
    private ObservableList<Neuron> nodes;
    
    public Double nodeSpeed = 0.2;
    
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
    
    public void createFake(){
        Network hold;
        hold = new Network("Test Graph");
        hold.setPane(vizPane);
        hold.createFake();
        this.addNetwork(hold);
        hold.connectAll();
        updateList();
    }
    
    public void createNetwork(String text){
        Network hold = new Network(text);
        hold.setPane(vizPane);
        this.addNetwork(hold);
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
    
    public void searchPaths(Network network, Neuron a, Neuron b){
        Query hold = new Query();
        hold.setGraph(network);
        hold.DFSpath(a,b);
        hold.showSavedPaths();
    }

    private void setupTimeline(){
        keyframe = new KeyFrame(Duration.millis(1), (ActionEvent event) -> {
            for(Network network : networkArray)
                network.update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    private void addNetwork(Network addMe){
        networkArray.add(index,addMe);
        setupPos(addMe);
        addNeuronsToScene(addMe);
        index++;
    }
    
    private void addNeuronsToScene(Network network){
        for (Neuron a : network.getVertices()) {
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
        
        Network hold = openGraph.open();
        if(hold != null){
            hold.setPane(vizPane);
            hold.reinitializeNeurons();
            addNetwork(hold);            
            System.out.println("Done loading!");
        }
        else{
            System.out.println("Graph not opened!");
        }
    }
    
    public ObservableList updateList(){
        if(nodes == null)
            nodes = FXCollections.observableArrayList();
        ArrayList<Neuron> hold = new ArrayList();
        for(Network a : networkArray){
            for(Neuron b : a.getVertices()){
                if(nodes.contains(b));
                else{
                    nodes.add(b);
                }
            }
        }
        return nodes;
    }
    
    private void setupPos(Network hold){
        ArrayList<Neuron> list = hold.getVertices();
        for(Neuron abe : list){
            Vector pos = new Vector(getRandomX(abe),getRandomY(abe));
            Vector vel = new Vector(Math.random() * nodeSpeed, Math.random() * nodeSpeed);
            abe.setPos(pos, vel);
        }
    }
    
    private double getRandomX(Neuron a){
        Double radius = a.getBody().getRadiusX();
        double width = vizPane.getWidth();
        double randomNum = Math.random()*width;
        if(randomNum < 0 + radius || randomNum > width - radius)
            randomNum = getRandomX(a);
        return randomNum;
    }
    
    private double getRandomY(Neuron a){
        Double radius = a.getBody().getRadiusX();
        double height =  vizPane.getHeight();
        double randomNum = Math.random()*height;
        if(randomNum < 0 + radius || randomNum > height - radius)
            randomNum = getRandomY(a);
        return randomNum;
    }


}

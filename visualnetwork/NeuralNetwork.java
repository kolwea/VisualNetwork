/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;
import static jdk.nashorn.internal.objects.NativeMath.max;

/**
 *
 * @author Kolbe
 */
public class NeuralNetwork {
    private AnchorPane vizPane;
    private KeyFrame keyframe;
    private Timeline timeline;
    private Graph network;
    private Stage stage;
    
    public NeuralNetwork(AnchorPane pane){
        vizPane = pane;
    }
    
    public void setPane(AnchorPane pane){
        vizPane = pane;
    }
    
    public void setGraph(Graph graph){
        network = graph;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void createFake(){
        if(network == null)
            network = new Graph("Test");
        
        network.addNeuron(new Neuron("Sun",vizPane));
        network.addNeuron(new Neuron("Red",vizPane));
        network.addNeuron(new Neuron("Green",vizPane));
        network.addNeuron(new Neuron("Large",vizPane));
        network.addNeuron(new Neuron("Medium",vizPane));
        network.addNeuron(new Neuron("Size",vizPane));
        network.addNeuron(new Neuron("Small",vizPane));
        network.addNeuron(new Neuron("Color",vizPane));
        network.addNeuron(new Neuron("Temp",vizPane));
        network.addNeuron(new Neuron("Hot",vizPane));
        network.addNeuron(new Neuron("Brown",vizPane));
        network.addNeuron(new Neuron("Cold",vizPane));
        network.addNeuron(new Neuron("Moon",vizPane));
        network.addNeuron(new Neuron("House",vizPane));
        network.addNeuron(new Neuron("Lamp",vizPane));
        network.addNeuron(new Neuron("Grey",vizPane));
        network.addNeuron(new Neuron("Purple",vizPane));
        network.addNeuron(new Neuron("Pink",vizPane));
        network.addNeuron(new Neuron("Desk",vizPane));
        network.addNeuron(new Neuron("Wood",vizPane));
        network.addNeuron(new Neuron("Drawer",vizPane));
        
        network.addEdge("Sun", "Red");
        network.addEdge("Sun", "Large");
        network.addEdge("Sun", "Hot");
        network.addEdge("Sun", "Fake");
        network.addEdgeDub("Size", "Large");
        network.addEdgeDub("Color", "Red");
        network.addEdgeDub("Color", "Green");
        network.addEdgeDub("Color", "Brown");
        network.addEdgeDub("Color", "Brown");
        
        setup();
           
//        Sun.add(Red);
//        Sun.add(Large);
//        Sun.add(Hot);
//        Size.add(Large);
//        Color.add(Red);
//        Color.add(Brown);
//        Color.add(Green);
//        Green.add(Color);
//        Red.add(Color);
//        Temp.add(Hot);
//        Temp.add(Cold);
//        Hot.add(Temp);
//        Cold.add(Temp);
//        Size.add(Small);
//        Small.add(Size);
//        Large.add(Size);
//        Size.add(Medium);
//        Medium.add(Size);
    }
    
    public void setup(){
        setupNetwork();
        setupTimeline();
        timeline.play();
    }
    private void setupNetwork(){
        if(network == null){
            System.out.println("Network hasn't been initialized. Please choose a neural graph to use.");
            return;
        }
        for(Neuron a : network.getVertices()){
            vizPane.getChildren().add(a.getBody());
        }
    }
    
    public void search(String a){
        Query hold = new Query();
        hold.setGraph(network);
        hold.DFS(network.getNeuron(a));
    }
    public void searchPaths(String a, String b){
        Query hold = new Query();
        hold.setGraph(network);
        hold.DFSpath(network.getNeuron(a),network.getNeuron(b));
        hold.showSavedPaths();
    }

    
    private void setupTimeline(){
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            if(network == null)
                System.out.println("This null nigga");
            network.update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public void saveGraph(){
        SaveGraph saveGraph = new SaveGraph();
        saveGraph.setStage(stage);
        saveGraph.save(network, network.getName());
    }
    
    public void openGraph(){
        OpenGraph openGraph = new OpenGraph();
        openGraph.setStage(stage);
        System.out.println("Stage is set!");
        this.network = openGraph.open();
        System.out.println("Done loading!");
        for(Neuron a: network.getVertices()){
            System.out.println(a.getName());
            a.setup(vizPane);
        }
        setup();
    }

}

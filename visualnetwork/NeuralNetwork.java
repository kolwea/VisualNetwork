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
            network = new Graph("TestGraph", vizPane);
        network.createFake();
    }
    
    public void createGraph(String text){
        network = new Graph(text, vizPane);
    }
    
    public void setup(){
        setupTimeline();
        timeline.play();
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
        Graph hold = openGraph.open();
        if(hold != null){
            network = hold;
            for(Neuron a: network.getVertices()){
                a.setup(vizPane);
            }
            setup();
            System.out.println("Done loading!");
        }
        else{
            System.out.println("Graph not opened");
        }
    }
    

    

}

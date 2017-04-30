/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import static jdk.nashorn.internal.objects.NativeMath.max;

/**
 *
 * @author Kolbe
 */
public class NeuralNetwork {
    private AnchorPane vizPane;
    private Graph network;
    
    public NeuralNetwork(){
    }
    
    public void setPane(AnchorPane pane){
        vizPane = pane;
    }
    
    public void setGraph(Graph graph){
        network = graph;
    }
    
    public void createFake(){
        if(network == null)
            network = new Graph();
        
        network.addNeuron(new Neuron("Sun"));
        network.addNeuron(new Neuron("Red"));
        network.addNeuron(new Neuron("Green"));
        network.addNeuron(new Neuron("Large"));
        network.addNeuron(new Neuron("Medium"));
        network.addNeuron(new Neuron("Size"));
        network.addNeuron(new Neuron("Small"));
        network.addNeuron(new Neuron("Color"));
        network.addNeuron(new Neuron("Temp"));
        network.addNeuron(new Neuron("Hot"));
        network.addNeuron(new Neuron("Brown"));
        network.addNeuron(new Neuron("Cold"));
        network.addNeuron(new Neuron("Moon"));
        network.addNeuron(new Neuron("House"));
        network.addNeuron(new Neuron("Lamp"));
        network.addNeuron(new Neuron("Grey"));
        network.addNeuron(new Neuron("Purple"));
        network.addNeuron(new Neuron("Pink"));
        network.addNeuron(new Neuron("Desk"));
        network.addNeuron(new Neuron("Wood"));
        network.addNeuron(new Neuron("Drawer"));
        
        network.addEdge("Sun", "Red");
        network.addEdge("Sun", "Large");
        network.addEdge("Sun", "Hot");
        network.addEdge("Sun", "Fake");
        network.addEdge("Size", "Large");
        network.addEdge("Color", "Red");
        network.addEdge("Color", "Green");
        network.addEdge("Color", "Brown");
           
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
    
    public void draw(){
        ArrayList<Neuron> neurons = network.getVertices();
        for(Neuron a : neurons){
            Ellipse hold = new Ellipse();
            hold.setRadiusX(30);
            hold.setRadiusY(30);
            hold.setFill(Color.BURLYWOOD);
            hold.setCenterX(getRandomX());
            hold.setCenterY(getRandomY());
            vizPane.getChildren().add(hold);
        }
    }
    
    private double getRandomX(){
        double width = vizPane.getWidth();
        double randomNum = Math.random()*width;
        System.out.print("X: " + randomNum);
        return randomNum;
    }
    
    private double getRandomY(){
        double height =  vizPane.getHeight();
        double randomNum = Math.random()*height;
        System.out.println(" Y: " + randomNum);
        return randomNum;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Kolbe
 */
public class Graph implements java.io.Serializable{
    private ArrayList<Neuron> vertices ;
    private HashMap<Neuron,Edge> edges;
    private static int index = 0;
    private String name;
    private AnchorPane vizPane;
    private ArrayList<Ellipse> bodies;
    
    public Graph(String name, AnchorPane pane){
        this.name = name;
        vizPane = pane;
        vertices = new ArrayList();
        edges = new HashMap();
        bodies = new ArrayList();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void addEdge(String name, String add){      
        int indy = getIndex(name);
        int indyAdd = getIndex(add);
        if(indy == -1 || indyAdd == -1)
            System.out.println("Can not add edge since a neuron does not exist");
        else{
            if(edges == null)
                edges = new HashMap();
            
            Neuron neuron = vertices.get(indy);
            Neuron neuron2 = vertices.get(indyAdd);
            
            Edge iter = edges.get(neuron);
            Edge addMe = new Edge(neuron2); 
            Edge parent = null;
            
            if(iter == null){
                edges.put(neuron, addMe);
            }
            else{
                while(iter != null){
                    if(iter.neuron == addMe.neuron){
                        System.out.println("Neuron already present in adjacency list");
                        return;
                    }
                    parent = iter;
                    iter = iter.next;
                }
                parent.next = addMe;       
                addMe.parent = parent;
            }
        }
    }
    
    public void addEdgeDub(String name, String name2){
        addEdge(name, name2);
        addEdge(name2, name);
    }
    
    public void addNeuron(Neuron neuron){
        if(vertices == null)
            vertices = new ArrayList();
        neuron.setIndex(index);
        vertices.add(index,neuron);
        bodies.add(neuron.getBody());
//        System.out.println("Adding neuron " + neuron.getName());
        bumpIndex();
    }
   
    public ArrayList<Neuron> getVertices(){
        return vertices;
    }
    
    private int getIndex(String name){
        int returnMe = -1;
        for(Neuron a : vertices){
            if(a.getName().compareTo(name) == 0)
                returnMe = a.getIndex();
        }
        return returnMe;
    }
    
    public static void bumpIndex(){
//        System.out.println("Index: " + index);
        index++;
    }
    
    public Edge getAdjHead(Neuron a){
        if(edges == null){
            System.out.println("Edges has not been defined");
            return null;
        }
        else{
            return edges.get(a);
        }
    }
    
    public Neuron getNeuron(String find){
        Neuron returnMe = null;
        for(Neuron a : vertices){
            if(a.getName().compareTo(find) == 0)
                returnMe = a;
        }
        System.out.println("Found it!");
        return returnMe;
    }
    
    public ArrayList<Ellipse> getBodies(){
        return bodies;
    }
    
    public void update(){
        for(Neuron a : vertices){
            a.update();
        }
    }
    
    public ArrayList<Neuron> getAdjList(Neuron a){
        int index = 0;
        Edge head = this.getAdjHead(a);
        ArrayList<Neuron> returnMe = new ArrayList();
        
        while(head != null){
            returnMe.add(index, head.neuron);
            head = head.next;
            index++;
        }
        return returnMe;
        
    }
    
    private void setupNetwork(){
        vizPane.getChildren().addAll(getBodies());
    }
   
    //Create test graph;
    public void createFake(){
        
        addNeuron(new Neuron("Tree",vizPane));
        addNeuron(new Neuron("Wood",vizPane));
        addNeuron(new Neuron("Leaf",vizPane));
        addNeuron(new Neuron("Branch",vizPane));
        addNeuron(new Neuron("Trunk",vizPane));
        addNeuron(new Neuron("Grass",vizPane));
        addNeuron(new Neuron("Forest",vizPane));
        addNeuron(new Neuron("Green",vizPane));
        addNeuron(new Neuron("Yellow",vizPane));
        addNeuron(new Neuron("Orange",vizPane));
        addNeuron(new Neuron("Brown",vizPane));
        addNeuron(new Neuron("Bird",vizPane));
        addNeuron(new Neuron("Raven",vizPane));
        addNeuron(new Neuron("Sparrow",vizPane));
        addNeuron(new Neuron("Flower",vizPane));
        addNeuron(new Neuron("Rose",vizPane));
        addNeuron(new Neuron("Tulip",vizPane));
        addNeuron(new Neuron("Dandelion",vizPane));
        addNeuron(new Neuron("Deer",vizPane));
        addNeuron(new Neuron("Wood",vizPane));
        addNeuron(new Neuron("Drawer",vizPane));
        
        setupNetwork();
        
//        network.addEdgeDub("Sun", "Red");
//        network.addEdgeDub("Sun", "Large");
//        network.addEdgeDub("Sun", "Hot");
//        network.addEdgeDub("Size", "Large");
//        network.addEdgeDub("Color", "Red");
//        network.addEdgeDub("Color", "Green");
//        network.addEdgeDub("Color", "Brown");
//        network.addEdgeDub("", name2);
           
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
   
}

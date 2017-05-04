/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author Kolbe
 */
public class Network extends Graph implements java.io.Serializable{
    
        
    private ArrayList<Neuron> vertices ;
    private HashMap<Neuron,Edge> edges;
    private ArrayList<Connection> conex;
    private static int index = 0;
    private String name;
    private ArrayList<Connection> queue;
    
    public Network(String name) {
        createMe(name);
    }
    
    public void createMe(String name){
        this.name = name;
        vertices = new ArrayList();
        edges = new HashMap();
        conex = new ArrayList();
        queue = new ArrayList();
    }
   
    //Create test graph;
    public void createFake(){
        
        addNewNeuron(new Neuron("Tree"));
        addNewNeuron(new Neuron("Wood"));
        addNewNeuron(new Neuron("Leaf"));
        addNewNeuron(new Neuron("Branch"));
        addNewNeuron(new Neuron("Trunk"));
        addNewNeuron(new Neuron("Grass"));
        addNewNeuron(new Neuron("Forest"));
        addNewNeuron(new Neuron("Green"));
        addNewNeuron(new Neuron("Yellow"));
        addNewNeuron(new Neuron("Orange"));
        addNewNeuron(new Neuron("Brown"));
        addNewNeuron(new Neuron("Bird"));
        addNewNeuron(new Neuron("Raven"));
        addNewNeuron(new Neuron("Sparrow"));
        addNewNeuron(new Neuron("Flower"));
        addNewNeuron(new Neuron("Rose"));
        addNewNeuron(new Neuron("Tulip"));
        addNewNeuron(new Neuron("Dandelion"));
        addNewNeuron(new Neuron("Deer"));
        addNewNeuron(new Neuron("Wood"));
        addNewNeuron(new Neuron("Drawer"));
        addNewNeuron(new Neuron("Color"));
        
        addNewNeuron(new Neuron("Spruce"));
        addNewNeuron(new Neuron("Willow"));
        addNewNeuron(new Neuron("Oak")); 
        addNewNeuron(new Neuron("Birch"));
        addNewNeuron(new Neuron("Sequoi"));                
        addNewNeuron(new Neuron("Plant"));
        
                
        addNewNeuron(new Neuron("Animal"));
        addNewNeuron(new Neuron("Wolf"));
        addNewNeuron(new Neuron("Boar"));
        addNewNeuron(new Neuron("Squirrel"));
        
//        addNewNeuron(new Neuron("Brown"));
//        addNewNeuron(new Neuron("Pink"));
//        addNewNeuron(new Neuron("Blue"));


    }
    
    public void connectFake(){
                
        addEdgeDub("Wood", "Tree");
        addEdgeDub("Leaf", "Tree");
        addEdgeDub("Trunk", "Tree");
        addEdgeDub("Forest", "Tree");
        addEdgeDub("Leaf", "Green");
        addEdgeDub("Leaf", "Yellow");
        addEdgeDub("Color", "Green");
        addEdgeDub("Color", "Yellow");
        
        addEdgeDub("Tree","Spruce");
        addEdgeDub("Tree","Oak");
        addEdgeDub("Tree", "Plant");
        addEdgeDub("Grass", "Plant");
        addEdgeDub("Animal", "Wolf");
        addEdgeDub("Animal", "Boar");
        addEdgeDub("Animal", "Squirrel");
        
        
    }
    
    public void update(){
        for(Neuron a : vertices){
            a.update();
//            Edge hold = getAdjHead(a);
//            while(hold != null){
//                
//            }
        }
        for(int i = 0; i<1 ; i++){
            for(Connection cone : conex){
                cone.update();
            }
        }        
        for(Connection q : queue){
            if(conex.contains(q));
            else{
                conex.add(q);
            }
        } 
        queue = new ArrayList();
    }
    
    public void createConex(Connection cool){
//        Connection cool = new Connection(a,b);
        cool.setNetwork(this);
        cool.setPane(vizPane);
        queue.add(cool);
        vizPane.getChildren().add(0,cool.getBody());
    }
    
    public void createHeadConex(Particle a, Particle b){
        Connection cool = new Connection(a,b);
        cool.setNetwork(this);
        cool.setPane(vizPane);
        cool.setIndex(0);
        queue.add(cool);
        vizPane.getChildren().add(0,cool.getBody());
    }
    
    public ArrayList<Connection> getConexs(){
        return conex;
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
            Neuron neuron = vertices.get(indy);
            Neuron neuron2 = vertices.get(indyAdd);
            
            Edge iter = edges.get(neuron);
            Edge addMe = new Edge(neuron2); 
            Edge parent = null;
            
            if(iter == null){
                edges.put(neuron, addMe);
                neuron.connect(neuron2.getBody());
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
                neuron.connect(neuron2.getBody());
            }
        }
    }
    
    public void addEdgeDub(String name, String name2){
        addEdge(name, name2);
        addEdge(name2, name);
    }
    
    public void addNewNeuron(Neuron neuron){
        neuron.setIndex(index);
        neuron.setPane(this.getPane());
        neuron.initial();
        vertices.add(index,neuron);
        bumpIndex();
//        System.out.println("Neuron added to" + name);
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
        
    private double getRandomX(){
        double width = this.getPane().getWidth();
        double randomNum = Math.random()*width;
//        System.out.print("X: " + randomNum);
        return randomNum;
    }
    
    private double getRandomY(){
        double height =  this.getPane().getHeight();
        double randomNum = Math.random()*height;
//        System.out.println(" Y: " + randomNum);
        return randomNum;
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
    
    public void initializeNeurons(){
        for(Neuron a : vertices){
            a.setPane(this.getPane());
            a.initial();
            a.setNewPos();
        }
    }
    
}

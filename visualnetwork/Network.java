/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Kolbe
 */
public class Network extends Graph implements java.io.Serializable{
    
        
    private ArrayList<Neuron> vertices ;
    private HashMap<Neuron,Edge> edges;
    private static int index = 0;
    private String name;
    
    public Network(String name) {        
        this.name = name;
        vertices = new ArrayList();
        edges = new HashMap();
    }
    
    //Create test graph;
    public void createFake(){

        addNewNeuron(new Neuron("A"));
        addNewNeuron(new Neuron("B"));
        addNewNeuron(new Neuron("C"));
        addNewNeuron(new Neuron("D"));
        addNewNeuron(new Neuron("E"));
        addNewNeuron(new Neuron("F"));
        addNewNeuron(new Neuron("G"));        
        addNewNeuron(new Neuron("H"));
        addNewNeuron(new Neuron("I"));
        addNewNeuron(new Neuron("J"));
        addNewNeuron(new Neuron("K"));
        addNewNeuron(new Neuron("L"));
        addNewNeuron(new Neuron("M"));
        addNewNeuron(new Neuron("N"));
        addNewNeuron(new Neuron("O"));
        addNewNeuron(new Neuron("P"));
        addNewNeuron(new Neuron("Q"));
        addNewNeuron(new Neuron("R"));
        addNewNeuron(new Neuron("S"));
        addNewNeuron(new Neuron("T"));
        addNewNeuron(new Neuron("U"));        
        addNewNeuron(new Neuron("V"));
        addNewNeuron(new Neuron("W"));
        addNewNeuron(new Neuron("X"));
        addNewNeuron(new Neuron("Y"));
        addNewNeuron(new Neuron("Z"));
                
        addNewNeuron(new Neuron("A1"));
        addNewNeuron(new Neuron("B1"));
        addNewNeuron(new Neuron("C1"));
        addNewNeuron(new Neuron("D1"));
        addNewNeuron(new Neuron("E1"));
        addNewNeuron(new Neuron("F1"));
        addNewNeuron(new Neuron("G1"));        
        addNewNeuron(new Neuron("H1"));
        addNewNeuron(new Neuron("I1"));
        addNewNeuron(new Neuron("J1"));
        addNewNeuron(new Neuron("K1"));
        addNewNeuron(new Neuron("L1"));
        addNewNeuron(new Neuron("M1"));
        addNewNeuron(new Neuron("N1"));
        addNewNeuron(new Neuron("O1"));
        addNewNeuron(new Neuron("P1"));
        addNewNeuron(new Neuron("Q1"));
        addNewNeuron(new Neuron("R1"));
        addNewNeuron(new Neuron("S1"));
        addNewNeuron(new Neuron("T1"));
        addNewNeuron(new Neuron("U1"));        
        addNewNeuron(new Neuron("V1"));
        addNewNeuron(new Neuron("W1"));
        addNewNeuron(new Neuron("X1"));
        addNewNeuron(new Neuron("Y1"));
        addNewNeuron(new Neuron("Z1"));
    }

    /**
     *Connects all vertices currently in the network
     */
    public void connectAll(){
        for(Neuron a : vertices){
            for(Neuron b : vertices.subList(a.getIndex()+1, vertices.size())){
                createDubConnection(a,b);
            }
        }
    }
    
    //Updates all the nodes in this network
    public void update(){
        for(Neuron a : vertices){
            a.update(vizPane.getWidth(),vizPane.getHeight());
            a.updateLines(this.getAdjHead(a));
        }
    }
    
    /**
     *
     * @param Name
     * @param add
     */
    public void createConnection(Neuron base, Neuron add){      
        Edge iter = edges.get(base);
        Edge addMe = new Edge(add);
        Edge parent = null;
        
        if(iter == null)
            edges.put(base, addMe);
        while(iter != null){        
            if(iter.neuron == addMe.neuron){
                System.out.println("Neuron already present in adjacency list");        
                return;
            }        
            parent = iter;
            iter = iter.next;
        }
        if(parent != null)
            parent.next = addMe;       
        addMe.parent = parent;                
        base.connect(add.getBody(),addMe);       
    }
    
    public void createDubConnection(Neuron base, Neuron add){
        createConnection(base, add);
        createConnection(add, base);
    }
    
    public void addNewNeuron(Neuron neuron){
        neuron.setIndex(index);
        vertices.add(index,neuron);
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
        return randomNum;
    }
    
    private double getRandomY(){
        double height =  this.getPane().getHeight();
        double randomNum = Math.random()*height;
        return randomNum;
    }
    
    public ArrayList<Neuron> getAdjList(Neuron a){
        Edge head = this.getAdjHead(a);
        ArrayList<Neuron> returnMe = new ArrayList();
        while(head != null){
            returnMe.add(head.neuron);
            head = head.next;
        }
        for(Neuron abe : returnMe){
            System.out.println(abe.toString());
        }
        return returnMe;
        
    }
    
    public void reinitializeNeurons(){
        for(Neuron a : vertices){
            a.initialBody();
        }
    }
           
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
}

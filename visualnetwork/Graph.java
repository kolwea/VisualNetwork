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

/**
 *
 * @author Kolbe
 */
public class Graph implements java.io.Serializable{
    private ArrayList<Neuron> vertices ;
    private HashMap<Neuron,Edge> edges;
    private static int index = 0;
    private String name;
    
    public Graph(String name){
        this.name = name;
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
   
}

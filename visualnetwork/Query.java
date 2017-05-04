/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Kolbe
 */
public class Query extends Thread{
    private Network network;
    private static int time;
    private ArrayList<Edge> paths;
    private int index;
    
    public Query(){
        index = 0;
    }
    
    public void run(Neuron search){
        DFS(search);
    }
    
    
    public void setGraph(Network set){
        network = set;
    }
    
    
    public void DFS(Neuron search){
        ArrayList<Neuron> vertices = network.getVertices();

        for(Neuron a : vertices){
            a.setStatus(Status.WHITE);
            a.parent = null;
            a.d = 0;
            a.f = 0;
        }
//        System.out.println("Initialized for search");
        time = 0;
        for(Neuron a : network.getVertices()){
            if(a.status == Status.WHITE)
                DFSvisit(a, search);
        }
    }
    
    public void DFSpath(Neuron start, Neuron search){
        ArrayList<Neuron> vertices = network.getVertices();

        for(Neuron a : vertices){
            a.setStatus(Status.WHITE);
            a.parent = null;
            a.d = 0;
            a.f = 0;
        }
//        System.out.println("Initialized for search");
        time = 0;
        for(Neuron a : network.getAdjList(start)){
            if(a.status == Status.WHITE)
                DFSpathVisit(a, search);
        }
    }
    
    private void DFSvisit(Neuron cur, Neuron search){
        if(cur == search){
            showPath(cur);
            savePath(cur);
        }
        time++;
        cur.d = time;
        cur.setStatus(Status.GREY);
        Edge head = network.getAdjHead(cur);
        while(head != null){
            if(head.neuron.status == Status.WHITE){
                head.neuron.parent = cur;
                DFSvisit(head.neuron, search);
            }
            head = head.next;
        }
        cur.setStatus(Status.BLACK);
        time++;
        cur.f = time;
//        System.out.println(cur.getName() + "-Finished: " + cur.f);
    }
    
    private void DFSpathVisit(Neuron cur, Neuron search){
        System.out.println("Visitin " + cur.getName());
        if(cur == search){
            savePath(cur);
        }
        else{
            cur.setStatus(Status.GREY);
            Edge head = network.getAdjHead(cur);
            while(head != null){
                if(head.neuron.status == Status.WHITE){
                    head.neuron.parent = cur;
                    DFSpathVisit(head.neuron, search);
                }
                head = head.next;
            }
        cur.setStatus(Status.BLACK);
        }
    }
    
    private void showPath(Neuron a){
        Neuron hold = a;
        System.out.println();
        while(hold != null){
            System.out.print("-> " + hold.getName());
            hold = hold.parent;
        }
        System.out.println();
    }
    
    private void savePath(Neuron a){
        if(paths == null)
            paths = new ArrayList();
        Neuron hold = a;
        Edge cur = null;
        Edge next = null;
        while(hold != null){
            cur = new Edge(hold);
            cur.next = next;
            next = cur;
            hold = hold.parent;
        }
        paths.add(index,cur);
        index++;
        System.out.println("Path added at " + index);
    }
    
    public void showSavedPaths(){              
        for(Neuron a : network.getVertices()){
            a.setStatus(Status.WHITE);
        }
        for(Edge head : paths){
            while(head != null){
                System.out.print(head.neuron.getName() + " : ");
                head.neuron.setStatus(Status.GREY);
                head = head.next;
            }
            System.out.println();
        }
    }
    
    
}

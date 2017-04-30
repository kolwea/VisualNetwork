/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

/**
 *
 * @author Kolbe
 */
public class Edge {
    public Neuron neuron = null;
    public double weight = 0.0;
    public Edge next = null;
    public Edge parent = null;
    
    public Edge(){

    };
    
    public Edge(Neuron neuron){
        this.neuron = neuron;
    }
    
    public Edge(Neuron neuron, double weight){
        this.neuron = neuron;
        this.weight = weight;
    }
    

}

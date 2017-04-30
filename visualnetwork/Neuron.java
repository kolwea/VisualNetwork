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
public class Neuron extends Particle{
    private String name;
    private int index;

    public Neuron(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setIndex(int i){
        this.index = i;
    }
    
    public int getIndex(){
        return index;
    }
}

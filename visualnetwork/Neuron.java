/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import javafx.scene.text.Text;

/**
 *
 * @author Kolbe
 */
public class Neuron extends Particle implements java.io.Serializable{
    private String name;
    private int index;
    public Status status;
    public Neuron parent;
    public int d,f;
    private Text label;

    
    public Neuron(String name){
        this.name = name;
        this.initialBody();
    }
    
    @Override
    public void update(Double boundX, Double boundY) {
        Double radius = this.getBody().getRadiusX();
        if(pos.x + radius > boundX || pos.x < radius)
            vel.x = -1 * vel.x;
        if(pos.y + radius> boundY || pos.y < radius)
            vel.y = -1 * vel.y;
        pos = pos.add(vel);
        getBody().setCenterX(pos.x);
        getBody().setCenterY(pos.y);
    }    
        
    public void setName(String name){
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
    
    public void setStatus(Status a) {
        this.status = a;
    }
    
    public Status getStatus(){
        return this.status;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
    
}


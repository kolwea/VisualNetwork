/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import javafx.scene.paint.Color;
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
        this.initial();
//        System.out.println("Setup for " + name + " completed.");
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
        if(null != status)switch (status) {
            case WHITE:
                this.getBody().setFill(Color.ANTIQUEWHITE);
                break;
            case GREY:
                this.getBody().setFill(Color.GREY);
                break;
            case BLACK:
                this.getBody().setFill(Color.BLACK);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void update() {
        if(pos.x > this.getPane().getWidth() || pos.x < 0)
            vel.x = -1 * vel.x;
        if(pos.y > this.getPane().getHeight() || pos.y < 0)
            vel.y = -1 * vel.y;
        pos = pos.add(vel);
        getBody().setCenterX(pos.x);
        getBody().setCenterY(pos.y);
//        if(this.getLines() != null){
//            this.updateLines(head);
//        }
//        if(this.getName().compareTo("Wood") == 0)
//            System.out.println("X: " + this.pos.x + " Y: " + this.pos.y);
//        label.setX(pos.x);
//        label.setY(pos.y);
    }    
    
    
    
}


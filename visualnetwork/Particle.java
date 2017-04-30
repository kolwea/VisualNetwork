/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import javafx.scene.shape.Ellipse;

/**
 *
 * @author Kolbe
 */
public class Particle {
        
    private Vector pos;
    private Ellipse body;
    private double radius = 10.0;

    
    public Particle(){
       
   } 
    
    public void setPos(Vector pos){
        this.pos = pos;
    }
    
    public Vector getPos(){
        return pos;
    }
    
    public void setBody(Ellipse body){
        this.body = body;
    }
    
    public Ellipse getBody(){
        return body;
    }
   
}

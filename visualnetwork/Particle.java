/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Kolbe
 */
public abstract class Particle {
        
    public Vector pos,vel;
    private Ellipse body;
    private double radius = 10.0;
    private AnchorPane vizPane;
    private Text label;

    
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
    
    public void setPane(AnchorPane a){
        vizPane = a;
    }
    
    public AnchorPane getPane(){
        return vizPane;
    }
   
    
    abstract public void update();
    
    public void initial(AnchorPane a){
        if(body == null)
            body = new Ellipse();
        if(label == null)
            label = new Text();
        vizPane = a;
        body.setRadiusX(30);
        body.setRadiusY(30);
        body.setFill(Color.BURLYWOOD);
        pos = new Vector(getRandomX(), getRandomY());
        vel = new Vector(Math.random()*5,Math.random()*5);  
    }
        
    
    private double getRandomX(){
        double width = vizPane.getWidth();
        double randomNum = Math.random()*width;
//        System.out.print("X: " + randomNum);
        return randomNum;
    }
    
    private double getRandomY(){
        double height =  vizPane.getHeight();
        double randomNum = Math.random()*height;
//        System.out.println(" Y: " + randomNum);
        return randomNum;
    }
    
}

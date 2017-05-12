/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

/**
 *
 * @author Kolbe
 */
public abstract class Particle{
        
    public Vector pos,vel;
    private Ellipse body;
    private double radius = 10.0;
    private final Random ran = new Random();

    
    public Particle(){
   } 

    abstract public void update(Double boundX,Double boundY);
         
    public void initialBody(){
        body = new Ellipse();
        body.setRadiusX(10);
        body.setRadiusY(10);
        body.setFill(Color.web("#72b01d")); 
        body.setStroke(Color.web("#357d20"));
    }  

    
//This is a lot of online code but theres just no way I could've gotten here without basically all of it...         
//Thanks James_D of StackOverflow (T_T)
    public void connect(Node n2, Edge head){
        if(this.body.getParent() == null){
            System.out.println("Tis null");
        }
        else{
            Pane parent = (Pane) this.body.getParent();
            Line line = new Line();
            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = this.body.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2 ;
            }, this.body.boundsInParentProperty()));
            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = this.body.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2 ;
            }, this.body.boundsInParentProperty()));
            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2 ;
            }, n2.boundsInParentProperty()));
            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2 ;
            }, n2.boundsInParentProperty()));
            parent.getChildren().add(line);
            this.getBody().toFront();
            
            n2.toFront();
            head.neuron.getBody().toFront();
            head.connection = line;

        }
    }
    
    public void updateLines(Edge head){
        while(head != null){
            Neuron update = head.neuron;
            Double x = (this.body.getCenterX() * update.getBody().getCenterX());
            Double y = (this.body.getCenterY() * update.getBody().getCenterY());
            Double dis = Math.sqrt(x+y);
            Double slope = (2.0 - 0.1) / 3000.0;
            Double output = 0.1 + slope * dis;
            head.connection.setStrokeWidth(Math.pow(output, 2));
            head = head.next;    
        }
    }
        
    public Color mixColor(Color abe, Color mix) {
        double r,g,b;
        r = abe.getRed()/1.5;
        g = abe.getGreen()/1.5;
        b = abe.getBlue()/1.5;
        return new Color(Math.min(mix.getRed() + r, 1.0), Math.min(mix.getGreen() + g , 1.0), Math.min(mix.getBlue() + b, 1.0), 1);
    }

    public Color randomColor() {
        return new Color(ran.nextDouble(), ran.nextDouble(), ran.nextDouble(), 1);
    }  
    
    public void setPos(Vector pos, Vector vel){
        this.pos = pos;
        this.vel = vel;
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

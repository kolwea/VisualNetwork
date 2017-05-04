/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
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
    private int index;
    private ArrayList<Line> lines;
        private final Random rng = new Random();

    
    public Particle(){
        index = 0;
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
        if(pos == null && vel == null){
          pos = new Vector(getRandomX(), getRandomY());
          vel = new Vector(Math.random()*5,Math.random()*5);
        }
        return body;
    }
    
    public void setNewPos(){
        if(pos == null && vel == null){
          pos = new Vector(getRandomX(), getRandomY());
          vel = new Vector(Math.random()*2,Math.random()*2);
        }
    }
    
    public void setPane(AnchorPane a){
        vizPane = a;
    }
    
    public AnchorPane getPane(){
        return vizPane;
    }
    
    public ArrayList<Line> getLines(){
        return lines;
    }

    abstract public void update();
        
    private Color randomColor() {
        return new Color(rng.nextDouble(), rng.nextDouble(), rng.nextDouble(), 1);
    }
    
    public void initial(){
//        System.out.println("Initializing...");
        body = new Ellipse();
        label = new Text();
        body.setRadiusX(20);
        body.setRadiusY(20);
        body.setFill(Color.web("#4C5C68", 0.3)); 
        body.setStroke(Color.web("#46494C"));
        
      
    }  
    
    public void initialConec(){
        body = new Ellipse();
        body.setRadiusX(2);
        body.setRadiusY(2);
        body.setFill(Color.web("#1985A1"));  
    }
    

    
    private double getRandomX(){
        double width = vizPane.getWidth();
        double randomNum = Math.random()*width;
        return randomNum;
    }
    
    private double getRandomY(){
        double height =  vizPane.getHeight();
        double randomNum = Math.random()*height;
        return randomNum;
    }
            
//This is a lot of online code but theres just no way I could've gotten here without basically all of it...         
//Thanks James_D of StackOverflow (T_T)
    public void connect(Node n2){
        if(lines == null)
            lines = new ArrayList();
        if(this.body.getParent() == null){
            System.out.println("Tis null");
        }
        else{
            Pane parent = (Pane) this.body.getParent();
            Line line = new Line();
            System.out.println(line.getStrokeWidth());
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
            lines.add(index,line);
            index++;
        }
    }
    
    public void updateLines(Edge head){
        if(this.getLines() != null){
            int index = 0;
            while(head != null){
                Neuron update = head.neuron;
                Double x = (this.body.getCenterX() * update.getBody().getCenterX());
                Double y = (this.body.getCenterY() * update.getBody().getCenterY());
                Double dis = Math.sqrt(x+y);
                Double wid = dis/800;
                this.getLines().get(index).setStrokeWidth(Math.pow(wid, 2));
    //            this.getLines().get(index).setStroke(this.randomColor());
                index++;
                head = head.next;
            }
            System.out.println("Nodes: " + index + "Lines" + lines.size());
        }
    }
    
}

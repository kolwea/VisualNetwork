/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import javafx.scene.paint.Color;


/**
 *
 * @author Kolbe
 */
public class Connection extends Particle{

    private Particle from;
    private Particle to;
    private Network network;
    private int index;
    
    public Connection(Particle a, Particle b){
        from = a;
        to = b;
        this.initialConec();
        this.pos = a.getPos();
        vel = new Vector(0.0, 0.0);
        b.getBody().setFill(Color.web("#1985A1"));
    }
    
    @Override
    public void update() {
        Vector toPo = to.getPos();
        if(checkTouching(toPo) != true){
            updateVel(toPo);
            this.pos = pos.add(vel);
            this.getBody().setCenterX(pos.x);
            this.getBody().setCenterY(pos.y);
            if(index % 1000 == 0){
                Connection hold = new Connection(from,(Particle)this);
                hold.setIndex(index+1);
                network.createConex(hold);
            }
            
        }
        
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    private void updateVel(Vector toPos){
        if(this.pos.x > toPos.x)
            vel.x = -10.0;
        if(this.pos.x <= toPos.x)
            vel.x = 10.0;
        if(this.pos.y > toPos.y)
            vel.y = -10.0;
        if(this.pos.y <= toPos.y)
            vel.y = 10.0;    
        //System.out.println("To: x-" + toPos.x + " y-" + toPos.y);
        System.out.println("From: x: " + vel.x + " y: " + vel.y);

    }
    
    private boolean checkTouching(Vector toPos){
        double disX = Math.abs(this.pos.x - toPos.x);   
        double disY = Math.abs(this.pos.y - toPos.y);
        
        if( disX <= to.getBody().getRadiusX()/2)
            if(disY <= to.getBody().getRadiusX()/2){
                System.out.println("Touched it");
                return true;
            }
        return false;
    }
    
    public void setNetwork(Network me){
        network = me;
    }
    
}

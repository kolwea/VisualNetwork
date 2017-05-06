/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualnetwork;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Kolbe
 */
public interface Particles {
    
    public Ellipse getBody();
    public void update(Double x, Double y);
    public Vector getPos();
    public void setPos();

}

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
public class Vector {
    public Double x;
    public Double y;

    public Vector(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector sub(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector mul(Vector other) {
        return new Vector(x * other.x, y * other.y);
    }
}

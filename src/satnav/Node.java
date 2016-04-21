package satnav;

import java.io.*;

public class Node {

    private boolean visited;
    private int id;
    private int x;
    private int y;
    private double distance = Double.MAX_VALUE;
    private double hdistance = Double.MAX_VALUE;
    //private String edgefrom = "";

    public Node(int newid, int newx, int newy) {
        id = newid;
        x = newx;
        y = newy;
        visited = false;
    }

    public double getdistance() {
        return distance;
    }

    public void setdistance(double a) {
        distance = a;
    }

    public int getid() {
        return id;
    }

    public int huristic(Node current) {
        int a = Math.abs(x - current.x);
        int b = Math.abs(y - current.y);
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public boolean isvisited() {
        return visited;
    }

    public void setvisited() {
        visited = true;
    }

    /**
     * @return the hdistance
     */
    public double getHdistance() {
        return hdistance;
    }

    /**
     * @param hdistance the hdistance to set
     */
    public void setHdistance(double hdistance) {
        this.hdistance = hdistance;
    }
}

package satnav;

/**
 *
 * @author b4026826
 */
public class edge {

    private String label;
    private double distance;
    private int id1;
    private int id2;
    private int speed;
    private boolean multiway;

    public edge(String newlabel, int a, int b, double d, boolean c, int speedlimit) {
        label = newlabel;
        distance = d;
        id1 = a;
        id2 = b;
        multiway = !c;
        speed = speedlimit;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the id1
     */
    public int getId1() {
        return id1;
    }

    /**
     * @param id1 the id1 to set
     */
    public void setId1(int id1) {
        this.id1 = id1;
    }

    /**
     * @return the id2
     */
    public int getId2() {
        return id2;
    }

    /**
     * @param id2 the id2 to set
     */
    public void setId2(int id2) {
        this.id2 = id2;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return distance * speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the multiway
     */
    public boolean isMultiway() {
        return multiway;
    }

    /**
     * @param multiway the multiway to set
     */
    public void setMultiway(boolean multiway) {
        this.multiway = multiway;
    }
}

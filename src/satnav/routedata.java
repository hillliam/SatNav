package satnav;

/**
 *
 * @author liam
 */
public class routedata implements RouteInfoInterface {

    private double totaldistance = 0;
    private double totaltime = 0;
    //private MyStack from = new MyStack(); // stack overflow on large maps
    private MyVector from = new MyVector();
    private int exploredvertex = 0;
    private String path = "";
    private String path_names = "";

    @Override
    public double GetDistanceInMiles() {
        return totaldistance;
    }

    public void adddistance(double distance) {
        totaldistance += distance;
    }

    @Override
    public double GetTimeInMinues() {
        return totaltime;
    }

    public void addtime(double time) {
        totaltime += time;
    }

    @Override
    public int[] GetRouteVertexIDs() {
        return from.getarray();
    }

    public void addvertex(int id) {
        from.AddItem(id);
        //from.Push(id);
    }

    @Override
    public int GetNoOfExploredVertices() {
        return exploredvertex;
    }

    public void incromentvertexcount() {
        exploredvertex++;
    }

    public void addtopath(String node) {
        path += node;
    }

    public void addtopathname(String node) {
        path_names += node;
    }

    @Override
    public String GetRoute(boolean incvertices) {
        if (incvertices) {
            return path_names;
        } else {
            return path;
        }
    }

}

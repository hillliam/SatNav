package satnav;

/**
 *
 * @author liam
 */
public class routedata implements RouteInfoInterface {
    
    private double totaldistance = 0;
    private double totaltime = 0;
    private double totalspeed = 0;
    //private MyStack from = new MyStack(); // stack overflow on large maps
    private intStack from = new intStack();
    private int exploredvertex = 0;
    //private StringVector path = new StringVector();
    //private StringVector path_names = new StringVector();
    private MyStack path = new MyStack();
    private MyStack path_names = new MyStack();
    
    @Override
    public double GetDistanceInMiles() {
        return totaldistance;
    }
    
    public void adddistance(double distance) {
        totaldistance += distance;
    }
    
    public double Getspeed() {
        return totalspeed;
    }
    
    public void addspeed(double speed) {
        totalspeed += speed;
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
        int a = from.GetNoOfItems();
        int array[] = new int[a];
        for (int i=0; i != a;i++)
        {
            array[i] = from.Pop();
        }
        return array;
        //return from.getarray();
    }
    
    public void addvertex(int id) {
        //from.AddItem(id);
        from.Push(id);
    }
    
    @Override
    public int GetNoOfExploredVertices() {
        return exploredvertex;
    }
    
    public void incromentvertexcount() {
        exploredvertex++;
    }
    
    public void addtopath(String node) {
        //if (path.FindItem(node) == -1) {
        //    path.AddItem(node);
        //}
        if (!path.find(node)) {
            path.Push(node);
        }
    }
    
    public void addtopathname(String node) {
        //path_names.AddItem(node);
        path_names.Push(node);
    }
    
    @Override
    public String GetRoute(boolean incvertices) {
        if (incvertices) {
            return path_names.toString();
        } else {
            return path.toString();
        }
    }
    
}

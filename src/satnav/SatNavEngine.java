package satnav;

public class SatNavEngine implements SatNavEngineInterface {

    private graph map = new graph();
    //public routedata path = new routedata();

    @Override
    public String GetSHUID() {
        return "b4026826";
    }

    @Override
    public void AddVertex(int vertexid, int x, int y) {
        map.AddNode(vertexid, x, y);
    }

    @Override
    public void AddEdge(String label, int fromid, int toid, double distance, boolean oneway, int speedlimit) {
        map.AddEdge(label, fromid, toid, distance, oneway, speedlimit);
    }

    @Override
    public RouteInfoInterface GetRoute(String[] start, String[] end, int routetype) {
        routedata path = new routedata();
        map.apathfinding(path, start, end, routetype);
        return path;
    }

    public String printnodes() {
        return map.printnode();
    }

    public String printedges() {
        return map.printedge();
    }
    //public String toString() {
    //    return printnodes() + printedges();
    //}
}

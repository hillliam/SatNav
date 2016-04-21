package satnav;

/**
 *
 * @author b4026826
 */
public class graph {

    private nodeVector nodes = new nodeVector(true); // more memory efficient and reliable
    //private HashMap nodes = new HashMap(9000000); // not reliable 
    private edgevector edges = new edgevector(); // if there where multi key hash maps
    private heap open = new heap();
    private nodeVector close = new nodeVector(); // better if hashmap
    private nodeVector from = new nodeVector();
    public boolean nooneway = false;
    public boolean amoterway = false;

    public graph() {

    }

    public edgevector getedges() {
        return edges;
    }

    void AddNode(int name, int x, int y) {
        nodes.AddItem(new Node(name, x, y));
    }

    public Node findnodebyedge(String it[]) {
        int id1 = 0;
        int id2 = 0;
        for (int i = 0; i != edges.getitems(); i++) {
            if (id1 == 0) {
                if (edges.get(i).getLabel().equals(it[0]) || edges.get(i).getLabel().equals(it[1])) {
                    id1 = edges.get(i).getId1();
                    id2 = edges.get(i).getId2();
                }
            } else {
                if (edges.get(i).getLabel().equals(it[0]) || edges.get(i).getLabel().equals(it[1])) {
                    if (id1 == edges.get(i).getId1() || id1 == edges.get(i).getId2()) {
                        return findnode(edges.get(i).getId1());
                    } else if (id2 == edges.get(i).getId1() || id2 == edges.get(i).getId2()) {
                        return findnode(edges.get(i).getId2());
                    }
                }
            }
        }
        return null;
    }

    public void apathfinding(routedata path, String start[], String end[], int mode) {
        nodes.Sort();
        if (amoterway) {
            removeservice();
        }
        Node startn = findnodebyedge(start);
        Node endn = findnodebyedge(end);
        //System.out.println("start node " + startn.getid());
        //System.out.println("end node " + endn.getid());
        Node current = startn;
        current.setHdistance(0);
        current.setdistance(0);
        current.setvisited();
        //open.AddItem(startn);
        open.Push(startn);
        path.addvertex(startn.getid());
        while (open.GetNoOfItems() != 0) {          //(open.GetNoOfItems() != 0) {
            //System.out.println("starting at " + current.getid());
            //current = lowestcost(open, mode, path);
            current = open.Pop();
            //System.out.println("moveing to " + current.getid());
            close.AddItem(current);
            //path.addvertex(current.getid());
            //open.DeleteItem(open.FindItem(current));
            if (current == endn) {
                break;
            }
            //int b = 0;
            check(path, current, endn, mode);
        }
        addup(path, current, startn);
    }

    private void removeservice() {
        for (int i = 0; i != edges.getitems(); i++) {
            if (edges.get(i).getLabel().contains("ServiceRoad")) {
                edges.remove(i);
            }
        }
    }

    private void check(routedata path, Node current, Node endn, int mode) {
        for (int i = 0; i != edges.getitems(); i++) {
            path.incromentvertexcount();
            if (edges.get(i).isMultiway()) {
                if (current.getid() == edges.get(i).getId1()) {
                    Node c = findnode(edges.get(i).getId2());
                    //System.out.println("found path along " + edges.get(i).getLabel());
                    nodebit(i, c, path, current, endn, mode);
                } else if (current.getid() == edges.get(i).getId2()) {
                    Node c = findnode(edges.get(i).getId1());
                    //System.out.println("found path along " + edges.get(i).getLabel());
                    nodebit(i, c, path, current, endn, mode);
                }
            } else {
                if (!nooneway) {
                    if (current.getid() == edges.get(i).getId1()) {
                        Node c = findnode(edges.get(i).getId2());
                        //System.out.println("found path along " + edges.get(i).getLabel());
                        nodebit(i, c, path, current, endn, mode);
                    }
                }
            }
        }
    }

    private void nodebit(int i, Node c, routedata path, Node current, Node endn, int mode) {
        if (close.FindItem(c) > 0) {
            return;
        }
        if (open.FindItem(c) < 0) {
            open.Push(c);
            //open.AddItem(c);
        }
        double tentive;
        if (mode == 1) {
            tentive = current.getdistance() + edges.get(i).getDistance();
        } else if (mode == 2) {
            tentive = current.getdistance() + edges.get(i).getTime();
        } else {
            tentive = current.getdistance() + edges.get(i).getSpeed();
        }
        if (tentive >= c.getdistance()) {
            return;
        }
        c.setBest(current);
        c.setPath(edges.get(i).getLabel());
        c.setPath_vertex(edges.get(i).getLabel() + "(" + edges.get(i).getId1() + ", " + edges.get(i).getId2() + ")");
        //path.addvertex(c.getid());
        //path.addtopath(edges.get(i).getLabel() + " -> ");
        //path.addtopathname(edges.get(i).getLabel() + "(" + edges.get(i).getId1() + ", " + edges.get(i).getId2() + ") -> ");
        c.setdistance(tentive);
        c.setHdistance(current.getdistance() + c.huristic(endn));
        //System.out.println(c.getdistance());
        //System.out.println(c.getHdistance());
    }

    public void addup(routedata path, Node current, Node startn) {
        Node a = current;
        while (a != startn) {
            path.addvertex(a.getid());
            path.adddistance(a.getdistance());
            path.addtime(a.getdistance());
            //if (path.GetRoute(false).contains(a.getPath())) {
                path.addtopath(a.getPath());
            //}
                path.addtopathname(a.getPath_vertex());
            a = a.getBest();
        }
    }

    public Node lowestcost(nodeVector list, int mode, routedata path) {
        return list.Findlowest();
    }

    public Node lowestcost(Node list, int mode, routedata path) {
        //nodeVector ajacent = GetNeighbours(list, mode);
        Node best = null; //findnode(edges[1].getId1());
        double distance = 9999999;
        for (int i = 0; i != edges.getitems(); i++) {
            path.incromentvertexcount();
            if (edges.get(i).isMultiway()) {
                if (list.getid() == edges.get(i).getId1()) {
                    if (!findnode(edges.get(i).getId1()).isvisited()) {
                        if (edges.get(i).getDistance() < distance) {
                            distance = edges.get(i).getDistance();
                            //System.out.println("found node on" + edges[i].getLabel());
                            best = findnode(edges.get(i).getId2());
                        }
                    }
                }
                if (list.getid() == edges.get(i).getId2()) {
                    if (!findnode(edges.get(i).getId1()).isvisited()) {
                        if (edges.get(i).getDistance() < distance) {
                            distance = edges.get(i).getDistance();
                            //System.out.println("found node on" + edges[i].getLabel());
                            best = findnode(edges.get(i).getId1());
                        }
                    }
                }
            } else {
                if (list.getid() == edges.get(i).getId1()) {
                    if (!findnode(edges.get(i).getId1()).isvisited()) {
                        if (edges.get(i).getDistance() < distance) {
                            distance = edges.get(i).getDistance();
                            //System.out.println("found node on" + edges[i].getLabel());
                            best = findnode(edges.get(i).getId2());
                        }
                    }
                }
            }
        }
        if (best == null) {
            return null;
        }

        best.setvisited();
        return best;
    }

    public Node findnode(int name) {
        //for (int i = 0; i != nodes.length; i++) {
        //    if (name == nodes[i].getid()) {
        //       return nodes[i];
        //     }
        // }
        //return null;
        //return nodes.GetValue(name);
        return nodes.findbynode(name);
    }

    public nodeVector GetNeighbours(Node a, int mode) {
        nodeVector found = new nodeVector();
        int b = 0;
        for (int i = 0; i != edges.getitems(); i++) {
            if (edges.get(i).isMultiway()) {
                if (a.getid() == edges.get(i).getId1()) {
                    Node c = findnode(edges.get(i).getId2());
                    found.AddItem(c);
                    c.setdistance(edges.get(i).getDistance());
                    c.setHdistance(a.getdistance() + c.huristic(a));
                } else if (a.getid() == edges.get(i).getId2()) {
                    Node c = findnode(edges.get(i).getId1());
                    found.AddItem(c);
                    c.setdistance(edges.get(i).getDistance());
                    c.setHdistance(a.getdistance() + c.huristic(a));
                }
            } else {
                if (a.getid() == edges.get(i).getId1()) {
                    Node c = findnode(edges.get(i).getId2());
                    found.AddItem(c);
                    c.setdistance(edges.get(i).getDistance());
                    c.setHdistance(a.getdistance() + c.huristic(a));
                }
            }
        }
        return found;
    }

    public void AddEdge(String label, int one, int two, double distance, boolean way, int speedlimit) {
        edge a = new edge(label, one, two, distance, way, speedlimit);
        edges.AddEdge(a);
    }

    public String printnode() {
        return nodes.toString();
    }

    public String printedge() {
        return edges.printedge();
    }
}

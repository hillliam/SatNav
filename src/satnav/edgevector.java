package satnav;

public class edgevector {

    private edge edges[] = new edge[0];
    private int itemcount = 0;
    private final int growammount = 2000;

    private void growedges() {
        edge newarray[] = new edge[edges.length + growammount];
        for (int i = 0; i != edges.length; i++) {
            newarray[i] = edges[i];
        }
        edges = newarray;
    }

    public int getitems() {
        return itemcount;
    }

    public edge get(int index) {
        return edges[index];
    }

    public boolean IsAdjacent(Node x, Node y) {
        for (int i = 0; i != itemcount; i++) {
            if (edges[i].isMultiway()) {
                if ((x.getid() == edges[i].getId1() || x.getid() == edges[i].getId2()) && (y.getid() == edges[i].getId1() || y.getid() == edges[i].getId2())) {
                    return true;
                }
            } else {
                if (x.getid() == edges[i].getId1() && y.getid() == edges[i].getId2()) {
                    return true;
                }
            }
        }
        return false;
    }

    public edge getedge(Node x, Node y) {
        if (x == null || y == null) {
            return null;
        }
        for (int i = 0; i != itemcount; i++) {
            if (edges[i].isMultiway()) {
                if ((x.getid() == edges[i].getId1() || x.getid() == edges[i].getId2()) && (y.getid() == edges[i].getId1() || y.getid() == edges[i].getId2())) {
                    return edges[i];
                }
            } else {
                if (x.getid() == edges[i].getId1() && y.getid() == edges[i].getId2()) {
                    return edges[i];
                }
            }
        }
        return null;
    }

    public String printedge() {
        String a = "";
        for (int i = 0; i != edges.length; i++) {
            if (edges[i] != null) {
                a += (edges[i].getLabel() + " " + edges[i].getId1() + " " + edges[i].getId2() + "/n");
            }
        }
        return a;
    }

    public void AddEdge(edge a) {
        if (itemcount == edges.length) {
            growedges();
        }
        edges[itemcount++] = a;
    }
    public void remove(int index)
    {
        for (int i = index; i!= itemcount-1; i++)
        {
            edges[i] = edges[i+1];
        }
        itemcount--;
    }
}

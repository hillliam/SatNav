package satnav;

import java.io.*;
import java.util.Arrays;

public class SatNav {

    public static void main(String[] args) {
        // Create a new SatNavEngine
        SatNavEngine engine = new SatNavEngine();
        // Load the vertex and edge data using the AddEdge and AddVertex methods
        //LoadVertices(engine, "SheffieldCentre - ADS2SatNavData-Vertices.dat");
        //LoadEdges(engine, "SheffieldCentre - ADS2SatNavData-Edges.dat");
        // bigger
        LoadVertices(engine, "Sheffield - ADS2SatNavData-Vertices.dat");
        LoadEdges(engine, "Sheffield - ADS2SatNavData-Edges.dat");
        // bigest
        //LoadVertices(engine, "LeedsSheffieldManchester - ADS2SatNavData-Vertices.dat");
        //LoadEdges(engine, "LeedsSheffieldManchester - ADS2SatNavData-Edges.dat");
        // start testing
        pathfinding("Furnival Street", "Arundel Street", "Norfolk Street", "Milk Street", engine);
        System.out.println("done");
        //pathfinding("Cross Turner Street", "Turner Street", "Orchard Lane", "Leopold Street", engine);
         System.out.println("done");
        //pathfinding("Furnival Street", "Arundel Street", "Leopold Street", "Back Lane", engine);
         System.out.println("done");
        //pathfinding("Loxley Road", "Back Lane", "Ellis Street", "Pike Road", engine);
         System.out.println("done");
        //pathfinding("Fulwood Lane", "Bassett Lane", "Loxley Road", "Back Lane", engine);
         System.out.println("done");
        //pathfinding("Fulwood Lane", "Bassett Lane", "Ellis Street", "Pike Road", engine);
         System.out.println("done");
        //pathfinding("Stanwell Avenue", "Stanwell Street", "Fulwood Lane", "Bassett Lane", engine);
    }

    public static void pathfinding(String start, String start2, String end, String end2, SatNavEngine engine) {

        RouteInfoInterface a = new routedata();
        RouteInfoInterface b = new routedata();
        String[] array = new String[2];
        String[] array2 = new String[2];
        //engine.printnodes();
        array[0] = start;
        array[1] = start2;
        array2[0] = end;
        array2[1] = end2;
        a = engine.GetRoute(array, array2, 1);
        b = engine.GetRoute(array, array2, 2);
        System.out.println("shortest distance");
        printdata(a);
        System.out.println("QUICKEST TIME");
        printdata(b);
    }

    private static void printdata(RouteInfoInterface a) {
        System.out.println(a.GetDistanceInMiles());
        System.out.println(a.GetNoOfExploredVertices());
        System.out.println(Arrays.toString(a.GetRouteVertexIDs()));
        System.out.println(a.GetTimeInMinues());
        System.out.println(a.GetRoute(true));
        System.out.println(a.GetRoute(false));
    }

    public static void LoadVertices(SatNavEngineInterface engine, String filename) {
        byte[] data = new byte[12];

        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
            while (in.read(data) == 12) {
                int id = (data[0] & 0xFF) | ((data[1] & 0xFF) << 8) | ((data[2] & 0xFF) << 16) | ((data[3] & 0xFF) << 24);
                int x = (data[4] & 0xFF) | ((data[5] & 0xFF) << 8) | ((data[6] & 0xFF) << 16) | ((data[7] & 0xFF) << 24);
                int y = (data[8] & 0xFF) | ((data[9] & 0xFF) << 8) | ((data[10] & 0xFF) << 16) | ((data[11] & 0xFF) << 24);
                engine.AddVertex(id, x, y);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void LoadEdges(SatNavEngineInterface engine, String filename) {
        int len;
        byte[] data = new byte[256];
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
            while ((len = in.read()) != -1) {
                in.read(data, 0, len);
                String label = new String(data, 0, len);

                in.read(data, 0, 13);
                int from = (data[0] & 0xFF) | ((data[1] & 0xFF) << 8) | ((data[2] & 0xFF) << 16) | ((data[3] & 0xFF) << 24);
                int to = (data[4] & 0xFF) | ((data[5] & 0xFF) << 8) | ((data[6] & 0xFF) << 16) | ((data[7] & 0xFF) << 24);
                int upper = (data[8] & 0xFF) | ((data[9] & 0xFF) << 8);
                int lower = (data[10] & 0xFF) | ((data[11] & 0xFF) << 8);
                double distance = upper + (lower / 1000.0);

                engine.AddEdge(label, from, to, distance, (data[12] & 0x80) == 0x80, data[12] & 0x7F);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

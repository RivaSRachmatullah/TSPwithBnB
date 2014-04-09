package Data_Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
    private Node[] NodesinGraph;
    public static void main(String args[]) {
        Graph G = new Graph();
        String path = "graph2.in";
        G.ReadGraphFromFile(path);
        G.PrintGraph();
    }
    public void ReadGraphFromFile(String FilePath) {
        try {
            NodesinGraph = new Node[CountNumberofLineinFile(FilePath)];
            FileReader filein = new FileReader(FilePath);
            BufferedReader fileinput = new BufferedReader(filein);
            String line;
            int i = 0;
            while ((line = fileinput.readLine()) != null) {
                NodesinGraph[i] = new Node();
                NodesinGraph[i].SetAllEdgeCorrespondtoNode(SplitandParsetoInt(line));
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
    public void PrintGraph() {
        int i = 0;
        for (Node NodeinGraph : NodesinGraph) {
            System.out.println("Node " + (i));
            for (Edge EdgeinANode : NodeinGraph.GetEdgeofNode()) {
                System.out.println(EdgeinANode.GetGoal() + " " + 
                        EdgeinANode.GetWeight());
            }
            i++;
        }
    }
    private int CountNumberofLineinFile(String FilePath) throws IOException {
        FileReader filein = new FileReader(FilePath);
        BufferedReader fileinput = new BufferedReader(filein);
        int i = 0;
        while (fileinput.readLine() != null) {
            i++;
        }
        return i;
    }
    private int[] SplitandParsetoInt(String Line) {
        String[] PerLine = Line.split(" ");
        int[] IntegeronLine = new int[PerLine.length];
        int i = 0;
        while (i < PerLine.length) {
            IntegeronLine[i] = Integer.parseInt(PerLine[i]);
            i++;
        }
        return IntegeronLine;
    }
}

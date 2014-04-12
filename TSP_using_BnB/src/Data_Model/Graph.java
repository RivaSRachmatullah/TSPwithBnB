package Data_Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
    private int NNode;
    private Double[][] GraphMatrix;
    
    public int GetNNode() {
        return NNode;
    }
    
    public Double[][] GetGraphMatrix() {
        return GraphMatrix;
    }
    
    public void ReadGraphFromFile(File FilePath) throws NumberFormatException {
        try {
            NNode = CountNumberofLineinFile(FilePath);
            GraphMatrix = new Double[NNode][NNode];
            FileReader filein = new FileReader(FilePath);
            BufferedReader fileinput = new BufferedReader(filein);
            String line;
            int i = 0;
            while ((line = fileinput.readLine()) != null) {
                GraphMatrix[i] = SplitandParsetoInt(line);
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
    
    private int CountNumberofLineinFile(File FilePath) throws IOException {
        FileReader filein = new FileReader(FilePath);
        BufferedReader fileinput = new BufferedReader(filein);
        int i = 0;
        while (fileinput.readLine() != null) {
            i++;
        }
        return i;
    }
    
    private Double[] SplitandParsetoInt(String Line) {
        String[] PerLine = Line.split(" ");
        Double[] Weights = new Double[PerLine.length];
        int i = 0;
        while (i < PerLine.length) {
            Weights[i] = Double.parseDouble(PerLine[i]);
            if (Weights[i] == 0) {
                Weights[i] = Double.POSITIVE_INFINITY;
            }
            i++;
        }
        return Weights;
    }
}

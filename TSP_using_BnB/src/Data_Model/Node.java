package Data_Model;

public class Node {
    private int Cost;
    private final Node ParentNode;
    private final int TreeLevel;
    private final int IdentityofNode;
    private Double[][] ReducedCostMatrix;
    
    public Node(int IdentityofNode, Double[][] GraphMatrix) {
        TreeLevel = 0;
        this.IdentityofNode = IdentityofNode;
        Cost = MakeReducedCostMatrixforthisNode(GraphMatrix);
        ParentNode = null;
    }
    
    public Node(int IdentityofNode, Node ParentNode) {
        this.IdentityofNode = IdentityofNode;
        Cost = ParentNode.Cost + 
                ParentNode.ReducedCostMatrix[ParentNode.IdentityofNode][IdentityofNode].intValue();
        int R = MakeReducedCostMatrixforthisNode(ParentNode);
        TreeLevel = ParentNode.TreeLevel + 1;
        Cost += R;
        this.ParentNode = ParentNode;
    }
    
    public int GetLevel() {
        return TreeLevel;
    }
    
    public int GetIdentity() {
        return IdentityofNode;
    }
    
    public int GetCostonThisNode() {
        return Cost;
    }
    
    public Node GetParentofThisNode() {
        return ParentNode;
    }
    
    private int MakeReducedCostMatrixforthisNode(Double[][] Matrix) {
        int NNode = Matrix.length;
        ReducedCostMatrix = cloneMatrix(Matrix);
        return ReduceMatrixUsingColumn(NNode) + ReduceMatrixUsingRow(NNode);
    }
    
    private int MakeReducedCostMatrixforthisNode(Node ParentNode) {
        int NNode = ParentNode.ReducedCostMatrix.length;
        ReducedCostMatrix = cloneMatrix(ParentNode.ReducedCostMatrix);
        for (int i = 0; i < NNode; i++) {
            ReducedCostMatrix[ParentNode.IdentityofNode][i] = Double.POSITIVE_INFINITY;
            ReducedCostMatrix[i][IdentityofNode] = Double.POSITIVE_INFINITY;
        }
        ReducedCostMatrix[IdentityofNode][0] = Double.POSITIVE_INFINITY;
        return ReduceMatrixUsingColumn(NNode) + ReduceMatrixUsingRow(NNode);
    }
    
    private Double[][] cloneMatrix(Double[][] Matrix) {
        int LengthofMatrix = Matrix.length;
        Double[][] ResultofClone = new Double[LengthofMatrix][LengthofMatrix];
        for (int i = 0; i < LengthofMatrix; i++) {
            System.arraycopy(Matrix[i], 0, ResultofClone[i], 0,
                    ResultofClone[i].length);
        }
        return ResultofClone;
    }
    
    private int ReduceMatrixUsingColumn(int NNode) {
        int TotalCosttoReduce = 0;
        for (int i = 0; i < NNode; i++) {
            Boolean Thereis0 = false;
            Double Minimum = ReducedCostMatrix[i][0];
            int InfinityCounter = 0;
            for (int j = 0; j < NNode; j++) {
                if (ReducedCostMatrix[i][j].isInfinite()) {
                    InfinityCounter++;
                }
                if (ReducedCostMatrix[i][j].equals((double) 0) && !Thereis0) {
                    Thereis0 = true;
                }
                if (Minimum.compareTo(ReducedCostMatrix[i][j]) > 0) {
                    Minimum = ReducedCostMatrix[i][j];
                }
            }
            if (!Thereis0 && InfinityCounter < NNode) {
                for (int j = 0; j < NNode; j++) {
                    ReducedCostMatrix[i][j] -= Minimum;
                }
                TotalCosttoReduce += Minimum.intValue();
            }
        }
        return TotalCosttoReduce;
    }
    
    private int ReduceMatrixUsingRow(int NNode) {
        int TotalCosttoReduce = 0;
        for (int i = 0; i < NNode; i++) {
            Boolean Thereis0 = false;
            Double Minimum = ReducedCostMatrix[0][i];
            int InfinityCounter = 0;
            for (int j = 0; j < NNode; j++) {
                if (ReducedCostMatrix[j][i].isInfinite()) {
                    InfinityCounter++;
                }
                if (ReducedCostMatrix[j][i].equals((double) 0) && !Thereis0) {
                    Thereis0 = true;
                }
                if (Minimum.compareTo(ReducedCostMatrix[j][i]) > 0) {
                    Minimum = ReducedCostMatrix[j][i];
                }
            }
            if (!Thereis0 && InfinityCounter < NNode) {
                for (int j = 0; j < NNode; j++) {
                    ReducedCostMatrix[j][i] -= Minimum;
                }
                TotalCosttoReduce += Minimum.intValue();
            }
        }
        return TotalCosttoReduce;
    }
}

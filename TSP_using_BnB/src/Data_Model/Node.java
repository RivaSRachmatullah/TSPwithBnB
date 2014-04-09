package Data_Model;

public class Node {
    private Edge[] EdgesCorrespondtoNode;
    public Edge[] GetEdgeofNode() {
        return EdgesCorrespondtoNode;
    }
    public void SetAllEdgeCorrespondtoNode(int[] ArrayofWeight) {
        EdgesCorrespondtoNode = new Edge[CountEdgefromThisNode(ArrayofWeight)];
        int i = 0, j = 0;
        for (int Weight : ArrayofWeight) {
            if (Weight != 0) {
                EdgesCorrespondtoNode[j] = new Edge();
                EdgesCorrespondtoNode[j].SetContent(Weight, i);
                j++;
            }
            i++;
        }
    }    
    private int CountEdgefromThisNode(int[] ArrayofWeight) {
        int NEdge = 0;
        for (int Weight : ArrayofWeight) {
            if (Weight > 0) {
                NEdge++;
            }
        }
        return NEdge;
    }
}

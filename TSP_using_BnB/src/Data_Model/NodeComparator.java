package Data_Model;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node A, Node B) {
        if (A.GetCostonThisNode()< B.GetCostonThisNode()) {
            return -1;
        } else if (A.GetCostonThisNode() > B.GetCostonThisNode()) {
            return 1;
        } else {
            return 0;
        }
    }
}

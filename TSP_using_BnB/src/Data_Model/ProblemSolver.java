package Data_Model;

import java.util.PriorityQueue;

public class ProblemSolver {
    private final int NNode;
    private final Double[][] GraphMatrix;
    private PriorityQueue<Node> LiveNode;
    
    public ProblemSolver(Double[][] GraphMatrix, int NNode) {
        this.NNode = NNode;
        this.GraphMatrix = GraphMatrix;
        LiveNode = new PriorityQueue<>(NNode*NNode, new NodeComparator());
    }
    
    public String SolveWithBnB() {
        String SolutionNode = null;
        LiveNode.add(new Node(0, GraphMatrix));
        Boolean Finish = false;
        while (!Finish) {
            Node ParentNode = LiveNode.remove();
            if (ParentNode.GetLevel() == NNode - 1) {
                Finish = true;
                LiveNode.clear();
                SolutionNode = PrintSolution(ParentNode);
            } else {
                for (int i = 0; i < NNode; i++) {
                    if (!IsNodeAlreadySelected(i, ParentNode)) {
                        Node Temp = new Node(i, ParentNode);
                        LiveNode.add(Temp);
                    }
                }
            }
        }
        SolutionNode += "1\n";
        return SolutionNode;
    }
    
    private String PrintSolution(Node EndNode) {
        if (EndNode.GetParentofThisNode() == null) {
            return EndNode.GetIdentity()+1 + " ";
        } else {
            String Solution = PrintSolution(EndNode.GetParentofThisNode());
            Solution += EndNode.GetIdentity()+1 + " ";
            return Solution;
        }
    }
    
    private Boolean IsNodeAlreadySelected(int NodeIdentity, Node ParentNode) {
        Boolean Check = false;
        Node Parent = ParentNode;
        while (!Check && Parent != null) {
            if (Parent.GetIdentity() == NodeIdentity) {
                Check = true;
            } else {
                Parent = Parent.GetParentofThisNode();
            }
        }
        return Check;
    }
}

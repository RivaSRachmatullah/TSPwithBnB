package Data_Model;

public class Edge {
    private int EndNode;
    private int WeightofEdge;
    public int GetGoal() {
        return EndNode;
    }
    public int GetWeight() {
        return WeightofEdge;
    }
    public void SetContent(int Weight, int Goal) {
       WeightofEdge = Weight;
       EndNode = Goal;
    }
}

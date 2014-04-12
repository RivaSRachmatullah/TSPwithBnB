package Data_View;

import java.awt.*;
import java.awt.geom.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import javax.swing.*;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

public class GraphView extends JApplet {
    private static class ListenableDirectedWeightedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
        implements DirectedGraph<V, E>, WeightedGraph<V,E>
    {
        ListenableDirectedWeightedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedWeightedMultigraph<V, E>(edgeClass));
        }
    }
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(500, 400);
    private JGraphModelAdapter jgAdapter;
    private ListenableDirectedWeightedMultigraph<Integer, DefaultWeightedEdge> DirectedGraphModel;
    
    public void init(Double[][] GraphMatrix) {
        DirectedGraphModel = new ListenableDirectedWeightedMultigraph<>
            (DefaultWeightedEdge.class);
        jgAdapter = new JGraphModelAdapter(DirectedGraphModel);
        JGraph jgraph = new JGraph(jgAdapter);
        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
        resize(DEFAULT_SIZE);
        for (int i = 0; i < GraphMatrix.length; i++) {
            DirectedGraphModel.addVertex(i);
        }
        for (int i = 0; i < GraphMatrix.length; i++) {
            for (int j = 0; j < GraphMatrix.length; j++) {
                if (!GraphMatrix[i][j].isInfinite()) {
                    DirectedGraphModel.addEdge(i, j);
                    DirectedGraphModel.setEdgeWeight(DirectedGraphModel.getEdge(i, j),
                        GraphMatrix[i][j]);
                }
            }
        }
        int GraphSize = 100;
        double x = Math.toRadians(360/GraphMatrix.length);
        for (int i = 0; i < GraphMatrix.length; i++) {
            positionVertexAt(i, cos(x*i)*GraphSize + this.getHeight()/2,
                    sin(x*i)*GraphSize + this.getWidth()/2);
        }
    }
    
    private void adjustDisplaySettings(JGraph jg) {
        jg.setPreferredSize(DEFAULT_SIZE);
        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;
        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {}
        if (colorStr != null) {
            c = Color.decode(colorStr);
        }
        jg.setBackground(c);
    }
    
    @SuppressWarnings("unchecked")
    private void positionVertexAt(Object vertex, double x, double y) {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);
        Rectangle2D newBounds = new Rectangle2D.Double(x, y, bounds.getWidth(),
                bounds.getHeight());
        GraphConstants.setBounds(attr, newBounds);
        org.jgraph.graph.AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }

}

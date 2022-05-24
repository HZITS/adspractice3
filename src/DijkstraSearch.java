import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


public class DijkstraSearch<Vertex> extends Search<Vertex> {
    private Set<Vertex> unsettledNodes;
    private Map<Vertex, Double> distances;
    private WeightedGraph<Vertex> graph;

    public DijkstraSearch(WeightedGraph<Vertex> graph, Vertex source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            Vertex node = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(node);
            unsettledNodes.remove(node);
            for (Map.Entry<Vertex, Double> target : graph.adjacencyList(node)) {
                if (getShortestDistance(target.getKey()) > getShortestDistance(node)
                        + target.getValue()) {
                    distances.put(target.getKey(), getShortestDistance(node)
                            + target.getValue());
                    edgeTo.put(target.getKey(), node);
                    unsettledNodes.add(target.getKey());
                }
            }
        }
    }

    private Vertex getVertexWithMinimumWeight(Set<Vertex> vertices) {
        Vertex minimum = null;
        for (Vertex vertex : vertices) {
            if (minimum == null)
                minimum = vertex;
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                    minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}



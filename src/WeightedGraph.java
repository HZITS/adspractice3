import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<T> {
    private final boolean biDirectional;
    private final Map<T, WeightedVertex<T>> map;

    public WeightedGraph() {
        this(false);
    }

    public WeightedGraph(boolean biD) {
        this.biDirectional = biD;
        map = new HashMap<>();
    }

    public void addVertex(T v) {
        map.put(v, new WeightedVertex<>(v));
    }

    public void addEdge(T source, T dest, Double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return;

        WeightedVertex<T> sourceVertex = map.get(source);
        WeightedVertex<T> destVertex = map.get(dest);

        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (biDirectional)
            destVertex.addAdjacentVertex(sourceVertex, weight);
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).countAdjacentVertices();
        }

        if (!biDirectional)
            count /= 2;

        return count;
    }


    public boolean hasVertex(T v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(T source, T dest) {
        WeightedVertex<T> sourceVertex = map.get(source);
        WeightedVertex<T> destVertex = map.get(dest);
        return hasVertex(source) && sourceVertex.isAdjacent(destVertex);
    }

    public Iterable<Map.Entry<T, Double>> adjacencyList(T v) {
        if (!hasVertex(v)) return null;
        WeightedVertex<T> vertex = map.get(v);
        Map<T, Double> adjacent = new HashMap<>();
        for (Map.Entry<WeightedVertex<T>, Double> entry : vertex.adjacentVerticesList()) {
            adjacent.put(entry.getKey().getVertex(), entry.getValue());
        }
        return adjacent.entrySet();
    }
}

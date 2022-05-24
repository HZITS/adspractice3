import java.util.*;

public class MyGraph<T> {
    private final boolean biDirectional;
    private final Map<T, Vertex<T>> map;

    public MyGraph() {
        this(false);
    }

    public MyGraph(boolean biDirectional) {
        this.biDirectional = biDirectional;
        map = new HashMap<>();
    }

    public void addVertex(T v) {
        map.put(v, new Vertex<>(v));
    }

    public void addEdge(T source, T dest) {
        Vertex<T> sourceVertex = map.get(source);
        Vertex<T> destVertex = map.get(dest);
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return;

        sourceVertex.addAdjacentVertex(destVertex);

        if (biDirectional)
            destVertex.addAdjacentVertex(sourceVertex);
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
        Vertex<T> sourceVertex = map.get(source);
        Vertex<T> destVertex = map.get(dest);
        return hasVertex(source) && sourceVertex.isAdjacent(destVertex);
    }

    public Iterable<T> adjacencyList(T v) {
        if (!hasVertex(v)) return null;
        Vertex<T> vertex = map.get(v);
        List<T> adjacent = new LinkedList<>();

        for (Vertex<T> a: vertex.adjacentVerticesList()) {
            adjacent.add(a.getVertex());
        }
        return adjacent;
    }
}


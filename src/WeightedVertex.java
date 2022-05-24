import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WeightedVertex<V> {
    private final V data;
    private final Map<WeightedVertex<V>, Double> adjacentVertices;

    public WeightedVertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public V getVertex(){
        return data;
    }

    public void addAdjacentVertex(WeightedVertex<V> destination, Double weight) {
        adjacentVertices.put(destination, weight);
    }

    public void removeAdjacentVertex(WeightedVertex<V> destination, Double weight) {
        adjacentVertices.remove(destination, weight);
    }

    public boolean isAdjacent(WeightedVertex<V> destination) {
        return adjacentVertices.containsKey(destination);
    }

    public int countAdjacentVertices() {
        return adjacentVertices.size();
    }

    public Iterable<Map.Entry<WeightedVertex<V>, Double>> adjacentVerticesList() {
        return adjacentVertices.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeightedVertex<?> vertex = (WeightedVertex<?>) o;
        return Objects.equals(data, vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
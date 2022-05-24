import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertex<V> {
    private final V data;
    private final Set<Vertex<V>> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashSet<>();
    }

    public V getVertex(){
        return data;
    }

    public void addAdjacentVertex(Vertex<V> destination) {
        adjacentVertices.add(destination);
    }

    public void removeAdjacentVertex(Vertex<V> destination) {
        adjacentVertices.remove(destination);
    }

    public boolean isAdjacent(Vertex<V> destination) {
        return adjacentVertices.contains(destination);
    }

    public int countAdjacentVertices() {
        return adjacentVertices.size();
    }

    public Iterable<Vertex<V>> adjacentVerticesList() {
        return adjacentVertices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> Vertex = (Vertex<?>) o;
        return Objects.equals(data, Vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
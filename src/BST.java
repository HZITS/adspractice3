import java.util.Iterator;

public class BST <K extends Comparable<K>, V> implements Iterable<K>{
    private static class MyNode<K extends Comparable<K>, V>{
        K key;
        V value;
        MyNode<K, V> left, right;
        int length = 1;

        MyNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private MyNode<K, V> root;
    private int length;

    public void put(K key, V value){
        MyNode<K, V> newNode = new MyNode<>(key, value);
        root = put(root, newNode);
    }

    private MyNode<K, V> put(MyNode<K, V> current, MyNode<K, V> node) {
        if (current == null) {
            return node;
        }

        int cmp = node.key.compareTo(current.key);

        if (cmp > 0) {
            current.right = put(current.right, node);
            current.length += current.right.length + 1;
        } else if (cmp < 0) {
            current.left = put(current.left, node);
            current.length += current.left.length + 1;
        } else {
            current.value = node.value;
        }

        current.length = size(current.right) + size(current.left) + 1;

        return current;
    }

    private int size(MyNode<K, V> node) {
        if (node == null){
            return 0;
        } else {
            return node.length;
        }
    }

    public int size() {
        return size(root);
    }

    public V get(K key) {
    
    }

    public void delete(K key) {

    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}

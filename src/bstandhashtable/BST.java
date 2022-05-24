package bstandhashtable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST <K extends Comparable<K>, V> implements Iterable<K>{
    private Node root;

    private class Node{
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value){
        Node newNode = new Node(key, value);
        newNode.left = null;
        newNode.right = null;
        put(newNode);
    }

    private void put(Node newNode) {
        Node prev = null;
        Node parent = root;

        while (parent != null) {
            prev = parent;

            if (newNode.key.compareTo(parent.key) <= 0) {
                parent = parent.left;
            } else {
                parent = parent.right;
            }
        }

        if (prev == null) {
            root = newNode;
        } else if (newNode.key.compareTo(prev.key) <= 0) {
            prev.left = newNode;
        } else {
            prev.right = newNode;
        }

    }

    public V get(K key) {
        return (V) getNode(key, root);
    }

    private Node getNode(K key, Node current) {

        if (current == null || current.key.equals(key)) {
            return current;
        }

        if (current.key.compareTo(key) >= 0) {
            return getNode(key, current.left);
        } else {
            return getNode(key, current.right);
        }

    }

    private Node getNodeParent(Node curr, K key, Node parent) {
        if (curr == null) {
            return null;
        }

        if (curr.key.equals(key)) {
            return parent;
        }

        if (curr.key.compareTo(key) >= 0) {
            return getNodeParent(curr.left, key, curr);
        } else {
            return getNodeParent(curr.right, key, curr);
        }
    }

    public void delete(K key) {
        Node z = getNode(key, root);
        Node p = getNodeParent(root, key, root);

        if (z.left == null) {
            zReplace(z, z.right, p);
        } else if (z.right == null) {
            zReplace(z, z.left, p);
        } else {
            Node y = findMin(z.right);
            Node yParent = findMinParent(y, z.right);
            if (!yParent.equals(z)) {
                zReplace(y, y.right, yParent);
                y.right = z.right;
            }
            zReplace(z, y, p);
            y.left = z.left;
        }
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node findMinParent(Node node, Node parent) {
        while (parent.left != null) {

            if (parent.left.equals(node)) {
                break;
            }

            parent = parent.left;
        }
        return parent;
    }

    private void zReplace(Node first, Node firstRight, Node firstParent) {
        if (firstParent == null) {
            root = firstRight;
        } else if (first.equals(firstParent.left)) {
            firstParent.left = firstRight;
        } else {
            firstParent.right = firstRight;
        }
    }

    @Override
    public Iterator<K> iterator() {
        Queue<K> q = new LinkedList<>();
        ordered(root, q);
        return q.iterator();
    }

    private void ordered(Node x, Queue<K> q) {
        if (x == null) return;
        ordered(x.left, q);
        q.add(x.key);
        ordered(x.right, q);
    }
}

public class MyHashTable<K, V> {

    private HashNode<K, V>[] chainArray;
    private int capacity = 11;
    private int length = 0;
    private final float loadFactor = 0.75F;

    public MyHashTable(){
        chainArray = new HashNode[];
    }

    public MyHashTable(int initialCapacity){
        this.capacity = (int)(initialCapacity * loadFactor);
        chainArray = new HashNode[capacity];
    }

    public void put(K key, V value){
        int index =  hash(key.hashCode());
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (chainArray[index] != null) {
            newNode.next = chainArray[index];
        }
        chainArray[index] = newNode;

        length++;
    }

    private int hash(int hashcode){
        return (hashcode & 0x7FFFFFFF) % capacity;
    }

    public V get(K key){

    }

    public V remove(K key){

    }

    public boolean contains(V value){

    }

    public K getKey(V value){

    }
}

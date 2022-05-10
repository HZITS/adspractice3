public class MyHashTable<K, V> {

    private HashNode<K, V>[] chainArray;
    private int capacity = 11;
    private int length = 0;
    private final float loadFactor = 0.75F;
    private final float loadStart = 0.65F;
    private final float loadEnd = 0.85F;

    public MyHashTable(){
        chainArray = new HashNode[capacity];
    }

    public MyHashTable(int M){
        capacity = (int)(M * loadFactor);
        chainArray = new HashNode[capacity];
    }

    public void put(K key, V value){
        int index =  hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (chainArray[index] != null) {
            newNode.next = chainArray[index];
        }
        chainArray[index] = newNode;
        length++;

        if ((length / capacity) > loadEnd){
            reArrange();
        }
    }

    private void reArrange() {
        int oldCapacity = capacity;
        int newCapacity = (int) (length / loadStart);
        HashNode<K, V>[] newChainArray = new HashNode[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {

            while (chainArray[i] != null) {
                HashNode<K, V> node = new HashNode<>(chainArray[i].key, chainArray[i].value);
                int index = hash(node.key);

                if (newChainArray[index] != null) {
                    node.next = newChainArray[index];
                }

                chainArray[index] = node;
                chainArray[i] = chainArray[i].next;
            }

        }
        chainArray = newChainArray;
    }

    private int hash(K key){
        return ((key.hashCode() & 0x7FFFFFFF) % capacity);
    }

    public V get(K key){
        int iSecondary = hash(key);
        HashNode<K, V> head = chainArray[iSecondary];

        while (head != null) {

            if (head.key.equals(key)) {

                return head.value;

            }
            head = head.next;

        }
        return null;
    }

    public V remove(K key){
        int keyIndex = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> head = chainArray[keyIndex];

        while (head != null) {

            if (head.key.equals(key)) {

                break;

            }
            prev = head;
            head = head.next;

        }

        if(head == null){

            return null;

        }

        length--;

        if(prev != null){
            prev.next = head.next;
        } else {
            chainArray[keyIndex] = head.next;
        }

        return head.value;
    }

    public boolean contains(V value){
        return getKey(value) != null;
    }

    public K getKey(V value){
        for (int i = 0; i < capacity; i++) {

            HashNode<K, V> head = chainArray[i];

            while (head != null) {

                if (head.value.equals(value)) {
                    System.out.println(head.value);
                    return head.key;
                }

                head = head.next;

            }
        }
        return null;
    }
}

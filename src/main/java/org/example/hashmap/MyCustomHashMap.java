package org.example.hashmap;

public class MyCustomHashMap<K, V> {
    public static final int INITIAL_SIZE = 1<<4;
    public static final int MAX_SIZE = 1<<30;
    public Entry[] hashTable;

    public MyCustomHashMap() {
        hashTable = new Entry[INITIAL_SIZE];
    }

    public MyCustomHashMap(int capacity){
        int tableSize = tableSizeFor(capacity);
        hashTable = new Entry[tableSize];
    }
    final int tableSizeFor(int capacity){
        int n = capacity-1;
        n |= n>>>1;
        n|=n>>>2;
        n|= n>>>4;
        n|= n>>>8;
        n|= n>>>16;
        return (n<0) ? 1 : (n>=MAX_SIZE) ? MAX_SIZE : n+1;
    }
    public class Entry<K,V>{
        public K key;
        public V value;
        public Entry next;
        Entry(K k, V v){
            this.key = k;
            this.value = v;
        }
    }
    public void put(K key, V value){
        int hashCode = Math.abs(key.hashCode()) % hashTable.length;
        Entry node = hashTable[hashCode];
        if(node == null){
            Entry newNode = new Entry<>(key, value);
            hashTable[hashCode] = newNode;
        }
        else{
            Entry previousNode = node;
            while(node != null){
                if(node.key == key){
                    node.value = value;
                    return;
                }
                previousNode = node;
                node = node.next;
            }
            Entry newNode = new Entry(key, value);
            previousNode.next = newNode;
        }
    }

    public V get(K key){
        int hashCode = Math.abs(key.hashCode()) % hashTable.length;
        Entry node = hashTable[hashCode];
        while(node != null){
            if(node.key == key){
                return (V) node.value;
            }
            node = node.next;
        }
        return null;
    }
}

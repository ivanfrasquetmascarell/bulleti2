package com.ivanfrasquet.tema01;


public class MyHashMap<K, V> {
    private int size;
    private Node<K,V>[][] data;
    private int capacity;

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        data = new Node[capacity][capacity];
        size = 0;
    }

    private int hash(K key) {
        int hash = key.hashCode();
        hash = hash * 31;
        hash = hash & 0x7FFFFFFF;
        return hash % data.length;
    }

    public V put(K key, V value) {
        int column = hash(key);
        int i;
        for (i = 0; i < data[column].length; i++) {
            Node<K, V> node = data[column][i];
            if (node == null || node.getKey().equals(key)) {
                break;
            }
        }
        if (i == data[column].length) {
            resize();
        }
        Node<K, V> oldValue = data[column][i];
        data[column][i] = new Node<>(key, value);
        if (oldValue == null) {
            return null;
        }
        return oldValue.getValue();
    }

    public V get(K key) {
        int column = hash(key);
        for (int i = 0; i < data[0].length; i++) {
            Node<K, V> node = data[column][i];
            if (node == null) {
                return null;
            }
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    private void resize() {
        Node<K,V>[][] data2 = new Node[capacity][capacity + 1];
     for(int i = 0; i < data.length; i++) {
         if (data[i][0] !=null) {
             for (int n = 0; n < data[i].length; n++) {
                 if (data[i][n] == null) {
                     break;
                 }
                 data2[i][n] = data[i][n];
             }
         }
     }
        data = data2;
    }

    public V remove (K key) {
        int column = hash(key);
        Node<K, V> position = null;
        for (int i = 0; i < data[column].length; i++) {
            Node<K, V> node = data[column][i];
            if (position != null) {
                if(i != (data[column].length - 1)){
                    if (data[column][i + 1] != null) {
                        data[column][i] = data[column][i + 1];

                    } else {
                        data[column][i] = null;
                        break;
                    }
                } else data[column][i] = null;

            } else {
                if (node == null) {
                    System.out.println("Key no encontrada");
                    return null;
                }
                if (node.getKey().equals(key)) {
                        position = data[column][i];
                        if(i != (data[column].length - 1)){
                            if (data[column][i + 1] != null) {
                                data[column][i] = data[column][i + 1];
                            } else {
                                data[column][i] = null;
                            }
                        } else data[column][i] = null;

                }
            }
        }

        if (position != null) {
            return position.getValue();
        }
        System.out.println("Key no encontrada");
        return null;
    }

    static class Node<K, V> {
        private final K key;
        private final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}

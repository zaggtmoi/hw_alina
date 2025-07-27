package org.example.hw1;


public class SimpleHashMap<K, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    private Entry<K, V>[] buckets;
    private V nullKeyValue = null;

    public SimpleHashMap() {
        buckets = new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * return null if not found
     */
    public V get(K key) {
        if (key == null) { // case: null key
            return nullKeyValue;
        }

        int hash = key.hashCode();
        Entry<K, V> entry = getBucket(hash);

        if (entry == null) { // case: empty bucket
            return null;
        }

        while (!entry.compareKey(hash, key) && entry.hasNext()) { // search in bucket
            entry = entry.nextEntry;
        }

        // check last found entry & return
        return entry.compareKey(hash, key) ? entry.value : null;
    }

    public void put(K key, V value) {
        if (key == null) { // case: null key
            nullKeyValue = value;
            return;
        }

        int hash = key.hashCode();
        Entry<K, V> entry = getBucket(hash);

        if (entry == null) { // case: empty bucket
            setBucket(hash, new Entry<>(null, hash, key, value));
            return;
        }

        while (!entry.compareKey(hash, key) && entry.hasNext()) { // search in bucket
            entry = entry.nextEntry;
        }

        if (entry.compareKey(hash, key)) { // change value
            entry.value = value;
        } else { // new value
            entry.nextEntry = new Entry<>(entry, hash, key, value);
        }
    }

    public void remove(K key) {
        if (key == null) { // case: null key
            nullKeyValue = null;
            return;
        }

        int hash = key.hashCode();
        Entry<K, V> entry = getBucket(hash);

        if (entry == null) { // case: empty bucket
            return;
        }

        while (!entry.compareKey(hash, key) && entry.hasNext()) { // search in bucket
            entry = entry.nextEntry;
        }

        if (entry.compareKey(hash, key)) { // remove value
            if (entry.prevEntry == null) { // case: first entry in bucket
                setBucket(hash, entry.nextEntry);
            } else { // case: entry in linked list
                Entry<K,V> parent = entry.prevEntry;
                parent.nextEntry = entry.nextEntry;
            }
        }
        // if nothing found - nothing happened
    }

    private Entry<K, V> getBucket(int hash) {
        return buckets[hash % DEFAULT_INITIAL_CAPACITY];
    }

    private void setBucket(int hash, Entry<K, V> entry) {
        buckets[hash % DEFAULT_INITIAL_CAPACITY] = entry;
    }

    private static class Entry<K, V> {
        final int hash;
        final K key;
        V value;

        Entry<K,V> prevEntry;
        Entry<K,V> nextEntry;

        public Entry(Entry<K, V> prev, int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public boolean hasNext() {
            return nextEntry != null;
        }

        public boolean compareKey(int hash, K key) {
            return (this.hash == hash) && (this.key.equals(key));
        }
    }
}

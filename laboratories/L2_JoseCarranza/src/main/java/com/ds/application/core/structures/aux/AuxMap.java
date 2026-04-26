package com.ds.application.core.structures.aux;

/**
 * Simple map-like structure backed by a SimpleList of Entry<K,V>.
 * Intended as a lightweight auxiliary replacement for java.util.Map
 * in this codebase.
 */
public class AuxMap<K, V> {
    private SimpleList<Entry<K, V>> entries;

    public AuxMap() {
        this.entries = new SimpleList<>();
    }

    public V put(K key, V value) {
        for (int i = 0; i < entries.size(); i++) {
            Entry<K, V> e = entries.get(i);
            if ((key == null && e.getKey() == null) || (key != null && key.equals(e.getKey()))) {
                V old = e.getValue();
                e.setValue(value);
                return old;
            }
        }
        entries.add(new Entry<>(key, value));
        return null;
    }

    public V get(K key) {
        for (int i = 0; i < entries.size(); i++) {
            Entry<K, V> e = entries.get(i);
            if ((key == null && e.getKey() == null) || (key != null && key.equals(e.getKey()))) {
                return e.getValue();
            }
        }
        return null;
    }

    public V getOrDefault(K key, V defaultValue) {
        V v = get(key);
        return v == null ? defaultValue : v;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return entries.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public SimpleList<Entry<K, V>> entryList() {
        return entries;
    }
}

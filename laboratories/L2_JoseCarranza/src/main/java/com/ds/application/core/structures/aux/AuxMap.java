package com.ds.application.core.structures.aux;

/**
 * Estructura auxiliar tipo mapa (clave-valor) implementada sobre una
 * {@link SimpleList} de {@link Entry}. Esta implementación es intencionadamente
 * simple y está pensada para usos internos del módulo core donde se
 * desee evitar dependencias directas a {@code java.util.Map}.
 *
 * @param <K> tipo de las claves
 * @param <V> tipo de los valores
 */
public class AuxMap<K, V> {
    private SimpleList<Entry<K, V>> entries;

    public AuxMap() {
        this.entries = new SimpleList<>();
    }

    /** Inserta o reemplaza la entrada asociada a la clave. */
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

    /** Obtiene el valor asociado a la clave, o null si no existe. */
    public V get(K key) {
        for (int i = 0; i < entries.size(); i++) {
            Entry<K, V> e = entries.get(i);
            if ((key == null && e.getKey() == null) || (key != null && key.equals(e.getKey()))) {
                return e.getValue();
            }
        }
        return null;
    }

    /** Obtiene el valor o devuelve un valor por defecto si no existe. */
    public V getOrDefault(K key, V defaultValue) {
        V v = get(key);
        return v == null ? defaultValue : v;
    }

    /** Indica si la clave está presente en el mapa. */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /** Devuelve el número de entradas almacenadas. */
    public int size() {
        return entries.size();
    }

    /** Indica si el mapa está vacío. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Devuelve la lista interna de entradas para iteración eficiente. */
    public SimpleList<Entry<K, V>> entryList() {
        return entries;
    }
}

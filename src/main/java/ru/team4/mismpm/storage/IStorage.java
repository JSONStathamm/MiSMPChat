package ru.team4.mismpm.storage;

public interface IStorage<K, V> {

    void save(K key, V value);
    V get(K key);
    void remove(K key);
    boolean contains(K key);

}

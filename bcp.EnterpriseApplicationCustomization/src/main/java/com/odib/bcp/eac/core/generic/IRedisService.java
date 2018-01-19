package com.odib.bcp.eac.core.generic;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author chensong
 * @discription
 * @datetime 2017-06-02
 */
public interface IRedisService<K, V> {

    //String
    public void set(K key, V value);

    public void set(K key, V value, long timeout, TimeUnit unit);

    public V get(K key);

    public V getAndSet(K key, V value);

    public void multiSet(Map<? extends K, ? extends V> map);

    public List<V> multiGet(Collection<K> collection);

    public boolean expire(K key, long timeout, TimeUnit unit);

    Long increment(String key, Long delta);

    //List
    public void addList(K key, V value);

    public V getList(K key);


    //Hash
    public void putAll(K key, Map value);

    <HK> void put(K key, HK field, V val);

    <HK> V find(K key, HK field);

    <HK> void remove(K key, HK field);

    public Map findAll(K key);


    //SortedSet
    public Boolean zadd(K key, V val, double score);

    public Set<V> zRange(K key, long start, long end);

    public Set<V> zRange(K key, long offset);

    public Long zRemoveRangeByScore(K key, double min, double max);

    public Set<V> zRangeByScore(K key, double min, double max);



    //Other
    public byte[] serialize(V o);

    public V deserialize(byte[] bytes);

    public void delete(K key);

    public Set<V> keys(Object o);
}

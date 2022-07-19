package com.wuxianggujun.toolbox.cache;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentCache
 * Tomcat 中的 ConcurrentCache 使用了 WeakHashMap 来实现缓存功能。
 * <p>
 * ConcurrentCache 采取的是分代缓存：
 * <p>
 * 经常使用的对象放入 eden 中，eden 使用 ConcurrentHashMap 实现，不用担心会被回收（伊甸园）；
 * 不常用的对象放入 longterm，longterm 使用 WeakHashMap 实现，这些老对象会被垃圾收集器回收。
 * 当调用 get() 方法时，会先从 eden 区获取，如果没有找到的话再到 longterm 获取，当从 longterm 获取到就把对象放入 eden 中，从而保证经常被访问的节点不容易被回收。
 * 当调用 put() 方法时，如果 eden 的大小超过了 size，那么就将 eden 中的所有对象都放入 longterm 中，利用虚拟机回收掉一部分不经常使用的对象。
 *
 * @param <k>
 * @param <V>
 */
public final class ConcurrentCache<k, V> {
    private final int size;

    private final Map<k, V> eden;

    private final Map<k, V> longTerm;

    public ConcurrentCache(int size) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longTerm = new WeakHashMap<>(size);
    }

    public V get(k key) {
        V v = this.eden.get(key);
        if (v == null) {
            v = this.longTerm.get(key);
            if (v != null)
                this.eden.put(key, v);
        }
        return v;
    }

    public void put(k key, V v) {
        if (this.eden.size() >= size) {
            this.longTerm.putAll(this.eden);
            this.eden.clear();
        }
        this.eden.put(key, v);
    }
    
}
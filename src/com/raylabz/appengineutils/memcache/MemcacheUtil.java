package com.raylabz.appengineutils.memcache;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class MemcacheUtil {

    public static final MemcacheService SERVICE =  MemcacheServiceFactory.getMemcacheService();

    public static void put(Object key, Object value) {
        SERVICE.put(key, value);
    }

    public static Object get(Object key) {
        return SERVICE.get(key);
    }

    public static boolean delete(Object key) {
        return SERVICE.delete(key);
    }

    public static boolean contains(Object key) {
        return SERVICE.contains(key);
    }

}

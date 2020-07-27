package com.raylabz.appengineutils.memcache;

import com.google.appengine.api.memcache.*;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Memcache {

    private static final Gson gson = new Gson();

    public static <T> T get(Class<T> aClass, String id) {
        return gson.fromJson((String) (MemcacheServiceFactory.getMemcacheService(aClass.getName()).get(id)), aClass);
    }

    public static <T> boolean contains(Class<T> aClass, String id) {
        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).contains(id);
    }

    public static <T> Map<String, T> getAll(Class<T> aClass, Collection<String> ids) {
        final Map<String, Object> all = MemcacheServiceFactory.getMemcacheService(aClass.getName()).getAll(ids);
        final HashMap<String, T> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : all.entrySet()) {
            map.put(entry.getKey(), gson.fromJson((String) entry.getValue(), aClass));
        }
        return map;
    }

    public static <T> void put(String id, T object, Expiration expiration) {
        MemcacheServiceFactory.getMemcacheService(object.getClass().getName()).put(id, gson.toJson(object), expiration);
    }

    public static <T> void put(String id, T object) {
        MemcacheServiceFactory.getMemcacheService(object.getClass().getName()).put(id, gson.toJson(object));
    }

    //Is there any way to do these with Gson??

//    public static <T> void putAll(Class<T> aClass, Map<String, T> map, Expiration expiration) {
//        MemcacheServiceFactory.getMemcacheService(aClass.getName()).putAll(map, expiration);
//    }
//
//    public static <T> void putAll(Class<T> aClass, Map<String, T> map) {
//        MemcacheServiceFactory.getMemcacheService(aClass.getName()).putAll(map);
//    }

//    public static <T> boolean putIfUntouched(Class<T> aClass, String id, MemcacheService.IdentifiableValue identifiableValue, T newValue, Expiration expiration) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).putIfUntouched(id, identifiableValue, newValue, expiration);
//    }
//
//    public static <T> boolean putIfUntouched(Class<T> aClass, String id, MemcacheService.IdentifiableValue identifiableValue, T newValue) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).putIfUntouched(id, identifiableValue, newValue);
//    }
//
//    public static <T> Set<String> putIfUntouched(Class<T> aClass, Map<String, MemcacheService.CasValues> map) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).putIfUntouched(map);
//    }
//
//    public static <T> Set<String> putIfUntouched(Class<T> aClass, Map<String, MemcacheService.CasValues> map, Expiration expiration) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).putIfUntouched(map, expiration);
//    }

    public static <T> boolean delete(Class<T> aClass, String id) {
        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).delete(id);
    }

    public static <T> boolean delete(Class<T> aClass, String id, long noReAddMS) {
        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).delete(id, noReAddMS);
    }

//    public static <T> Set<String> deleteAll(Class<T> aClass, Collection<String> collection) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).deleteAll(collection);
//    }
//
//    public static <T> Set<String> deleteAll(Class<T> aClass, Collection<String> collection, long noReAddMS) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).deleteAll(collection, noReAddMS);
//    }
//
//    public static <T> Long increment(Class<T> aClass, String id, long incrementation) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).increment(id, incrementation);
//    }
//
//    public static <T> Long increment(Class<T> aClass, String id, long incrementation, Long initialValue) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).increment(id, incrementation, initialValue);
//    }
//
//    public static <T> Map<String, Long> incrementAll(Class<T> aClass, Collection<String> collection, long incrementation) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).incrementAll(collection, incrementation);
//    }
//
//    public static <T> Map<String, Long> incrementAll(Class<T> aClass, Collection<String> collection, long incrementation, Long initialValue) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).incrementAll(collection, incrementation, initialValue);
//    }
//
//    public static <T> Map<String, Long> incrementAll(Class<T> aClass, Map<String, Long> map) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).incrementAll(map);
//    }
//
//    public static <T> Map<String, Long> incrementAll(Class<T> aClass, Map<String, Long> map, Long initialValue) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).incrementAll(map, initialValue);
//    }

    public static <T> void clearAll(Class<T> aClass) {
        MemcacheServiceFactory.getMemcacheService(aClass.getName()).clearAll();
    }

//    public static <T> Stats getStatistics(Class<T> aClass) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).getStatistics();
//    }
//
//    public static <T> ErrorHandler getErrorHandler(Class<T> aClass) {
//        return MemcacheServiceFactory.getMemcacheService(aClass.getName()).getErrorHandler();
//    }
//
//    public static <T> void setErrorHandler(Class<T> aClass, ErrorHandler errorHandler) {
//        MemcacheServiceFactory.getMemcacheService(aClass.getName()).setErrorHandler(errorHandler);
//    }

    public static <T> MemcacheService getCache(Class<T> aClass) {
        return MemcacheServiceFactory.getMemcacheService(aClass.getName());
    }

    public static MemcacheService getMainCache() {
        return MemcacheServiceFactory.getMemcacheService();
    }

}

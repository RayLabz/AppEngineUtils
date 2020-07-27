package com.raylabz.appengineutils.memcache;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

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

    public static <T> ArrayList<T> list(Class<T> aClass) {
        final MemcacheService mainCache = Memcache.getMainCache();
        final ArrayList<String> list = (ArrayList<String>) mainCache.get("entries-" + aClass.getName());
        final ArrayList<T> items = new ArrayList<>();

        for (String id : list) {
            final T object = aClass.cast(MemcacheServiceFactory.getMemcacheService(aClass.getName()).get(id));
            items.add(object);
        }
        return items;
    }

    public static <T> ArrayList<String> listIDs(Class<T> aClass) {
        final MemcacheService mainCache = Memcache.getMainCache();
        ArrayList<String> list = (ArrayList<String>) mainCache.get("entries-" + aClass.getName());
        if (list != null) {
            return list;
        }
        return new ArrayList<>();
    }

    public static <T> void forAll(Class<T> aClass, Consumer<? super T> action) {
        final MemcacheService mainCache = Memcache.getMainCache();
        final ArrayList<String> list = (ArrayList<String>) mainCache.get("entries-" + aClass.getName());
        if (list != null) {
            for (String id : list) {
                final T object = aClass.cast(get(aClass, id));
                action.accept(object);
            }
        }
    }

    public static <T> void put(String id, T object, Expiration expiration) {
        final MemcacheService mainCache = Memcache.getMainCache();
        ArrayList<String> list = (ArrayList<String>) mainCache.get("entries-" + object.getClass().getName());
        if (list == null) list = new ArrayList<>();
        list.add(id);
        mainCache.put("entries-" + object.getClass().getName(), object);
        MemcacheServiceFactory.getMemcacheService(object.getClass().getName()).put(id, gson.toJson(object), expiration);
    }

    public static <T> void put(String id, T object) {
        final MemcacheService mainCache = Memcache.getMainCache();
        ArrayList<String> list = (ArrayList<String>) mainCache.get("entries-" + object.getClass().getName());
        if (list == null) list = new ArrayList<>();
        list.add(id);
        mainCache.put("entries-" + object.getClass().getName(), list);
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

    public static <T> void delete(Class<T> aClass, String id) {
        final MemcacheService mainCache = Memcache.getMainCache();
        ArrayList<String> idList = (ArrayList<String>) mainCache.get("entries-" + aClass.getName());
        for (String itemID : idList) {
            if (itemID.equals(id)) {
                MemcacheServiceFactory.getMemcacheService(aClass.getName()).delete(id);
            }
        }
        idList = new ArrayList<>();
        mainCache.put("entries-" + aClass.getName(), idList);
    }

    public static <T> void delete(Class<T> aClass, String id, long noReAddMS) {
        final MemcacheService mainCache = Memcache.getMainCache();
        final ArrayList<String> idList = (ArrayList<String>) mainCache.get("entries-" + aClass.getName());
        for (String itemID : idList) {
            if (itemID.equals(id)) {
                idList.remove(id);
                MemcacheServiceFactory.getMemcacheService(aClass.getName()).delete(id, noReAddMS);
            }
        }
        mainCache.put("entries-" + aClass.getName(), idList);
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
        getMainCache().delete("entries-" + aClass.getName());
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

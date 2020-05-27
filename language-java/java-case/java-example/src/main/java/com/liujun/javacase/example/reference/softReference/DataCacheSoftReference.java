package com.liujun.javacase.example.reference.softReference;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象缓存的软引用
 *
 * <p>用来做应用程序的缓存，当应用程序的内存空间足够时，可向内容中加入缓存
 *
 * <p>当应用程序内存不足时，此数据将会被会内存回收
 *
 * @author liujun
 * @version 0.0.1
 */
public class DataCacheSoftReference {

  private Map<String, SoftReference<Object>> dataCache = new ConcurrentHashMap<>();

  private static final DataCacheSoftReference INSTANCE = new DataCacheSoftReference();

  public static DataCacheSoftReference getInstance() {
    return INSTANCE;
  }

  public void put(String key, Object value) {
    dataCache.put(key, new SoftReference<>(value));
    // 去掉强引用
    value = null;
  }

  public Object get(String key) {
    SoftReference<Object> dataCacheSoftRef = dataCache.get(key);
    return dataCacheSoftRef.get();
  }


}

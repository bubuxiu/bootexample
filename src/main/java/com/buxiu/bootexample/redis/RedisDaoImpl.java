package com.buxiu.bootexample.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Repository
public class RedisDaoImpl {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 判断当前key是否存在
     *
     * @param k
     * @return
     */
    public boolean hasKey(String k) {
        return stringRedisTemplate.hasKey(k);
    }

    /**
     * 判断当前hash变量是否存在
     *
     * @param hk
     * @param ok
     * @return
     */
    public boolean hasKey(String hk, String ok) {
        return stringRedisTemplate.opsForHash().hasKey(hk, ok);
    }

    /**
     * 判断set集合中是否存在指定value值
     *
     * @param k
     * @param value
     * @return
     */
    public boolean existValue(String k, String value) {
        return stringRedisTemplate.opsForSet().isMember(k, value);
    }

    /**
     * 查询指定key的值
     *
     * @param k
     * @return
     */
    public String getString(String k) {
        return stringRedisTemplate.opsForValue().get(k);
    }

    /**
     * 查询指定key的map集合
     *
     * @param k
     * @return
     */
    public Map<Object, Object> getMap(String k) {
        return stringRedisTemplate.opsForHash().entries(k);
    }

    /**
     * 获取指定hash中的key的返回值
     *
     * @param hk
     * @param k
     * @return
     */
    public String getString(String hk, String k) {
        return String.valueOf(stringRedisTemplate.opsForHash().get(hk, k));
    }

    /**
     * 获取map中key的对象
     *
     * @param hk
     * @param k
     * @return
     */
    public Object getObject(String hk, String k) {
        return readObject(String.valueOf(getString(hk, k)));
    }

    /**
     * 获取set集合
     *
     * @param k
     * @return
     */
    public Set<String> getSet(String k) {
        return stringRedisTemplate.opsForSet().members(k);
    }

    /**
     * 获取key的过期时间
     *
     * @param k
     * @param timeUnit
     * @return
     */
    public String getExpire(String k, TimeUnit timeUnit) {
        return String.valueOf(stringRedisTemplate.getExpire(k, timeUnit));
    }

    /**
     * 存入单个变量并指定过期时间
     *
     * @param k
     * @param v
     * @param time
     * @param timeUnit
     */
    public void saveKey(String k, String v, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(k, v);
        if (time > 0)
            stringRedisTemplate.expire(k, time, timeUnit);
    }

    /**
     * 存入多个变量并指定过期时间
     *
     * @param key
     * @param map
     * @param time
     * @param timeUnit
     */
    public void saveKey(String key, Map<Object, Object> map, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForHash().putAll(key, map);
        if (time > 0)
            stringRedisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 存入hash并指定过期时间
     *
     * @param hk
     * @param k
     * @param v
     * @param time
     * @param timeUnit
     */
    public void saveKey(String hk, String k, String v, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForHash().put(hk, k, v);
        if (time > 0)
            stringRedisTemplate.expire(k, time, timeUnit);
    }

    /**
     * 存入集合
     *
     * @param k
     * @param value
     */
    public void saveSet(String k, String... value) {
        stringRedisTemplate.opsForSet().add(k, value);
    }

    /**
     * 存入对象并指定过期时间
     *
     * @param k
     * @param v
     * @param time
     * @param timeUnit
     */
    public void saveObj(String k, Object v, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(k, writeObject(v));
        if (time > 0)
            stringRedisTemplate.expire(k, time, timeUnit);
    }

    /**
     * 修改指定key的值
     *
     * @param k
     * @param num
     */
    public void incKey(String k, int num) {
        stringRedisTemplate.boundValueOps(k).increment(num);

    }

    /**
     * 修改指定hash的key的值
     *
     * @param hk
     * @param k
     * @param num
     */
    public void incKey(String hk, String k, double num) {
        stringRedisTemplate.boundHashOps(hk).increment(k, num);
    }

    /**
     * 更新key的过期时间
     *
     * @param k
     * @param time
     * @param timeUnit
     */
    public void expire(String k, long time, TimeUnit timeUnit) {
        stringRedisTemplate.expire(k, time, timeUnit);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除hash
     *
     * @param hk
     * @param ok
     */
    public void deleteKey(String hk, String ok) {
        stringRedisTemplate.opsForHash().delete(hk, ok);
    }

    /**
     * 序列化对象为String字符串
     *
     * @param o Object
     * @return String
     * @throws Exception
     */
    public static String writeObject(Object o) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            oos.flush();
            oos.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Base64.getEncoder().encodeToString(bos.toByteArray());
    }

    /**
     * 反序列化字符串为对象
     *
     * @param object String
     * @return
     * @throws Exception
     */
    public static Object readObject(String object) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        Object o = null;
        try {
            bis = new ByteArrayInputStream(Base64.getDecoder().decode(object));
            ois = new ObjectInputStream(bis);
            o = ois.readObject();
            bis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}


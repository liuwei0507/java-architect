package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisBasic {
    public static void main(String[] args) {
        testJedisCli();

        testJedisPool();


    }

    private static void testJedisPool() {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get("mytest1");
        System.out.println(result);
        jedis.close();
        jedisPool.close();
    }


    private static void testJedisCli() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("mytest1", "hello world, this is jedis client");
        // 从jedis中取值
        String result = jedis.get("mytest");
        System.out.println(result);
        jedis.close();
    }

}

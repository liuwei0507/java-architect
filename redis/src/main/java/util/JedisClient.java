package util;

import redis.clients.jedis.Jedis;

public class JedisClient {

    public static Jedis getJedisClient(String host, Integer port) {
        return new Jedis(host, port);
    }
}

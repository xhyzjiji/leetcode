package com.meituan.xhyzjiji.number.framework.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class SimpleRW {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();

        System.out.println(getData(client, "/zkcmdtest"));
        System.out.println(setData(client, "/zkcmdtest", "hello, world".getBytes()));
        System.out.println(createPersistentEmptyNode(client, "/zkcmdtest/emptyNode"));
        System.out.println(createPersistentNode(client, "/zkcmdtest/notEmptyNode", "hi, zk".getBytes()));
    }

    private static String createPersistentEmptyNode(CuratorFramework client, String path) throws Exception {
        return client.create().forPath(path);
    }

    private static String createPersistentNode(CuratorFramework client, String path, byte[] content) throws Exception {
        return client.create().forPath(path, content);
    }

    private static String getData(CuratorFramework client, String path) throws Exception {
        return new String(client.getData().forPath(path));
    }

    private static Stat setData(CuratorFramework client, String path, byte[] content) throws Exception {
        return client.setData().forPath(path, content);
    }

}

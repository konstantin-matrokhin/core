package net.enot.darta.data;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Енот on 23.04.2017.
 */
public class Proxy {

    private static Map<Channel, Proxy> proxys = new ConcurrentHashMap<>();

    public static Map<Channel, Proxy> getProxys(){
        return proxys;
    }

    public static boolean containsProxy(Channel channel){
        return proxys.containsKey(channel);
    }

    public static Proxy getProxy(Channel channel){
        return proxys.get(channel);
    }

    private Channel channel;
    private String name;
    private String adress;
    private int online = 0;

    public Proxy(Channel channel, String name){
        this.channel = channel;
        this.name = name;
        adress = ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress();
        proxys.put(channel, this);
    }

    public Channel getChannel(){
        return channel;
    }

    public String getName(){
        return name;
    }

    public int getOnline(){
        return online;
    }

    public void addOnline(){
        online++;
    }

    public void subtractOnline(){
        online--;
    }

    public String getAdress(){
        return adress;
    }

    public void remove(){
        proxys.remove(channel);
    }
}

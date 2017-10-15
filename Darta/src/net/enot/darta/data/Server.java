package net.enot.darta.data;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Енот on 23.04.2017.
 */
public class Server {

    private static Map<Channel, Server> servers = new ConcurrentHashMap<>();

    public static Map<Channel, Server> getServers(){
        return servers;
    }

    public static Server getServerByName(String name){
        for (Server server : servers.values()){
            if (server.getName().equals(name)){
                return server;
            }
        }
        return null;
    }

    public static boolean containsServer(Channel channel){
        return servers.containsKey(channel);
    }

    public static Server getServer(Channel channel){
        return servers.get(channel);
    }

    private Channel channel;
    private String name;
    private String adress;
    private int port;
    private int online = 0;

    public Server(Channel channel, String name, int port){
        this.channel = channel;
        this.name = name;
        this.port = port;
        adress = ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress();
        servers.put(channel, this);
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

    public int getPort(){
        return port;
    }

    public void remove(){
        servers.remove(channel);
    }
}

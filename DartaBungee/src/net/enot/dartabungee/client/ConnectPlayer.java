package net.enot.dartabungee.client;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartasystem.packets.player.PlayerLoginOutPacket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.Map;

/**
 * Created by Енот on 23.04.2017.
 */
public class ConnectPlayer {

    private static Map<String, ConnectPlayer> players = new ConcurrentHashMap<>();

    public static boolean containsConnectPlayer(String name){
        return players.containsKey(name.toLowerCase());
    }

    public static ConnectPlayer getConnectPlayer(String name){
        return players.get(name.toLowerCase());
    }

    private String name;
    private boolean canJoin;
    private CountDownLatch cdl = new CountDownLatch(1);

    public ConnectPlayer(String name) throws InterruptedException {
        this.name = name.toLowerCase();
        DartaBungee.getInstance().getDartaClient().write(new PlayerLoginOutPacket(name));
        players.put(this.name, this);
        cdl.await();
    }

    public void countDown(boolean canJoin){
        this.canJoin = canJoin;
        cdl.countDown();
    }

    public boolean isCanJoin(){
        return canJoin;
    }

    public void remove(){
        players.remove(name);
    }
}

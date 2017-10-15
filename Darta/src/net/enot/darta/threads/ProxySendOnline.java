package net.enot.darta.threads;

import io.netty.channel.Channel;
import net.enot.darta.data.Proxy;
import net.enot.dartasystem.packets.proxy.ProxySendOnlinePacket;

import java.util.concurrent.TimeUnit;

/**
 * Created by Енот on 24.04.2017.
 */
public class ProxySendOnline extends Thread {

    public void run(){
        while (!Thread.interrupted()){
            int online = 0;
            for (Proxy proxy : Proxy.getProxys().values()){
                online += proxy.getOnline();
            }
            for (Channel channel : Proxy.getProxys().keySet()){
                channel.writeAndFlush(new ProxySendOnlinePacket(online));
            }
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException ignored) {
            }
        }
    }

}

package net.enot.dartasystem.packets.proxy;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;

import java.io.IOException;

/**
 * Created by Енот on 24.04.2017.
 */
public class ProxySendOnlinePacket implements Packet {

    private int online;

    public ProxySendOnlinePacket(){}

    public ProxySendOnlinePacket(int online){
        this.online = online;
    }

    public int getOnline(){
        return online;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        online = byteBuf.readInt();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeInt(online);
    }
}

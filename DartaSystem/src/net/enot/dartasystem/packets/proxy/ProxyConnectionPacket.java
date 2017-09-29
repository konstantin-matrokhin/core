package net.enot.dartasystem.packets.proxy;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 23.04.2017.
 */
public class ProxyConnectionPacket implements Packet {

    private String name;

    public ProxyConnectionPacket(){}

    public ProxyConnectionPacket(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        name = ByteBufUtil.readString(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, name);
    }
}

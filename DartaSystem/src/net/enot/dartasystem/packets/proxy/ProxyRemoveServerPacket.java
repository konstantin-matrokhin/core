package net.enot.dartasystem.packets.proxy;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 27.04.2017.
 */
public class ProxyRemoveServerPacket implements Packet{

    private String name;

    public ProxyRemoveServerPacket(){}

    public ProxyRemoveServerPacket(String name){
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
package net.enot.dartasystem.packets.player;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 23.04.2017.
 */
public class PlayerRedirectPacket implements Packet {

    private String name;
    private String server;

    public PlayerRedirectPacket(){}

    public PlayerRedirectPacket(String name, String server){
        this.name = name;
        this.server = server;
    }

    public String getName(){
        return name;
    }

    public String getServer(){
        return server;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        name = ByteBufUtil.readString(byteBuf);
        server = ByteBufUtil.readString(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, name.toLowerCase());
        ByteBufUtil.writeString(byteBuf, server);
    }
}

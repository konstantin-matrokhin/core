package net.enot.dartasystem.packets.player;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 23.04.2017.
 */
public class PlayerLoginOutPacket implements Packet{

    private String name;

    public String getName(){
        return name;
    }

    public PlayerLoginOutPacket(){}

    public PlayerLoginOutPacket(String name){
        this.name = name;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        name = ByteBufUtil.readString(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, name.toLowerCase());
    }

}

package net.enot.dartasystem.packets.player;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 23.04.2017.
 */
public class PlayerLoginInPacket implements Packet {

    private String name;
    private boolean canJoin;

    public PlayerLoginInPacket(){}

    public String getName(){
        return name;
    }

    public boolean isCanJoin(){
        return canJoin;
    }

    public PlayerLoginInPacket(String name, boolean canJoin){
        this.name = name;
        this.canJoin = canJoin;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        name = ByteBufUtil.readString(byteBuf);
        canJoin = byteBuf.readBoolean();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, name);
        byteBuf.writeBoolean(canJoin);
    }

}

package net.enot.dartasystem.packets.commands;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 27.04.2017.
 */
public class OnlineCommandOutPacket implements Packet {

    private String playerName;
    private String server;

    public OnlineCommandOutPacket(){}

    public OnlineCommandOutPacket(String playerName, String server){
        this.playerName = playerName;
        this.server = server;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getServer(){
        return server;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        playerName = ByteBufUtil.readString(byteBuf);
        server = ByteBufUtil.readString(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, playerName.toLowerCase());
        ByteBufUtil.writeString(byteBuf, server.toLowerCase());
    }
}

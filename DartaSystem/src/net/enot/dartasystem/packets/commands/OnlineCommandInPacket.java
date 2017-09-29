package net.enot.dartasystem.packets.commands;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 27.04.2017.
 */
public class OnlineCommandInPacket implements Packet {

    private String playerName;
    private String server;
    private boolean created;
    private int online;

    public OnlineCommandInPacket(){}

    public OnlineCommandInPacket(String playerName, String server, boolean created){
        this.playerName = playerName;
        this.server = server;
        this.created = created;
    }

    public OnlineCommandInPacket(String playerName, String server, boolean created, int online){
        this.playerName = playerName;
        this.server = server;
        this.created = created;
        this.online = online;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getServer(){
        return server;
    }

    public boolean isCreated(){
        return created;
    }

    public int getOnline(){
        return online;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        playerName = ByteBufUtil.readString(byteBuf);
        server = ByteBufUtil.readString(byteBuf);
        created = byteBuf.readBoolean();
        if (created) {
            online = byteBuf.readInt();
        }
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, playerName);
        ByteBufUtil.writeString(byteBuf, server);
        byteBuf.writeBoolean(created);
        if (created) {
            byteBuf.writeInt(online);
        }
    }
}

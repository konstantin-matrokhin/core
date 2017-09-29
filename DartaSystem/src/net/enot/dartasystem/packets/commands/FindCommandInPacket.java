package net.enot.dartasystem.packets.commands;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 24.04.2017.
 */
public class FindCommandInPacket implements Packet {

    private boolean online;
    private String sender;
    private String find;
    private String server;

    public FindCommandInPacket(){}

    public FindCommandInPacket(boolean online, String sender, String find, String server){
        this.online = online;
        this.sender = sender;
        this.find = find;
        this.server = server;
    }

    public FindCommandInPacket(boolean online, String sender, String find){
        this.online = online;
        this.sender = sender;
        this.find = find;
    }

    public boolean isOnline(){
        return online;
    }

    public String getSender(){
        return sender;
    }

    public String getFind(){
        return find;
    }

    public String getServer(){
        return server;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        online = byteBuf.readBoolean();
        sender = ByteBufUtil.readString(byteBuf);
        find = ByteBufUtil.readString(byteBuf);
        if (online){
            server = ByteBufUtil.readString(byteBuf);
        }
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeBoolean(online);
        ByteBufUtil.writeString(byteBuf, sender);
        ByteBufUtil.writeString(byteBuf, find);
        if (online){
            ByteBufUtil.writeString(byteBuf, server);
        }
    }
}

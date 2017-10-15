package net.enot.dartasystem.packets.player;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 26.04.2017.
 */
public class SendPlayerOutPacket implements Packet {

    private boolean online;
    private String sender;
    private String sendPlayer;
    private String server;
    private boolean send;

    public SendPlayerOutPacket(){}

    public SendPlayerOutPacket(boolean online, String sender, String sendPlayer, String server){
        this.online = online;
        this.sender = sender;
        this.sendPlayer = sendPlayer;
        this.server = server;
    }

    public SendPlayerOutPacket(boolean online, String sender, String sendPlayer, String server, boolean send){
        this.online = online;
        this.sender = sender;
        this.sendPlayer = sendPlayer;
        this.server = server;
        this.send = send;
    }

    public boolean isOnline(){
        return online;
    }

    public String getSender(){
        return sender;
    }

    public String getSendPlayer(){
        return sendPlayer;
    }

    public String getServer(){
        return server;
    }

    public boolean isSend(){
        return send;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        online = byteBuf.readBoolean();
        sender = ByteBufUtil.readString(byteBuf);
        sendPlayer = ByteBufUtil.readString(byteBuf);
        server = ByteBufUtil.readString(byteBuf);
        if (online){
            send = byteBuf.readBoolean();
        }
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeBoolean(online);
        ByteBufUtil.writeString(byteBuf, sender);
        ByteBufUtil.writeString(byteBuf, sendPlayer);
        ByteBufUtil.writeString(byteBuf, server);
        if (online){
            byteBuf.writeBoolean(send);
        }
    }

}

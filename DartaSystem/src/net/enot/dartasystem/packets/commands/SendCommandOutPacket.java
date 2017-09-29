package net.enot.dartasystem.packets.commands;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 26.04.2017.
 */
public class SendCommandOutPacket implements Packet {

    private String sender;
    private String sendPlayer;
    private String server;

    public SendCommandOutPacket(){}

    public SendCommandOutPacket(String sender, String sendPlayer, String server){
        this.sender = sender;
        this.sendPlayer = sendPlayer;
        this.server = server;
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

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        sender = ByteBufUtil.readString(byteBuf);
        sendPlayer = ByteBufUtil.readString(byteBuf);
        server = ByteBufUtil.readString(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, sender.toLowerCase());
        ByteBufUtil.writeString(byteBuf, sendPlayer.toLowerCase());
        ByteBufUtil.writeString(byteBuf, server.toLowerCase());
    }
}

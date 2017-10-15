package net.enot.dartasystem.packets.commands;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 24.04.2017.
 */
public class FindCommandOutPacket implements Packet {

    private String sender;
    private String find;

    public FindCommandOutPacket(){}

    public FindCommandOutPacket(String sender, String find){
        this.sender = sender;
        this.find = find;
    }

    public String getSender(){
        return sender;
    }

    public String getFind(){
        return find;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        sender = ByteBufUtil.readString(byteBuf);
        find = ByteBufUtil.readString(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, sender.toLowerCase());
        ByteBufUtil.writeString(byteBuf, find.toLowerCase());
    }
}

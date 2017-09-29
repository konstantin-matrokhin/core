package net.enot.dartasystem.packets.commands;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 24.04.2017.
 */
public class AlertCommandPacket implements Packet {

    private String message;

    public AlertCommandPacket(){}

    public AlertCommandPacket(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        message = ByteBufUtil.readString(byteBuf);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, message);
    }

}

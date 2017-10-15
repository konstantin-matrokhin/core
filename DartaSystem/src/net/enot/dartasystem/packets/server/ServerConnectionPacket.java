package net.enot.dartasystem.packets.server;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 27.04.2017.
 */
public class ServerConnectionPacket implements Packet {

    private String name;
    public int port;

    public ServerConnectionPacket(){}

    public ServerConnectionPacket(String name, int port){
        this.name = name;
        this.port = port;
    }

    public String getName(){
        return name;
    }

    public int getPort(){
        return port;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        name = ByteBufUtil.readString(byteBuf);
        port = byteBuf.readInt();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, name);
        byteBuf.writeInt(port);
    }
}

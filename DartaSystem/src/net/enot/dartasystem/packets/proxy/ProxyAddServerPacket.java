package net.enot.dartasystem.packets.proxy;

import io.netty.buffer.ByteBuf;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.utils.ByteBufUtil;

import java.io.IOException;

/**
 * Created by Енот on 27.04.2017.
 */
public class ProxyAddServerPacket implements Packet {

    private String name;
    private String address;
    private int port;

    public ProxyAddServerPacket(){}

    public ProxyAddServerPacket(String name, String address, int port){
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public int getPort(){
        return port;
    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        name = ByteBufUtil.readString(byteBuf);
        address = ByteBufUtil.readString(byteBuf);
        port = byteBuf.readInt();
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        ByteBufUtil.writeString(byteBuf, name);
        ByteBufUtil.writeString(byteBuf, address);
        byteBuf.writeInt(port);
    }

}

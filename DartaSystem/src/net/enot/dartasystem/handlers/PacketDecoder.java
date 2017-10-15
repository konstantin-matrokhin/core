package net.enot.dartasystem.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.enot.dartasystem.DartaSystem;
import net.enot.dartasystem.packets.Packet;

import java.util.List;

/**
 * Created by Енот on 23.04.2017.
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> output) throws Exception {
        int id = byteBuf.readInt();
        if (DartaSystem.INpackets.size() <= id){
            throw new NullPointerException("Не найден пакета с ID - " + id);
        }
        Class<? extends Packet> packetClass = DartaSystem.INpackets.get(id);
        Packet packet = packetClass.newInstance();
        packet.read(byteBuf);
        output.add(packet);
    }

}

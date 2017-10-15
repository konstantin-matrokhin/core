package net.enot.dartasystem.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.enot.dartasystem.DartaSystem;
import net.enot.dartasystem.packets.Packet;

/**
 * Created by Енот on 23.04.2017.
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf output) throws Exception {
        int id = DartaSystem.OUTpackets.indexOf(packet.getClass());
        if (id == -1){
            throw new NullPointerException("Пакет не найден");
        }
        output.writeInt(id);
        packet.write(output);
    }

}

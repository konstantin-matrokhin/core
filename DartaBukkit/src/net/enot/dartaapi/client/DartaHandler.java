package net.enot.dartaapi.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.enot.dartaapi.DartaAPI;
import net.enot.dartasystem.packets.Packet;

/**
 * Created by Енот on 27.04.2017.
 */
public class DartaHandler extends SimpleChannelInboundHandler<Packet> {

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctx.close();
        DartaAPI.getInstance().getDartaClient().doConnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}

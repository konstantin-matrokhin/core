package net.enot.darta;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.enot.darta.data.Player;
import net.enot.darta.data.Proxy;
import net.enot.darta.data.Server;
import net.enot.darta.logger.LoggerColor;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.packets.commands.*;
import net.enot.dartasystem.packets.player.*;
import net.enot.dartasystem.packets.proxy.ProxyAddServerPacket;
import net.enot.dartasystem.packets.proxy.ProxyConnectionPacket;
import net.enot.dartasystem.packets.proxy.ProxyRemoveServerPacket;
import net.enot.dartasystem.packets.server.ServerConnectionPacket;

import java.net.InetSocketAddress;
import java.util.logging.Level;

/**
 * Created by Енот on 25.04.2017.
 */
public class DartaHandler extends SimpleChannelInboundHandler<Packet> {

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        if (packet instanceof ProxyConnectionPacket) {
            ProxyConnectionPacket pcp = (ProxyConnectionPacket) packet;
            new Proxy(ctx.channel(), pcp.getName());
            Darta.getInstance().getLogger().log(Level.INFO, "[Proxy " + ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress() + "] > " + LoggerColor.GREEN + "Подключилась прокся " + pcp.getName());
            for (Server server : Server.getServers().values()){
                channel.writeAndFlush(new ProxyAddServerPacket(server.getName(), server.getAdress(), server.getPort()));
            }
        } else if (packet instanceof PlayerRejoinPacket) {
            PlayerRejoinPacket prp = (PlayerRejoinPacket) packet;
            Proxy proxy = Proxy.getProxy(channel);
            String name = prp.getName();
            new Player(name, proxy, Server.getServerByName(prp.getServer()));
            proxy.addOnline();

            //TODO REMOVE
            System.out.println("PLAYER REJOIN: " + name);
        } else if (packet instanceof PlayerJoinPacket) {
            PlayerJoinPacket pjp = (PlayerJoinPacket) packet;
            Proxy proxy = Proxy.getProxy(channel);
            String name = pjp.getName();
            new Player(name, proxy);
            proxy.addOnline();

            //TODO REMOVE
            System.out.println("PLAYER JOIN: " + name);
        } else if (packet instanceof PlayerLoginOutPacket) {
            String name = ((PlayerLoginOutPacket) packet).getName();
            channel.writeAndFlush(new PlayerLoginInPacket(name, !Player.containsPlayer(name)));

            //TODO REMOVE
            System.out.println("PLAYER LOGIN: " + name);
        } else if (packet instanceof PlayerQuitPacket) {
            String name = ((PlayerQuitPacket) packet).getName();
            Player.getPlayer(name).remove();
            Proxy proxy = Proxy.getProxy(channel);
            proxy.subtractOnline();

            //TODO REMOVE
            System.out.println("PLAYER QUIT: " + name);
        } else if (packet instanceof PlayerRedirectPacket) {
            PlayerRedirectPacket prp = (PlayerRedirectPacket) packet;
            String name = prp.getName();
            String server = prp.getServer();
            Player.getPlayer(name).setServer(Server.getServerByName(server));

            //TODO REMOVE
            System.out.println("PLAYER REDIRECTED: " + name + " SERVER: " + server);
        } else if (packet instanceof AlertCommandPacket) {
            for (Channel channel : Proxy.getProxys().keySet()) {
                if (channel == this.channel) continue;
                channel.writeAndFlush(packet);
            }
        } else if (packet instanceof FindCommandOutPacket) {
            FindCommandOutPacket fcop = (FindCommandOutPacket) packet;
            String sender = fcop.getSender();
            String find = fcop.getFind();
            if (Player.containsPlayer(find)) {
                Server server = Player.getPlayer(find).getServer();
                if (server != null) {
                    channel.writeAndFlush(new FindCommandInPacket(true, sender, find, server.getName()));
                } else {
                    channel.writeAndFlush(new FindCommandInPacket(true, sender, find, "null"));
                }
            } else {
                channel.writeAndFlush(new FindCommandInPacket(false, sender, find));
            }
        } else if (packet instanceof SendCommandOutPacket){
            SendCommandOutPacket scop = (SendCommandOutPacket) packet;
            if (Player.containsPlayer(scop.getSendPlayer())){
                Player.getPlayer(scop.getSendPlayer()).getProxy().getChannel().writeAndFlush(new SendPlayerInPacket(scop.getSender(), scop.getSendPlayer(), scop.getServer()));
            } else {
                channel.writeAndFlush(new SendCommandInPacket(false, scop.getSender(), scop.getSendPlayer(), scop.getServer()));
            }
        } else if (packet instanceof SendPlayerOutPacket){
            SendPlayerOutPacket spop = (SendPlayerOutPacket) packet;
            if (Player.containsPlayer(spop.getSender())){
                if (spop.isOnline()){
                    Player.getPlayer(spop.getSender()).getProxy().getChannel().writeAndFlush(new SendCommandInPacket(true, spop.getSender(), spop.getSendPlayer(), spop.getServer(), spop.isSend()));
                } else {
                    Player.getPlayer(spop.getSender()).getProxy().getChannel().writeAndFlush(new SendCommandInPacket(false, spop.getSender(), spop.getSendPlayer(), spop.getServer()));
                }
            }
        } else if (packet instanceof ServerConnectionPacket){
            ServerConnectionPacket scp = (ServerConnectionPacket) packet;
            Server server = new Server(channel, scp.getName(), scp.getPort());
            Darta.getInstance().getLogger().log(Level.INFO, "[Server " + server.getAdress() + ":" + server.getPort() + "] > " + LoggerColor.GREEN + "Подключился сервер " + server.getName());
            for (Channel channel : Proxy.getProxys().keySet()){
                channel.writeAndFlush(new ProxyAddServerPacket(server.getName(), server.getAdress(), server.getPort()));
            }
        } else if (packet instanceof OnlineCommandOutPacket){
            OnlineCommandOutPacket ocop = (OnlineCommandOutPacket) packet;
            Server server = Server.getServerByName(ocop.getServer());
            if (server != null){
                channel.writeAndFlush(new OnlineCommandInPacket(ocop.getPlayerName(), ocop.getServer(), true, server.getOnline()));
            } else {
                channel.writeAndFlush(new OnlineCommandInPacket(ocop.getPlayerName(), ocop.getServer(), false));
            }

        } else if (packet instanceof TestINPacket){
            TestINPacket testPacket = (TestINPacket) packet;
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (Proxy.containsProxy(channel)) {
            Proxy proxy = Proxy.getProxy(channel);
            Player.getPlayers().values().stream().filter(player -> player.getProxy() == proxy).forEach(Player::remove);
            Darta.getInstance().getLogger().log(Level.INFO, "[Proxy " + proxy.getAdress() + "] > " + LoggerColor.RED + "Отключилась прокся " + proxy.getName());
            proxy.remove();
            ctx.close();
        } else if (Server.containsServer(channel)) {
            Server server = Server.getServer(channel);
            Darta.getInstance().getLogger().log(Level.INFO, "[Server " + server.getAdress() + ":" + server.getPort() + "] > " + LoggerColor.RED + "Отключился сервер " + server.getName());
            for (Channel channel : Proxy.getProxys().keySet()){
                channel.writeAndFlush(new ProxyRemoveServerPacket(server.getName()));
            }
            server.remove();
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}

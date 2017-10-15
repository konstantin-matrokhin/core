package net.enot.dartabungee.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.enot.dartabungee.DartaBungee;
import net.enot.dartabungee.client.listeners.PingListener;
import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.packets.commands.AlertCommandPacket;
import net.enot.dartasystem.packets.commands.FindCommandInPacket;
import net.enot.dartasystem.packets.commands.OnlineCommandInPacket;
import net.enot.dartasystem.packets.commands.SendCommandInPacket;
import net.enot.dartasystem.packets.player.PlayerLoginInPacket;
import net.enot.dartasystem.packets.player.SendPlayerInPacket;
import net.enot.dartasystem.packets.player.SendPlayerOutPacket;
import net.enot.dartasystem.packets.proxy.ProxyAddServerPacket;
import net.enot.dartasystem.packets.proxy.ProxyRemoveServerPacket;
import net.enot.dartasystem.packets.proxy.ProxySendOnlinePacket;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.net.InetSocketAddress;

/**
 * Created by Енот on 23.04.2017.
 */
public class DartaHandler extends SimpleChannelInboundHandler<Packet> {

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        if (packet instanceof PlayerLoginInPacket) {
            PlayerLoginInPacket plip = (PlayerLoginInPacket) packet;
            if (ConnectPlayer.containsConnectPlayer(plip.getName())) {
                ConnectPlayer.getConnectPlayer(plip.getName()).countDown(plip.isCanJoin());
            }
        } else if (packet instanceof AlertCommandPacket){
            ProxyServer.getInstance().broadcast(new TextComponent(((AlertCommandPacket) packet).getMessage()));
        } else if (packet instanceof FindCommandInPacket){
            FindCommandInPacket fcip = (FindCommandInPacket) packet;
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(fcip.getSender());
            if (player != null){
                if (fcip.isOnline()){
                    player.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + fcip.getFind() + " §6находится на сервере §c" + fcip.getServer()));
                } else {
                    player.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + fcip.getFind() + " не найден"));
                }

            }
        } else if (packet instanceof ProxySendOnlinePacket){
            PingListener.online = ((ProxySendOnlinePacket) packet).getOnline();
        } else if (packet instanceof SendPlayerInPacket){
            SendPlayerInPacket spip = (SendPlayerInPacket) packet;
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(spip.getSendPlayer());
            if (player != null){
                player.connect(ProxyServer.getInstance().getServerInfo(spip.getServer()), (aBoolean, throwable) -> {
                    if (aBoolean){
                        channel.writeAndFlush(new SendPlayerOutPacket(true, spip.getSender(), spip.getSendPlayer(), spip.getServer(), true));
                        player.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + spip.getSender() + " §6телепортировал вас на сервер §c" + spip.getServer()));
                    } else {
                        channel.writeAndFlush(new SendPlayerOutPacket(true, spip.getSender(), spip.getSendPlayer(), spip.getServer(), false));
                    }
                });
            } else {
                channel.writeAndFlush(new SendPlayerOutPacket(false, spip.getSender(), spip.getSendPlayer(), spip.getServer()));
            }
        } else if (packet instanceof SendCommandInPacket){
            SendCommandInPacket scip = (SendCommandInPacket) packet;
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(scip.getSender());
            if (player != null){
                if (scip.isOnline()){
                    if (scip.isSend()){
                        player.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + scip.getSendPlayer() + " §6телепортирован на сервер §c" + scip.getServer()));
                    } else {
                        player.sendMessage(new TextComponent(MessageUtil.prefix + "§cНе удалось телепортировать " + scip.getSendPlayer() + " на сервер " + scip.getServer()));
                    }
                } else {
                    player.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + scip.getSendPlayer() + " не найден"));
                }
            }
        } else if (packet instanceof ProxyAddServerPacket){
            ProxyAddServerPacket pasp = (ProxyAddServerPacket) packet;
            ProxyServer.getInstance().getServers().put(pasp.getName(), ProxyServer.getInstance().constructServerInfo(pasp.getName(), new InetSocketAddress(pasp.getAddress(), pasp.getPort()), "", false));
        } else if (packet instanceof ProxyRemoveServerPacket){
            ProxyRemoveServerPacket prsp = (ProxyRemoveServerPacket) packet;
            ServerInfo server = ProxyServer.getInstance().getServerInfo(prsp.getName());
            if (server != null){
                for (ProxiedPlayer player : server.getPlayers()) {
                    player.disconnect(new TextComponent("§cЭтот сервер был принудительно отключен"));
                }
                ProxyServer.getInstance().getServers().remove(prsp.getName());
            }
        } else if (packet instanceof OnlineCommandInPacket){
            OnlineCommandInPacket ocip = (OnlineCommandInPacket) packet;
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(ocip.getPlayerName());
            if (player != null){
                if (ocip.isCreated()){
                    player.sendMessage(new TextComponent(MessageUtil.prefix + "§6Онлайн сервера §c" + ocip.getServer() + " §6- §c" + ocip.getOnline()));
                } else {
                    player.sendMessage(new TextComponent(MessageUtil.prefix + "§cСервер " + ocip.getServer() + " не найден"));
                }
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctx.close();
        DartaBungee.getInstance().getDartaClient().doConnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

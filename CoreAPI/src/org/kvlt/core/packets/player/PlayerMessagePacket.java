package org.kvlt.core.packets.player;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.kvlt.core.CoreServer;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.bukkit.ServerMessagePacket;

import java.awt.*;

public class PlayerMessagePacket extends Packet {

    private static final String msgFormat = "(PM) [%from%] %sender%: %msg%";

    private String sender;
    private String recipient;
    private String message;
    private String server;

    public PlayerMessagePacket(String from, String sender, String recepient, String message) {
        this.sender = sender;
        this.recipient = recepient;
        this.message = message;
        this.server = from;
    }

    @Override
    protected void onCore() {
        OnlinePlayer coreRecepient = CoreServer.get().getOnlinePlayers().get(recipient);
        OnlinePlayer coreSender = CoreServer.get().getOnlinePlayers().get(sender);

        if (coreRecepient != null) {
            coreRecepient.getCurrentProxy().send(this);
        } else {
            String errorMsg = "Такого игрока нет онлайн.";
            ServerMessagePacket smp = new ServerMessagePacket(sender, errorMsg);
            coreSender.getCurrentProxy().send(this);
        }
    }

    @Override
    protected void onServer() {
    }

    @Override
    protected void onProxy() {
        String formattedMsg = msgFormat
                .replaceAll("%from%", server)
                .replaceAll("%sender%", sender)
                .replaceAll("%msg%", message);

        CoreBungee.get().getProxy().getLogger().info(formattedMsg);

        BungeeCord.getInstance().getPlayer(recipient)
                .sendMessage(ChatMessageType.CHAT,
                        TextComponent.fromLegacyText(formattedMsg));
    }
}

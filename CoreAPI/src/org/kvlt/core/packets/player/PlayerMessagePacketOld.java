package org.kvlt.core.packets.player;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.kvlt.core.CoreServer;
import org.kvlt.core.bungee.storages.ReplyStorage;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.PacketOld;
import org.kvlt.core.packets.bukkit.ServerMessagePacket;

public class PlayerMessagePacketOld extends PacketOld {

    private static final String msgFormat = "&7[&e%from%&7] %sender% -> вам: &e%msg%";

    private String sender;
    private String recipient;
    private String message;
    private String server;

    public PlayerMessagePacketOld(String from, String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.server = from;
    }

    @Override
    protected void onCore() {
        OnlinePlayer coreRecipient = CoreServer.get().getOnlinePlayers().get(recipient);
        OnlinePlayer coreSender = CoreServer.get().getOnlinePlayers().get(sender);
        String response;

        if (coreSender == null) return;
        if (coreRecipient != null) {
            coreRecipient.getCurrentProxy().send(this);
            response = "[" + server + "] я -> " + recipient + ": " + message;
        } else {
            response = "Такого игрока нет онлайн.";
        }
        ServerMessagePacket smp = new ServerMessagePacket(sender, response);
        coreSender.getCurrentProxy().send(smp);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
        ReplyStorage.setLastInterlocutor(sender, recipient);

        String formattedMsg = ChatColor
                .translateAlternateColorCodes('&',msgFormat
                    .replaceAll("%from%", server)
                    .replaceAll("%sender%", sender)
                    .replaceAll("%msg%", message)
                );

        BungeeCord.getInstance().getPlayer(recipient)
                .sendMessage(ChatMessageType.CHAT,
                        TextComponent.fromLegacyText(formattedMsg));
    }
}

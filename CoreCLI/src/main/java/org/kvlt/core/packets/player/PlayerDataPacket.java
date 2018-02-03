package org.kvlt.core.packets.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.buffer.ByteBuf;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerDataPacket extends CorePacketOut {

    private static Gson gson;
    private String playerJson;
    private int key;

    {
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

    public PlayerDataPacket(ServerPlayer player) {
        if (player != null) {
            playerJson = gson.toJson(player);
        } else {
            playerJson = PacketUtil.EMPTY_STRING;
        }
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerJson, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_DATA_PACKET;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

}

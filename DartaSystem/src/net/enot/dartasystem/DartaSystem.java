package net.enot.dartasystem;

import net.enot.dartasystem.packets.Packet;
import net.enot.dartasystem.packets.TestINPacket;
import net.enot.dartasystem.packets.TestOUTPacket;
import net.enot.dartasystem.packets.commands.*;
import net.enot.dartasystem.packets.player.*;
import net.enot.dartasystem.packets.proxy.ProxyAddServerPacket;
import net.enot.dartasystem.packets.proxy.ProxyConnectionPacket;
import net.enot.dartasystem.packets.proxy.ProxyRemoveServerPacket;
import net.enot.dartasystem.packets.proxy.ProxySendOnlinePacket;
import net.enot.dartasystem.packets.server.ServerConnectionPacket;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Енот on 23.04.2017.
 */
public class DartaSystem {

    public static final List<Class<? extends Packet>> INpackets = Arrays.asList(
            PlayerLoginInPacket.class,
            FindCommandInPacket.class,
            SendCommandInPacket.class,
            SendPlayerInPacket.class,
            OnlineCommandInPacket.class,
            TestInPacket.class);

    public static final List<Class<? extends Packet>> OUTpackets = Arrays.asList(
            PlayerLoginOutPacket.class,
            FindCommandOutPacket.class,
            SendCommandOutPacket.class,
            SendPlayerOutPacket.class,
            OnlineCommandOutPacket.class,
            TestOUTPacket.class);

}

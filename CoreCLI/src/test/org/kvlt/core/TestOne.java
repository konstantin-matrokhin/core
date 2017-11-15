package test.org.kvlt.core;

import org.junit.Assert;
import org.junit.Test;
import org.kvlt.core.CoreServer;
import org.kvlt.core.config.Config;
import org.kvlt.core.db.DAO;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.packets.player.PlayerProxyLoginPacket;

public class TestOne {

    @Test
    public void test1() {
        String name = "kvlt";
        String proxyName = "proxy-1";
        Object dummy = null;

        Config.init();
        DAO.connect();
        CoreServer cs = CoreServer.get();

        Proxy proxy = new Proxy(proxyName, null);
        cs.getProxies().addNode(proxy);

        OnlinePlayer op = new OnlinePlayer();
        op.setName(name);

        PlayerProxyLoginPacket playerProxyLoginPacket;
        playerProxyLoginPacket = new PlayerProxyLoginPacket(op, proxy.getName());

        playerProxyLoginPacket.execute(dummy);

        PlayerDB.loadOnlinePlayer(op);
        int size = cs.getOnlinePlayers().size();

        Assert.assertEquals(op.getId(), 34);
        Assert.assertEquals(size, 1);
    }

}

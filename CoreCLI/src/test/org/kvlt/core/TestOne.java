package test.org.kvlt.core;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.kvlt.core.CoreServer;
import org.kvlt.core.config.Config;
import org.kvlt.core.db.DAO;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.packets.player.PlayerProxyLoginPacket;

public class TestOne extends TestCase {

    private String name = "kvlt";
    private CoreServer cs;

    @Test
    public void test1() {
        String proxyName = "proxy-1";
        Object dummy = null;

        Config.init();
        DAO.connect();
        cs = CoreServer.get();

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

    @Test
    public void test2() {
        long playedTime = PlayerDB.loadServerPlayer(name).getPlayedTotal();
        Assert.assertEquals("07:00:00", PlayedTimeCounter.getFormatedTime(playedTime));
    }

}

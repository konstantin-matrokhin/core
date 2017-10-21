package test.kvlt.core;

import org.junit.Assert;
import org.junit.Test;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.entities.SimplePlayer;

public class TestCore {

    @Test
    public void testTest() {
        ServerPlayer sp = new SimplePlayer();
        sp.setName("maxim");
        Assert.assertEquals("maxim", sp.getName());
        Assert.assertEquals(null, sp.getUUID());
    }

}

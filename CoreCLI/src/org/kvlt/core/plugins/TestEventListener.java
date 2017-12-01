package org.kvlt.core.plugins;

import org.kvlt.core.events.CoreHandler;
import org.kvlt.core.events.CoreListener;

public class TestEventListener implements CoreListener {

    @CoreHandler
    public void kek(TestEvent e) {
        System.out.println(e.getKek());
    }

}

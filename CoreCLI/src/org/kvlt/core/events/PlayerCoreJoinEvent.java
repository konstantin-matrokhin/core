package org.kvlt.core.events;

import java.util.ArrayList;
import java.util.List;

public class PlayerCoreJoinEvent implements CoreEvent {

    private List<CoreListener> listeners;

    public PlayerCoreJoinEvent() {
        listeners = new ArrayList<>();
    }

    @Override
    public void addListener(CoreListener cl) {
        listeners.add(cl);
    }

    @Override
    public void notifyListeners() {
        listeners.forEach(l -> l.execute());
    }
}

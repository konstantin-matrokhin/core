package org.kvlt.core.events;

import org.kvlt.core.CoreServer;

public interface CoreEvent {

    default String getName() {
        return this.getClass().getSimpleName();
    }

    default void invoke() {
        CoreServer.get().getEventManager().invokeEvent(this);
    }

}

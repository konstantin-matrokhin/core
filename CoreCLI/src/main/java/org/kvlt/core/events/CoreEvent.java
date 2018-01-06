package org.kvlt.core.events;

import org.kvlt.core.Core;

public interface CoreEvent {

    default String getName() {
        return this.getClass().getSimpleName();
    }

    default void invoke() {
        Core.get().getEventManager().invokeEvent(this);
    }

}

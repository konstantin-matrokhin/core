package org.kvlt.core.events;

public interface CoreEvent {

    default String getName() {
        return this.getClass().getSimpleName();
    }

}

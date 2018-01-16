package org.kvlt.core.events;

public interface Cancellable {

    void setCancelled(boolean cancelled);
    boolean isCancelled();

}

package org.kvlt.core.events;

public interface CoreEvent {

    void addListener(CoreListener coreListener);
    void notifyListeners();

}

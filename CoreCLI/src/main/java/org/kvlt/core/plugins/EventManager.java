package org.kvlt.core.plugins;

import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.events.CoreHandler;
import org.kvlt.core.events.CoreListener;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventManager {

    private List<CoreListener> listeners;
    private List<Method> allMethods;

    {
        listeners = new ArrayList<>();
    }

    public void initializeExternalEvents() {
        List<Method> handlersMethods = new ArrayList<>();
        Class annotation = CoreHandler.class;

        listeners.forEach(listener -> {
            Method[] methods = listener.getClass().getMethods();
            List<Method> list = Arrays.asList(methods);
            list.stream()
                    .filter(m -> m.isAnnotationPresent(annotation))
                    .forEach(m -> handlersMethods.add((Method) m));
        });
        allMethods = handlersMethods;
    }

    public void registerListener(CoreListener cl) {
        listeners.add(cl);
    }

    public void invokeEvent(CoreEvent event) {
        allMethods.forEach(method -> {
            Parameter[] params = method.getParameters();

            for (Parameter param : params) {
                if (param.getType().equals(event.getClass())) {
                    try{
                        Object toInvoke = method.getDeclaringClass()
                                .getDeclaredConstructor()
                                .newInstance();
                        method.invoke(toInvoke, event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}

package com.fuliang.authoflex.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AfEventCenter {
    private static Map<EventType, List<AfListener>> listeners = new HashMap<>();
    static {
        addListener(new AfListener4Log());
    }
    public static void addListener(AfListener listener) {
        EventType topic = listener.getTopic();
        List<AfListener> listenerList = listeners.getOrDefault(topic, new ArrayList<>());
        listenerList.add(listener);
        listeners.put(topic, listenerList);
    }

    public static void onEvent(EventType topic, Object event) {
        List<AfListener> listenerList = listeners.get(topic);
        if (listenerList == null) return;
        for (AfListener listener : listenerList) {
            listener.onEvent(event);
        }
    }

}

package com.fuliang.authoflex.event;

import java.util.List;

public interface AfListener {
    EventType getTopic();
    void onEvent(Object event);
}

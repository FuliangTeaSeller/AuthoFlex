package com.fuliang.authoflex.event;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class AfListener4Log implements AfListener {
    @Override
    public EventType getTopic() {
        return EventType.ON_LOGIN;
    }

    @Override
    public void onEvent(Object event) {
//        log.info("{}事件发生了，参数为{}", getTopic(), event);
    }
}

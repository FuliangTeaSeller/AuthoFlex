package com.fuliang.authoflex.event;

//@Slf4j
public class AfListenerForLog implements AfListener {
    @Override
    public EventType getTopic() {
        return EventType.ON_LOGIN;
    }

    @Override
    public void onEvent(Object event) {
//        log.info("{}事件发生了，参数为{}", getTopic(), event);
    }
}

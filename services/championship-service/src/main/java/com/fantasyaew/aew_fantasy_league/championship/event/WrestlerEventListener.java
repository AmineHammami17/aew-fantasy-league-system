package com.fantasyaew.aew_fantasy_league.championship.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WrestlerEventListener {

    private static final Logger log = LoggerFactory.getLogger(WrestlerEventListener.class);

    @KafkaListener(topics = "wrestlers.events", groupId = "championship-service-group")
    public void handleWrestlerEvent(WrestlerEvent event) {
        // Later: use event.id (wrestlerId) to link to title reigns or validate assignments
        log.info("Received wrestler event type={} id={} ringName={}",
                event.getEventType(), event.getId(), event.getRingName());
    }
}


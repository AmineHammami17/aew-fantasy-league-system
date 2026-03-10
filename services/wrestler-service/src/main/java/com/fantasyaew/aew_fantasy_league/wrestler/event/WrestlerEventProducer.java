package com.fantasyaew.aew_fantasy_league.wrestler.event;

import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class WrestlerEventProducer {

    private static final Logger log = LoggerFactory.getLogger(WrestlerEventProducer.class);
    private static final String TOPIC = "wrestlers.events";

    private final KafkaTemplate<String, WrestlerEvent> kafkaTemplate;

    public void sendCreated(Wrestler wrestler) {
        sendEvent(wrestler, "CREATED");
    }

    public void sendUpdated(Wrestler wrestler) {
        sendEvent(wrestler, "UPDATED");
    }

    private void sendEvent(Wrestler wrestler, String type) {
        WrestlerEvent event = WrestlerEvent.builder()
                .id(wrestler.getId())
                .ringName(wrestler.getRingName())
                .promotion(wrestler.getPromotion())
                .division(wrestler.getDivision())
                .popularityScore(wrestler.getPopularityScore())
                .eventType(type)
                .occurredAt(Instant.now())
                .build();

        kafkaTemplate.send(TOPIC, wrestler.getId().toString(), event);
        log.info("Published wrestler event {} for id={}", type, wrestler.getId());
    }
}


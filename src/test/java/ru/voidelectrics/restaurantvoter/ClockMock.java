package ru.voidelectrics.restaurantvoter;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class ClockMock extends Clock {

    private Instant instant = Instant.now();

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public ZoneId getZone() {
        return ZoneId.of("UTC");
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.fixed(instant, zone);
    }

    @Override
    public Instant instant() {
        return instant;
    }
}

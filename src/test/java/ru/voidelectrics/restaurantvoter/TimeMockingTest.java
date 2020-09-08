package ru.voidelectrics.restaurantvoter;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.Clock;

public abstract class TimeMockingTest {
    @Autowired
    Clock clockMock;

    protected ClockMock clockMock() {
        return (ClockMock) clockMock;
    }
}

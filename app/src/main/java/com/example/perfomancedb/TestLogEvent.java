package com.example.perfomancedb;

/**
 * Date: 29.09.2016
 * Time: 16:11
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class TestLogEvent {
    private final long startTime;
    private final long endTime;
    private final String dbName;
    private final String eventType;

    public TestLogEvent(long startTime, long endTime, String dbName, String eventType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dbName = dbName;
        this.eventType = eventType;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getDbName() {
        return dbName;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "TestLogEvent{" +
                "time=" + (endTime - startTime) +
                ", dbName='" + dbName + '\'' +
                ", eventType=" + eventType +
                '}';
    }
}

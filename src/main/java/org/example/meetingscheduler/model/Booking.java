package org.example.meetingscheduler.model;

public class Booking {
    private static int idCounter = 1; // auto increment
    private int bookingId;
    private int roomId;
    private int startTime;
    private int endTime;
    private String meetingTitle;

    public Booking(int roomId, int startTime, int endTime, String meetingTitle) {
        this.bookingId = idCounter++;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingTitle = meetingTitle;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }
}

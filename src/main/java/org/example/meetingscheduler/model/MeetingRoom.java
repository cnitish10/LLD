package org.example.meetingscheduler.model;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    private int roomId;
    private int capacity;
    private List<String> facilities;
    private List<Booking> allBookings;

    public MeetingRoom(int roomId, int capacity, List<String> facilities) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.facilities = facilities;
        this.allBookings = new ArrayList<>();
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public List<Booking> getAllBookings() {
        return allBookings;
    }

    public void addBooking(Booking booking) {
        this.allBookings.add(booking);
    }
}

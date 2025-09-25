package org.example.meetingscheduler.service;

import org.example.meetingscheduler.model.Booking;
import org.example.meetingscheduler.model.MeetingRoom;

import java.util.*;
import java.util.stream.Collectors;

public class MeetingSchedulerService {
    private final Map<Integer, MeetingRoom> meetingRoomHashMap;
    private final Map<Integer, Set<Booking>> roomVsBookingHashMap;
    private final Map<Integer, Booking> bookingHashMap;
    private final List<MeetingRoom> meetingRooms;

    public MeetingSchedulerService() {
        meetingRoomHashMap = new HashMap<>();
        roomVsBookingHashMap = new HashMap<>();
        bookingHashMap = new HashMap<>();
        meetingRooms = new ArrayList<>();
    }

    // Add meeting room to the system
    public void addMeetingRoom(MeetingRoom room) {
        if (!meetingRoomHashMap.containsKey(room.getRoomId())) {
            meetingRoomHashMap.put(room.getRoomId(), room);
            meetingRooms.add(room);
            System.out.println("Room added successfully: ");
        } else {
            System.out.println("Room already exists!");
        }
    }

    // Book a room
    public void bookMeetingRoom(Booking booking) {
        int roomId = booking.getRoomId();

        if (!meetingRoomHashMap.containsKey(roomId)) {
            System.out.println("Room doesn't exist!");
            return;
        }

        int startTime = booking.getStartTime();
        int endTime = booking.getEndTime();

        if (startTime >= endTime) {
            System.out.println("End time must be greater than start time!");
            return;
        }

        // Check conflicts
        Set<Booking> bookingsForRoom = roomVsBookingHashMap.getOrDefault(roomId, new HashSet<>());
        for (Booking existing : bookingsForRoom) {
            if (!(endTime <= existing.getStartTime() || startTime >= existing.getEndTime())) {
                System.out.println("Booking conflict detected! Cannot book.");
                return;
            }
        }

        // Save booking
        bookingHashMap.put(booking.getBookingId(), booking);
        bookingsForRoom.add(booking);
        roomVsBookingHashMap.put(roomId, bookingsForRoom);

        System.out.println("Booking successful for room: " + meetingRoomHashMap.get(roomId).getRoomId());
    }

    // Cancel booking
    public void cancelBooking(int bookingId) {
        if (!bookingHashMap.containsKey(bookingId)) {
            System.out.println("Booking not found!");
            return;
        }

        Booking booking = bookingHashMap.remove(bookingId);
        int roomId = booking.getRoomId();
        Set<Booking> bookings = roomVsBookingHashMap.get(roomId);
        if (bookings != null) {
            bookings.remove(booking);
        }

        System.out.println("Cancelled booking ID: " + bookingId);
    }

    // List all bookings for a room
    public List<Booking> allBookingsForRoom(int roomId) {
        return new ArrayList<>(roomVsBookingHashMap.getOrDefault(roomId, new HashSet<>()));
    }

    // Search by capacity
    public List<MeetingRoom> searchMeetingRoomByCapacity(int capacity) {
        return this.meetingRooms.stream()
                .filter(room -> room.getCapacity() >= capacity)
                .collect(Collectors.toList());
    }

    // Search by facilities
    public List<MeetingRoom> searchMeetingRoomByFacility(List<String> facilities) {
        return this.meetingRooms.stream()
                .filter(room -> room.getFacilities().containsAll(facilities))
                .collect(Collectors.toList());
    }

    // Get available rooms for a given interval + filters
    public List<MeetingRoom> getAvailableRooms(int startTime, int endTime, int requiredCapacity, List<String> requiredFacilities) {
        List<MeetingRoom> availableRooms = new ArrayList<>();

        for (MeetingRoom room : meetingRooms) {
            // Capacity filter
            if (room.getCapacity() < requiredCapacity) continue;

            // Facility filter
            if (!room.getFacilities().containsAll(requiredFacilities)) continue;

            // Booking conflict check
            boolean conflict = false;
            Set<Booking> bookingsForRoom = roomVsBookingHashMap.getOrDefault(room.getRoomId(), new HashSet<>());
            for (Booking existing : bookingsForRoom) {
                if (!(endTime <= existing.getStartTime() || startTime >= existing.getEndTime())) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }
}

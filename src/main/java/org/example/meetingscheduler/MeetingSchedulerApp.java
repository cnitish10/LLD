package org.example.meetingscheduler;

import org.example.meetingscheduler.model.Booking;
import org.example.meetingscheduler.model.MeetingRoom;
import org.example.meetingscheduler.service.MeetingSchedulerService;

import java.util.*;

public class MeetingSchedulerApp {

    public static void main(String args[]) {
        MeetingSchedulerService meetingSchedulerService = new MeetingSchedulerService();

        // Create meeting rooms with facilities
        MeetingRoom meetingRoom1 = new MeetingRoom(1, 10, Arrays.asList("AC", "Projector"));
        MeetingRoom meetingRoom2 = new MeetingRoom(2, 20, Arrays.asList("AC", "Whiteboard", "Speaker"));

        // Add meeting rooms
        meetingSchedulerService.addMeetingRoom(meetingRoom1);
        meetingSchedulerService.addMeetingRoom(meetingRoom2);

        // Bookings
        Booking booking1 = new Booking(1, 1, 10,  "Discussion on FE"); // needs 8 seats
        Booking booking2 = new Booking(2, 2, 22,  "Discussion on BE"); // needs 15 seats

        // Book meeting rooms
        meetingSchedulerService.bookMeetingRoom(booking1);
        meetingSchedulerService.bookMeetingRoom(booking2);

        // --- AVAILABILITY CHECK ---
        System.out.println("\nAvailable rooms between 12 to 18 with capacity 10:");
        List<MeetingRoom> availableRooms = meetingSchedulerService.getAvailableRooms(12, 18, 10, new ArrayList<>());
        for (MeetingRoom room : availableRooms) {
            System.out.println("Room " + room.getRoomId() + " is available");
        }

        // --- SEARCH BY FACILITIES ---
        System.out.println("\nSearching rooms with facilities [AC, Projector]:");
        List<MeetingRoom> facilityRooms = meetingSchedulerService.searchMeetingRoomByFacility(Arrays.asList("AC", "Projector"));
        for (MeetingRoom room : facilityRooms) {
            System.out.println("Room " + room.getRoomId() + " has required facilities");
        }

        // --- CANCEL BOOKING ---
        meetingSchedulerService.cancelBooking(1);

        // Show bookings after cancellation
        System.out.println("\nBookings after cancellation for Room 1:");
        List<Booking> bookingsRoom1 = meetingSchedulerService.allBookingsForRoom(1);
        for (Booking b : bookingsRoom1) {
            System.out.println(b.getMeetingTitle());
        }
    }
}

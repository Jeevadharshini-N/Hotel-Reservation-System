import java.util.ArrayList;
import java.util.Scanner;

// Room class
class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

// Booking class
class Booking {
    String customerName;
    int roomNumber;

    Booking(String customerName, int roomNumber) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Add sample rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
        rooms.add(new Room(302, "Suite"));

        while (true) {
            System.out.println("\n--- HOTEL RESERVATION SYSTEM ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> viewBookings();
                case 5 -> {
                    System.out.println("Thank you for using the system!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // View available rooms
    static void viewAvailableRooms() {
        System.out.println("\n--- AVAILABLE ROOMS ---");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room " + r.roomNumber + " - " + r.category);
            }
        }
    }

    // Book a room
    static void bookRoom() {
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Room Number to Book: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo) {
                if (r.isBooked) {
                    System.out.println("Room already booked!");
                    return;
                } else {
                    r.isBooked = true;
                    bookings.add(new Booking(name, roomNo));
                    System.out.println("Room " + roomNo + " booked successfully!");
                    return;
                }
            }
        }
        System.out.println("Invalid Room Number.");
    }

    // Cancel booking
    static void cancelBooking() {
        System.out.print("Enter Room Number to Cancel: ");
        int roomNo = sc.nextInt();

        for (Booking b : bookings) {
            if (b.roomNumber == roomNo) {
                bookings.remove(b);

                for (Room r : rooms) r.isBooked = (r.roomNumber == roomNo) ? false : r.isBooked;

                System.out.println("Booking cancelled successfully!");
                return;
            }
        }
        System.out.println("No booking found for that room.");
    }

    // View all bookings
    static void viewBookings() {
        System.out.println("\n--- CURRENT BOOKINGS ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println("Room " + b.roomNumber + " booked by " + b.customerName);
        }
    }
}
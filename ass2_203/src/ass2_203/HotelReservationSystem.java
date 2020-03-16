/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass2_203;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;
 
public class HotelReservationSystem {

    public static int counter = 0, re = 0;

    public static void main(String[] args) throws Exception {
        //Input/output
        File input = new File("input.txt");
        File print = new File("print.txt");
        // cheak the file exist or not
        if (!input.exists()) {
            System.out.println("File doesn't exist");
            System.exit(0);
        }

        Scanner read = new Scanner(input);
        PrintWriter output = new PrintWriter(print);
        int numberOfRoom = read.nextInt();
        Room[] room = new Room[numberOfRoom];
        int r = 0;
        int numberOfGuest = read.nextInt();
        Guest[] guest = new Guest[numberOfGuest];
        int g = 0;
        int numberOfReservation = read.nextInt();
        Reservation[] reservation = new Reservation[numberOfReservation];
        int re = 0;
        while (read.hasNext()) {
            String commandos = read.next();
            switch (commandos) {
                case "Add_Room": {
                    add_room(read, output, room, r);
                    r++;
                }
                break;
                case "Add_Guest": {

                    add_guest(read, guest, output, g);
                    g++;

                }
                break;
                case "Make_Reservation": {

                    makeReservation(read, room, guest, reservation, output, re);

                }
                break;
                case "Print_Reservation": {
                    String code = read.next();
                    //   Print_Reservation(code, output, reservation);
                }
                break;
                case "Search_Print": {
                    String roomView = read.next();
                    String roomType = read.next();
                    Search_Print(roomView, roomType, output, room);
                }
                break;
                case "Room_Status": {
                    String code = read.next();
                    Room_Status(code, output, room);
                }
                break;
                case "Quit": {
                    break;
                }

            }
// end wile loop

        }
        read.close();
        output.close();
    }
//    end main method

    public static void add_room(Scanner read, PrintWriter output, Room[] room, int i) throws ParseException {
        String roomCode = read.next();
        String roomView = read.next();
        String roomType = read.next();
        int numberofGust = read.nextInt();
        double price = read.nextDouble();
        String SDate = read.next();
        String eDate = read.next();

        SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");
        Date startdate = dates.parse(SDate);
        Date enddate = dates.parse(eDate);

        if (i < room.length) {
            room[i] = new Room(roomCode, roomView, roomType, startdate, enddate, numberofGust, price);
            System.out.println("ADD_Room " + room[i].getRoomCode() + " was added successfully to the system\n");

        } else {
            System.out.println("Room " + roomCode + " was not added, you exceed the maximum number of rooms");

        }
    }

    public static void add_guest(Scanner read, Guest[] guests, PrintWriter output, int i) {
        String name = read.next();
        int Age = read.nextInt();
        String gender = read.next();
        String phone = read.next();

        if (i < guests.length) {

            guests[i] = new Guest(name, Age, gender.charAt(0), phone);
            output.println("good");
        } else {
            output.println("sorry there  are no room");

        }

    }

    public static void Print_Reservation(String code, PrintWriter output, Reservation[] reservation) {
        for (int i = 0; i < reservation.length; i++) {
            if (code.equalsIgnoreCase(reservation[i].getURN())) {
                output.println(reservation[i].getGuest().getName());
                output.println(reservation[i].getRoom().getRoomCode());
                output.println(reservation[i].getReservationSDate());
            }
        }
    }

    public static void Search_Print(String roomView, String roomType, PrintWriter output, Room[] room) {
        for (int i = 0; i < room.length; i++) {
            if (roomView.equalsIgnoreCase(room[i].getRoomView()) && roomType.equalsIgnoreCase(room[i].getRoomType())) {
                output.println(room[i].getRoomCode());
            }

        }
    }

    public static void Room_Status(String code, PrintWriter output, Room[] room) {
        for (int i = 0; i < room.length; i++) {
            if (code.equalsIgnoreCase(room[i].getRoomCode())) {
                output.println("");
            }
        }
    }

    public static void makeReservation(Scanner read, Room[] rooms, Guest[] guestroom, Reservation[] reservation,
            PrintWriter write,
            int k) throws ParseException {
        String roomcode = read.next();
        int numberOfgeust = read.nextInt();
        String name = read.next();
        String sdate = read.next();
        String edate = read.next();

        SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");
        Date reservationSDate = dates.parse(sdate);
        Date reservationEDate = dates.parse(edate);

        /////-------check for Guest 
        Guest foundGuest = checkGuest(guestroom, name);
        /////-------check for room 
        Room foundRoom = chekingroomINfo(rooms, roomcode, numberOfgeust, reservationSDate, reservationEDate);

        ///---------add reservation 
        if (reservation.length > k) {

            if (foundRoom != null) {
                if (foundGuest != null) {

                    reservation[k] = new Reservation(("Re00" + (++counter)), reservationSDate, reservationEDate, foundRoom, foundGuest,
                            foundRoom.getPrice() * (reservationEDate.getDay() - reservationSDate.getDay()));
                    re++;
                    System.out.println("The reservation was successfully completed and reservation number is " + ("Re00" + counter) + "\n");

                } else {
                    System.out.println("The guest " + name + " is not a registered guest");
                }
            }
        } else {
            System.out.println("you exceeded the number of reservation requests");
        }

        System.out.println("---------------------------------------------------------");

    }

    public static Room chekingroomINfo(Room[] rooms, String roomcode, int gustnumber, Date reservationSDate, Date reservationEDate) {
        Room RoomCheck = checkRoom(rooms, roomcode);
        if (RoomCheck != null) {
            if ((!(RoomCheck.getGuestnumber() > gustnumber))) {

                if (((RoomCheck.getStartDate().before(reservationSDate) || RoomCheck.getStartDate().equals(reservationSDate))
                        && (RoomCheck.getEndtDate().after(reservationEDate) || RoomCheck.getEndtDate().equals(reservationEDate)))) {
                    RoomCheck.setStartDate(reservationEDate);
                    RoomCheck.setEndtDate(RoomCheck.getEndtDate());
                    return RoomCheck;
                } else {
                    System.out.println("The requested room is not available for the requested period");
                    return null;

                }
            } else {
                System.out.println("you exceeded the number of reservation requests");
                return null;

            }
        } else {
            System.out.println("The room number " + roomcode + " is not in the system");
            return null;

        }

    }

    public static Guest checkGuest(Guest[] guests, String guestname) {
        for (int i = 0; i < guests.length; i++) {
            if (guests[i].getName().equals(guestname)) {
                return guests[i];
            }
        }
        return null;
    }

    public static Room checkRoom(Room[] rooms, String roomcode) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getRoomCode().equals(roomcode)) {
                return rooms[i];
            }
        }
        return null;
    }

}

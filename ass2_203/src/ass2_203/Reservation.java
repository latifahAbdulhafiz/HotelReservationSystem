package ass2_203;

import java.util.Date;

public class Reservation {

    private String URN;
    private java.util.Date reservationSDate;
    private java.util.Date reservationEDate;
    private Room room;
    private Guest guest;
    private double Totalpayment;

    public Reservation(String URN, Date reservationSDate, Date reservationEDate, Room room, Guest guest, double Totalpayment) {
        this.URN = URN;
        this.reservationSDate = reservationSDate;
        this.reservationEDate = reservationEDate;
        this.room = room;
        this.guest = guest;
        this.Totalpayment = Totalpayment;
    }

    public Reservation() {
    }

    public void setURN(String URN) {
        this.URN = URN;
    }

    public void setReservationSDate(Date reservationSDate) {
        this.reservationSDate = reservationSDate;
    }

    public void setReservationEDate(Date reservationEDate) {
        this.reservationEDate = reservationEDate;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setTotalpayment(double Totalpayment) {
        this.Totalpayment = Totalpayment;
    }

    public String getURN() {
        return URN;
    }

    public Date getReservationSDate() {
        return reservationSDate;
    }

    public Date getReservationEDate() {
        return reservationEDate;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public double getTotalpayment() {
        return Totalpayment;
    }
    
    
}

package ass2_203;

import java.util.Date;

public class Room {

    private String roomCode;
    private String roomView;
    private String roomType;
    private java.util.Date startDate;
    private java.util.Date endtDate;
    private int guestnumber;
    private double price;

    public Room(String roomCode, String roomView, String roomType, Date startDate, Date endtDate, int guestnumber, double price) {
        this.roomCode = roomCode;
        this.roomView = roomView;
        this.roomType = roomType;
        this.startDate = startDate;
        this.endtDate = endtDate;
        this.guestnumber = guestnumber;
        this.price = price;
    }

    public Room() {
   this(  "0000",   "CityView",   "Standard",   new Date ()   , new Date ()    ,  2,  350);
 }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setRoomView(String roomView) {
        this.roomView = roomView;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndtDate(Date endtDate) {
        this.endtDate = endtDate;
    }

    public void setGuestnumber(int guestnumber) {
        this.guestnumber = guestnumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getRoomView() {
        return roomView;
    }

    public String getRoomType() {
        return roomType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndtDate() {
        return endtDate;
    }

    public int getGuestnumber() {
        return guestnumber;
    }

    public double getPrice() {
        return price;
    }
    
    
    
}

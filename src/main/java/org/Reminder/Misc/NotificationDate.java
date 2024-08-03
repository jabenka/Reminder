package org.Reminder.Misc;
//JUST USELESS BUT JAVA DATE CLASS AND CALENDAR IS BULLSHIT
public class NotificationDate {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int seconds;
    public NotificationDate(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.seconds = 0;
    }
    public NotificationDate() {
        year=0;
        month=0;
        day=0;
        hour=0;
        minute=0;
        seconds=0;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    public int getSeconds(){
        return seconds;
    }
    @Override
    public String toString() {
        return year + "-" + month + "-" + day + "-" + hour + "-" + minute;
    }
}

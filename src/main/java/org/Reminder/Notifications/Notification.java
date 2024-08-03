package org.Reminder.Notifications;
import org.Reminder.Misc.NotificationDate;

import java.util.Date;

public class Notification {
    private int id;
    private String title;
    private String message;
    private NotificationDate RemindAt;

    public Notification(String title, String message, NotificationDate remindAt, int id) {
        this.message = message;
        RemindAt = remindAt;
        this.title = title;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Notification:" +id+
                " Name: " + title+
                " Description: " + message +
                " Date: " + RemindAt.getYear()+"/"+ RemindAt.getMonth()+"/"+RemindAt.getDay()+"/"+RemindAt.getHour()+":"+RemindAt.getMinute();
    }
    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public NotificationDate getRemindAt() {
        return RemindAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRemindAt(NotificationDate remindAt) {
        RemindAt = remindAt;
    }


    public boolean checkTime() {
        boolean result=false;
        int time;
        if(RemindAt != null) {
          int year = RemindAt.getYear();
          int month = RemindAt.getMonth();
          int day = RemindAt.getDay();
          int hour = RemindAt.getHour();
          Date date=new Date();

          if(date.getYear()+1900==year){
              if(date.getMonth()+1==month){
                  if(date.getDate()==day){
                      if(date.getHours()==hour){
                          time = RemindAt.getMinute();
                          if(time==date.getMinutes() && RemindAt.getSeconds()==date.getSeconds()){
                              result=true;
                          }
                      }
                  }
              }
          }



        }

       return result;
    }
}

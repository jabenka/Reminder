package org.Reminder.Notifications;

import java.util.ArrayList;

public class NotificationManager {
    private ArrayList<Notification> notifications;
    public NotificationManager() {
        notifications = new ArrayList<>();
    }
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }
    public void removeNotification(Notification notification) {
        notifications.remove(notification);
    }
    public void removeAllNotifications() {
        notifications.clear();
    }
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }
    public Notification getNotification(String Name) {
        for (Notification notification : notifications) {
            if(notification.getTitle().equals(Name)) {
                return notification;
            }
        }
        return null;
    }




}

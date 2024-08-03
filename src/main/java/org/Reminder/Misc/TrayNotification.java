package org.Reminder.Misc;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class TrayNotification {
    private String NotificationTitle;
    private String NotificationText;
    public TrayNotification(){
        NotificationTitle="";
        NotificationText="";
    }

    public TrayNotification(String name, String message) {
        NotificationTitle = name;
        NotificationText = message;
    }

    public String getNotificationTitle() {
        return NotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        NotificationTitle = notificationTitle;
    }

    public String getNotificationText() {
        return NotificationText;
    }

    public void setNotificationText(String notificationText) {
        NotificationText = notificationText;
    }

    public void Display(){
        JFrame frame=new JFrame();
        frame.setSize(400,130);
        JPanel panel=new JPanel();
        SpringLayout layout=new SpringLayout();
        panel.setLayout(layout);
        JLabel Name=new JLabel();
        Name.setFont(new Font("Serif", Font.PLAIN, 30));
        Name.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,Name,0,SpringLayout.NORTH,panel);
        JLabel Message=new JLabel();
        Message.setFont(new Font("Serif",Font.PLAIN,20));
        Message.setSize(200,100);
        layout.putConstraint(SpringLayout.NORTH,Message,40,SpringLayout.NORTH,panel);
        Name.setText(NotificationTitle);
        Message.setText(NotificationText);

        panel.add(Name);
        panel.add(Message);
        frame.add(panel);
        frame.setLocation(1520,950);
        frame.setUndecorated(true);
        frame.setVisible(true);
        try {
            sleep(10000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        frame.dispose();



    }
}

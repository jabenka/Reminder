package org.Reminder.Environment;

import org.Reminder.Notifications.Notification;
import org.Reminder.Notifications.NotificationManager;

import java.io.*;

import org.Reminder.Misc.NotificationDate;
public class Config {
        NotificationManager manager;
        Config(NotificationManager manager){
          this.manager=manager;
        }
    public void Save() {
        File cfg = new File("cfg.txt");
        try {
            if(!cfg.exists()) {
                cfg.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("cfg.txt"));
            Integer size=manager.getNotifications().size();
            bw.write(String.valueOf(size));
            for(int i=0;i<manager.getNotifications().size();i++) {
                Notification n = manager.getNotifications().get(i);
                bw.newLine();
                bw.write(String.valueOf(n.getId()));
                bw.newLine();
                bw.write(n.getTitle());
                bw.newLine();
                bw.write(n.getMessage());
                bw.newLine();
                bw.write((n.getRemindAt().getYear())+"-"+(n.getRemindAt().getMonth())+"-"+n.getRemindAt().getDay()+"-"+n.getRemindAt().getHour()+"-"+n.getRemindAt().getMinute());
            }
            bw.write("\n");
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Load() {
        try(BufferedReader br = new BufferedReader(new FileReader("cfg.txt"))){
            int size=Integer.parseInt(br.readLine());
            String name="";
            String description="";
            NotificationDate notificationDate =null;
            String id="";
            for(int i=0;i<size;i++) {
                id=br.readLine();
                name=br.readLine();
                description=br.readLine();

               String data=br.readLine();
               String[] nums=data.split("-");
               notificationDate =new NotificationDate(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]),Integer.parseInt(nums[2]),Integer.parseInt(nums[3]),Integer.parseInt(nums[4]));
                manager.getNotifications().add(new Notification(name,description, notificationDate,Integer.parseInt(id)));
            }



        }catch (Exception e){
            e.printStackTrace();
        }



    }

}

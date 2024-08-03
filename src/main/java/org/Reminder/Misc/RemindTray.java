package org.Reminder.Misc;

import org.Reminder.Environment.Environment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class RemindTray {
    private SystemTray tray = null;
    private TrayIcon trayIcon = null;
    private Environment env;
    public RemindTray(String filepath,Environment env) {
        BufferedImage image=null;
        try{
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filepath)));

        }catch (IOException e){
            e.printStackTrace();
        }





        trayIcon=new TrayIcon(image, "Reminder Tray");
        tray = SystemTray.getSystemTray();
        this.env=env;
    }
    public void showTray() {
        try{
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Reminder Tray");
            tray.add(trayIcon);
        }catch(Exception e){
            e.printStackTrace();
        }
        PopupMenu menu = new PopupMenu();
        menu.setLabel("Menu");
        MenuItem Exit = new MenuItem("Exit");
        MenuItem New = new MenuItem("New");
        New.addActionListener(e->{
            env.Setup();
        });
        menu.add(Exit);
        menu.addSeparator();
        menu.add(New);
        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.setEnabled(true);
        trayIcon.setPopupMenu(menu);
    }



}

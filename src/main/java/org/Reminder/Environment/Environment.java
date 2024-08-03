package org.Reminder.Environment;

import org.Reminder.Notifications.Notification;
import org.Reminder.Notifications.NotificationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.Reminder.Misc.NotificationDate;


public class Environment extends JComponent {
    JFrame frame;
    SpringLayout layout;
    Container cp;
    public NotificationManager manager;
    static Integer ID= 1;
    Config cfg;
    DefaultListModel<Notification> model;
    public Environment(JFrame frame) {
        this.frame = frame;
        cp=frame.getContentPane();
        layout = new SpringLayout();
        manager=new NotificationManager();
        model=new DefaultListModel<>();
        cfg=new Config(manager);
    }

    public void Setup() {
        frame.setTitle("Environment");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
            }
        });
        try {


            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace();
        }
        frame.setSize(500,900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        GetEnvironment();
        frame.setVisible(true);
    }
    public void GetEnvironment() {
        frame.setLayout(layout);
        JLabel label=new JLabel("Reminder");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setSize(100,50);

        layout.putConstraint(SpringLayout.WEST,label,frame.getWidth()/2-label.getText().length()/2-60,SpringLayout.WEST,cp);
        layout.putConstraint(SpringLayout.NORTH,label,10,SpringLayout.NORTH,cp);



        //Name of Event
        JLabel SetName=new JLabel("Set name for notification");
        SetName.setFont(new Font("Serif", Font.PLAIN, 16));
        SetName.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,SetName,50,SpringLayout.NORTH,cp);
        JTextField GetName=new JTextField(12);
        GetName.setFont(new Font("Serif", Font.PLAIN, 16));
        layout.putConstraint(SpringLayout.NORTH,GetName,70,SpringLayout.NORTH,cp);
        //Description of event
        JLabel SetDescription=new JLabel("Set description for notification");
        SetDescription.setFont(new Font("Serif", Font.PLAIN, 16));
        SetDescription.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,SetDescription,100,SpringLayout.NORTH,cp);
        JTextField GetDescription=new JTextField(20);
        GetDescription.setFont(new Font("Serif", Font.PLAIN, 16));
        GetDescription.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,GetDescription,120,SpringLayout.NORTH,cp);
        //Date of Event
        JLabel SetDate=new JLabel("Set date for notification in format:"+"YYYY-MM-DD-HH-MM");
        SetDate.setFont(new Font("Serif", Font.PLAIN, 16));
        SetDate.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,SetDate,150,SpringLayout.NORTH,cp);
        JTextField GetDate=new JTextField(12);
        GetDate.setFont(new Font("Serif", Font.PLAIN, 16));
        GetDate.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,GetDate,170,SpringLayout.NORTH,cp);

        JLabel Created=new JLabel("");
        Created.setFont(new Font("Serif", Font.PLAIN, 16));
        Created.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,Created,260,SpringLayout.NORTH,cp);



        JButton push=new JButton("Create Notification");
        push.setFont(new Font("Serif", Font.PLAIN, 16));
        push.setSize(100,50);

        layout.putConstraint(SpringLayout.NORTH,push,220,SpringLayout.NORTH,cp);
        push.addActionListener(e->{
            String[] Data = GetDate.getText().split("-");
            int Year=0;
            int Month=0;

            NotificationDate notificationDate = new NotificationDate(Integer.parseInt(Data[0]), (Integer.parseInt(Data[1])), Integer.parseInt(Data[2]), Integer.parseInt(Data[3]), Integer.parseInt(Data[4]));
            Notification newNotification = new Notification(GetName.getText(), GetDescription.getText(), notificationDate,ID);
            ID++;
            manager.addNotification(newNotification);
            Created.setText("Notification created!");
            cfg.Save();
        });


        JButton View=new JButton("View All Notification");
        View.setFont(new Font("Serif", Font.PLAIN, 16));
        View.setSize(100,50);
        layout.putConstraint(SpringLayout.NORTH,View,800,SpringLayout.NORTH,cp);



        View.addActionListener(e->{
            JFrame ViewFrame=new JFrame();
            ViewFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {

                        ViewFrame.dispose();
                }
            });
            ViewFrame.setSize(500,600);
            ViewFrame.setLocationRelativeTo(null);
            JPanel panel=new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            manager.getNotifications().clear();
            cfg.Load();
            model=new DefaultListModel<>();
            model.addAll(manager.getNotifications());

            JButton Delete = new JButton("Delete Notification");
            Delete.setFont(new Font("Serif", Font.PLAIN, 16));
            Delete.setSize(100,50);
            JButton Edit = new JButton("Edit Notification");
            Edit.setFont(new Font("Serif", Font.PLAIN, 16));
            Edit.setSize(100,50);
            JButton Save=new JButton("Save Notifications");
            Save.setFont(new Font("Serif", Font.PLAIN, 16));
            Save.setSize(100,50);
            Delete.setLocation(0,0);

            JList<Notification> Notifications = getNotificationJList(model, Delete,Edit);

            Save.addActionListener(e1->{
                cfg.Save();
            });

            panel.add(Save);
            panel.add(Edit);
            panel.add(Delete);
            panel.add(Notifications);
            Notifications.setVisible(true);


            ViewFrame.add(panel);

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            ViewFrame.add(scrollPane);
            Notifications.setFont(new Font("Serif", Font.PLAIN, 16));


            ViewFrame.setVisible(true);
            });








        cp.add(label);
        cp.add(SetName);
        cp.add(GetName);
        cp.add(SetDescription);
        cp.add(GetDescription);
        cp.add(SetDate);
        cp.add(GetDate);
        cp.add(push);
        cp.add(View);
        cp.add(Created);

    }

    private JList<Notification> getNotificationJList(DefaultListModel<Notification> model, JButton Delete,JButton Edit) {
        JList<Notification> Notifications=new JList<>(model);
        Notifications.setSize(500,400);
        Notifications.setLocation(0, 200);
        Notifications.addListSelectionListener(e1 -> {
            if (!e1.getValueIsAdjusting()) {
                Notification SelectedNot = Notifications.getSelectedValue();
                Delete.addActionListener(e2-> {
                            manager.removeNotification(SelectedNot);
                            model.removeAllElements();
                            cfg.Save();
                            model.addAll(manager.getNotifications());
                            Notifications.setModel(model);
                        });
                Edit.addActionListener(e3->{
                    JFrame EditFrame=new JFrame();
                    EditFrame.setTitle("Edit");
                    EditFrame.setSize(400,350);
                    EditFrame.setLocationRelativeTo(null);
                    EditFrame.setVisible(true);
                    JPanel panel=new JPanel();
                    EditFrame.add(panel);
                    SpringLayout EditLayout = new SpringLayout();
                    panel.setLayout(EditLayout);

                    //Edit name
                    JTextField setName=new JTextField(12);
                    JLabel Name=new JLabel("Edit Name of Notification");
                    Name.setFont(new Font("Serif", Font.PLAIN, 16));
                    EditLayout.putConstraint(SpringLayout.NORTH,Name,10,SpringLayout.NORTH,panel);
                    EditLayout.putConstraint(SpringLayout.NORTH,setName,30,SpringLayout.NORTH,panel);

                    //Edit Description
                    JLabel Description=new JLabel("Edit Description of Notification");
                    Description.setFont(new Font("Serif", Font.PLAIN, 16));
                    JTextField setDescription=new JTextField(20);
                    EditLayout.putConstraint(SpringLayout.NORTH,Description,70,SpringLayout.NORTH,panel);
                    EditLayout.putConstraint(SpringLayout.NORTH,setDescription,90,SpringLayout.NORTH,panel);

                    //Edit Date
                    JLabel Date=new JLabel("Edit Date of Notification");
                    Date.setFont(new Font("Serif", Font.PLAIN, 16));
                    JTextField setDate=new JTextField(12);
                    EditLayout.putConstraint(SpringLayout.NORTH,Date,130,SpringLayout.NORTH,panel);
                    EditLayout.putConstraint(SpringLayout.NORTH,setDate,150,SpringLayout.NORTH,panel);





                    JButton submit=new JButton("Submit changes");
                    submit.setFont(new Font("Serif", Font.PLAIN, 16));
                    submit.setSize(100,50);
                    EditLayout.putConstraint(SpringLayout.NORTH,submit,250,SpringLayout.NORTH,panel);
                    EditLayout.putConstraint(SpringLayout.WEST,submit,125,SpringLayout.WEST,panel);
                    submit.addActionListener(e->{
                        Notification not=new Notification("","",new NotificationDate(),SelectedNot.getId());
                        String name = setName.getText();
                        String description = setDescription.getText();
                        NotificationDate notificationDate =null;
                        if(!setDate.getText().isEmpty()) {
                            String[] Data = setDate.getText().split("-");

                            notificationDate = new NotificationDate(Integer.parseInt(Data[0]), Integer.parseInt(Data[1]), Integer.parseInt(Data[2]), Integer.parseInt(Data[3]), Integer.parseInt(Data[4]));

                        }
                        if(!name.isEmpty()){
                            not.setTitle(name);
                        }else{
                            not.setTitle(SelectedNot.getTitle());
                        }
                        if(!description.isEmpty()){
                            not.setMessage(description);
                        }
                        else{
                            not.setMessage(SelectedNot.getMessage());
                        }
                        if(notificationDate !=null){
                            not.setRemindAt(notificationDate);
                        }else{
                            not.setRemindAt(SelectedNot.getRemindAt());
                        }
                        manager.removeNotification(SelectedNot);
                        model.removeAllElements();
                        manager.addNotification(not);
                        cfg.Save();
                        model.addAll(manager.getNotifications());
                        Notifications.setModel(model);

                        EditFrame.dispose();

                    });




                    panel.add(Name);
                    panel.add(setName);
                    panel.add(Description);
                    panel.add(setDescription);
                    panel.add(Date);
                    panel.add(setDate);
                    panel.add(submit);


                });
            }
        });
        return Notifications;
    }

}

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class MainMenuGUI implements ActionListener
{

    CustomerProfDB db;
    private ButtonGroup options;
    private JFrame menu;
    String fName;
    String curAdminID;

    public MainMenuGUI(String fName,String adminID)
    {
        this.fName = fName;
        this.curAdminID = adminID;
        db = new CustomerProfDB(fName);

        menu = new JFrame();
        menu.setTitle("Customer System Interface");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);
        menu.setSize(600, 600);
        menu.setBackground(Color.red);

        JLabel header = new JLabel();
        header.setText("Choose one option bellow and then hit the select button");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.TOP);
        header.setFont(new Font("Calibri", Font.PLAIN, 18));

        JRadioButton createProfile = new JRadioButton("Create Profile");
        createProfile.setActionCommand("create");
        createProfile.setHorizontalAlignment(JRadioButton.CENTER);

        JRadioButton deleteProfile = new JRadioButton("Delete Profile");
        deleteProfile.setActionCommand("delete");
        deleteProfile.setHorizontalAlignment(JRadioButton.CENTER);

        JRadioButton updateProfile = new JRadioButton("Update Profile");
        updateProfile.setActionCommand("update");
        updateProfile.setHorizontalAlignment(JRadioButton.CENTER);

        JRadioButton findProfile = new JRadioButton("Find/Display Profile");
        findProfile.setActionCommand("display");
        findProfile.setHorizontalAlignment(JRadioButton.CENTER);

        JRadioButton displayAllProfiles = new JRadioButton("Display All Profiles");
        displayAllProfiles.setActionCommand("display_all");
        displayAllProfiles.setHorizontalAlignment(JRadioButton.CENTER);


        JButton select = new JButton();
        select.setText("Select");
        select.setSize(100, 100);
        select.addActionListener(this);



        JPanel buttonZone = new JPanel();
        buttonZone.setBounds(100, 100, 400, 400);
        buttonZone.setLayout(new GridLayout(0,1));


        options = new ButtonGroup();
        options.add(createProfile);
        options.add(deleteProfile);
        options.add(updateProfile);
        options.add(findProfile);
        options.add(displayAllProfiles);

        buttonZone.add(header);
        buttonZone.add(createProfile);
        buttonZone.add(deleteProfile);
        buttonZone.add(updateProfile);
        buttonZone.add(findProfile);
        buttonZone.add(displayAllProfiles);
        buttonZone.add(select);

        menu.add(buttonZone);
        menu.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        ButtonModel selectedButton = options.getSelection();
        String selected = selectedButton.getActionCommand();

        //dp = delete profile
        //up = update profile
        //fp = find profile
        //da = display all profile

        if(selected == "create")
        {
            //call create gui
            CreateProfileGUI gui = new CreateProfileGUI(db);
        }
        if(selected == "update")
        {
            //call update gui
            UpdatePart1GUI updatePart1GUI = new UpdatePart1GUI(db,fName);
            menu.dispose();
        }
        if(selected == "delete")
        {
            //call delete gui
            DeleteFirstGui deleteFirstGui = new DeleteFirstGui(db,fName,"None");
            menu.dispose();
        }
        if(selected == "display")
        {
            AdminIdGui adminId = new AdminIdGui(db,fName,"fp");
            menu.dispose();
        }
        if(selected == "display_all")
        {


            //call display all gui

            AdminIdGui adminId = new AdminIdGui(db,fName,"da");
            menu.dispose();
        }
        if(selected == "write")
        {
            //call write gui
            System.out.println("write");
        }

    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter address path of database");
        String f = scan.nextLine();
        //C:\Users\Steven Siveyer\Documents\School\CSE_2102\testDoc.txt
        //C:\\Users\\yeyin\\Desktop\\Test.txt

        MainMenuGUI main = new MainMenuGUI(f,"None");
    }

}

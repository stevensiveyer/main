import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminIdGui implements ActionListener {

    private String fname;
    private String curAdminId;

    JFrame frame1;
    private static JLabel adminLabel;
    private static JTextField adminTextField;
    private static JButton enterButton;
    private String option;
    private CustomerProfDB database;


    public AdminIdGui(CustomerProfDB database,String fname, String option)
    {
        this.fname = fname;
        this.option = option;
        this.database = database;
        //C:\\Users\\yeyin\\Desktop\\Test.txt
        frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        frame1.setTitle("AdminID");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel1);
        panel1.setLayout(null);
        frame1.setSize(200,120);
        frame1.setLocationRelativeTo(null);

        //Setting up label
        adminLabel = new JLabel("AminID : ");
        adminLabel.setBounds(20,20,80,25);
        panel1.add(adminLabel);

        //Setting up textfield
        adminTextField = new JTextField();
        adminTextField.setBounds(80,20,80,25);
        panel1.add(adminTextField);

        //Setting up button
        enterButton = new JButton("ENTER");
        enterButton.setBounds(80,40,80,25);
        enterButton.addActionListener(this);
        panel1.add(enterButton);

        frame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        curAdminId = adminTextField.getText();
        if (option.equals("cp"))
        {

        }else if(option.equals("dp"))
        {

        }else if(option.equals("up"))
        {

        }else if(option.equals("fp"))
        {
            AskLastNameGUI lastNameGUI = new AskLastNameGUI(database,fname,curAdminId);

        }else if(option.equals("da"))
        {
            DisplayAllProfilesGUI diplayAll = new DisplayAllProfilesGUI(database,fname,curAdminId,-1);

        }
        frame1.dispose();
    }

    public static class AskLastNameGUI implements ActionListener {
        private String fname;
        private String lastName;
        private String curAdminId;

        JFrame frame1;
        private static JLabel adminLabel;
        private static JTextField lastNameTextField;
        private static JButton enterButton;
        private String option;
        private CustomerProfDB database;


        public AskLastNameGUI(CustomerProfDB database, String fname, String curAdminId)
        {
            this.fname = fname;
            this.database = database;
            this.curAdminId = curAdminId;
            //C:\\Users\\yeyin\\Desktop\\Test.txt
            frame1 = new JFrame();
            JPanel panel1 = new JPanel();
            frame1.setTitle("Enter Person Last Name");
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.add(panel1);
            panel1.setLayout(null);
            frame1.setSize(200,120);

            //Setting up label
            adminLabel = new JLabel("Last Name : ");
            adminLabel.setBounds(20,20,80,25);
            panel1.add(adminLabel);

            //Setting up textfield
            lastNameTextField = new JTextField();
            lastNameTextField.setBounds(80,20,80,25);
            panel1.add(lastNameTextField);

            //Setting up button
            enterButton = new JButton("ENTER");
            enterButton.setBounds(80,40,80,25);
            enterButton.addActionListener(this);
            panel1.add(enterButton);

            frame1.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            lastName = lastNameTextField.getText();
            FindDisplayProfileGUI findGui = new FindDisplayProfileGUI(database,fname,curAdminId,lastName);
            frame1.dispose();

        }
    }
}

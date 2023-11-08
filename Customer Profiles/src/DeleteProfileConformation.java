import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProfileConformation implements ActionListener {


    private String fname;
    private String curAdminId;

    JFrame frame1;
    private static JLabel adminLabel;
    private static JButton enterButton;
    private String option;


    public DeleteProfileConformation(String fname)
    {
        this.fname = fname;
        //C:\\Users\\yeyin\\Desktop\\Test.txt
        frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        frame1.setTitle("AdminID");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel1);
        panel1.setLayout(null);
        frame1.setSize(200,160);
        frame1.setLocationRelativeTo(null);

        //Setting up label
        adminLabel = new JLabel("Profile deleted");
        adminLabel.setBounds(20,20,150,25);
        panel1.add(adminLabel);


        //Setting up button
        enterButton = new JButton("MAIN");
        enterButton.setBounds(90,80,80,25);
        enterButton.addActionListener(this);
        panel1.add(enterButton);

        frame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenuGUI main = new MainMenuGUI(fname,"None");
        frame1.dispose();
    }

}

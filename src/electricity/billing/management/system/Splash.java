package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame{
    Splash(){

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/wallpaper.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(642, 360, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);

        setLayout(null);

        imageLabel.setBounds(0, 0, 642, 360);
        add(imageLabel);

        JLabel mainLabel = new JLabel("Electricity Billing Management System");
        mainLabel.setForeground(Color.WHITE);
        mainLabel.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        mainLabel.setBounds(142, 20, 400, 40);
        imageLabel.add(mainLabel);

        JLabel label = new JLabel("Loading, Please Wait...");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 10));
        label.setBounds(200, 320, 250, 20);
        imageLabel.add(label);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBounds(200, 340, 250, 5);
        imageLabel.add(progressBar);

        setSize(642, 360);
        setLocation(342, 200);
        setUndecorated(true);
        setVisible(true);

        try{
            Thread.sleep(4000);
            new Login();
            setVisible(false);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        new Splash();
    }
}

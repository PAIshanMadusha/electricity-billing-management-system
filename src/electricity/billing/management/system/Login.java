package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JTextField userText;
    JPasswordField passwordText;
    Choice loginChoice;
    JButton signInButton, cancelButton, signUpButton;
    Login(){
        super("Login Form");

        JLabel userName = new JLabel("UserName: ");
        userName.setFont(new Font("System", Font.BOLD, 14));
        userName.setForeground(Color.BLACK);
        userName.setBounds(279, 80, 100, 25);
        add(userName);

        userText = new JTextField();
        userText.setBounds(400, 80, 180, 25);
        add(userText);

        JLabel password = new JLabel("Password: ");
        password.setForeground(Color.BLACK);
        password.setFont(new Font("System", Font.BOLD, 14));
        password.setBounds(279, 120, 100, 25);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(400, 120, 180, 25);
        add(passwordText);

        JLabel loggingAs = new JLabel("Logging In As: ");
        loggingAs.setForeground(Color.BLACK);
        loggingAs.setFont(new Font("System", Font.BOLD, 14));
        loggingAs.setBounds(279, 160, 120, 25);
        add(loggingAs);

        loginChoice = new Choice();
        loginChoice.add("Customer");
        loginChoice.add("Admin");
        loginChoice.setBounds(400, 160, 180, 25);
        add(loginChoice);

        signInButton = new JButton("SignIn");
        signInButton.setBackground(Color.GREEN);
        signInButton.setFont(new Font("System", Font.BOLD, 12));
        signInButton.setForeground(Color.WHITE);
        signInButton.setBounds(400, 225, 80, 25);
        signInButton.addActionListener(this);
        add(signInButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setFont(new Font("System", Font.BOLD, 12));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(500, 225, 80, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signUpButton = new JButton("SignUp");
        signUpButton.setBackground(Color.GREEN);
        signUpButton.setFont(new Font("System", Font.BOLD, 12));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBounds(400, 260, 180, 25);
        signUpButton.addActionListener(this);
        add(signUpButton);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/loginFrame.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(642, 360, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(0, 0, 642, 360);
        add(imageLabel);

        setSize(642, 360);
        getContentPane().setBackground(new Color(224, 255, 233));
        setLocation(342, 200);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == signInButton){
            String sUserName = userText.getText();
            String sPassword = passwordText.getText();
            String sUser = loginChoice.getSelectedItem();
            if(userText.getText().equals("") || passwordText.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Fill All the Fields");
                return;
            }
            try{
                Db db = new Db();
                String q = "select * from signup where userName = ? and password = ? and userType = ?";
                PreparedStatement preparedStatement = db.connection.prepareStatement(q);

                preparedStatement.setString(1, sUserName);
                preparedStatement.setString(2, sPassword);
                preparedStatement.setString(3, sUser);

                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    new Main_Class(sUser, meter);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Enter Valid Login Details");
                }

            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource() == cancelButton){
            this.dispose();
        }else if(e.getSource() == signUpButton){
            new Signup();
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new Login();
    }
}

package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Signup extends JFrame implements ActionListener{
    Choice loginAsChoice;
    JTextField meterText, employerText, userNameText, nameText, adminCodeText;
    JPasswordField passwordText;
    JButton create, back;
    Signup(){

        super("SignUp Form");

        JLabel createAs = new JLabel("Create Account As: ");
        createAs.setFont(new Font("System", Font.BOLD, 14));
        createAs.setBounds(30, 30, 140, 20);
        add(createAs);

        loginAsChoice = new Choice();
        loginAsChoice.add("Customer");
        loginAsChoice.add("Admin");

        loginAsChoice.setBounds(170, 30, 180, 20);
        add(loginAsChoice);

        JLabel meterNo = new JLabel("Meter No: ");
        meterNo.setFont(new Font("System", Font.BOLD, 14));
        meterNo.setBounds(30, 80, 125, 20);
        meterNo.setVisible(true);
        add(meterNo);

        meterText = new JTextField();
        meterText.setBounds(170, 80, 180, 20);
        meterText.setVisible(true);
        add(meterText);

        JLabel employer = new JLabel("Employer ID: ");
        employer.setFont(new Font("System", Font.BOLD, 14));
        employer.setBounds(30, 80, 120, 20);
        employer.setVisible(false);
        add(employer);

        employerText = new JTextField();
        employerText.setBounds(170, 80, 180, 20);
        employerText.setVisible(false);
        add(employerText);

        JLabel userName = new JLabel("User Name: ");
        userName.setFont(new Font("System", Font.BOLD, 14));
        userName.setBounds(30, 130, 120, 20);
        add(userName);

        userNameText = new JTextField();
        userNameText.setBounds(170, 130, 180, 20);
        add(userNameText);

        JLabel name = new JLabel("Your Name: ");
        name.setFont(new Font("System", Font.BOLD, 14));
        name.setBounds(30, 180, 120, 20);
        add(name);

        nameText = new JTextField("");
        nameText.setBounds(170, 180, 180, 20);
        add(nameText);

        JLabel adminCodeLabel = new JLabel("ADMIN CODE");
        adminCodeLabel.setFont(new Font("System", Font.BOLD, 16));
        adminCodeLabel.setForeground(Color.RED);
        adminCodeLabel.setBounds(430, 110, 120, 20);
        adminCodeLabel.setVisible(false);
        add(adminCodeLabel);

        adminCodeText = new JTextField();
        adminCodeText.setBackground(Color.RED);
        adminCodeText.setForeground(Color.WHITE);
        adminCodeText.setBounds(390, 130, 180, 20);
        adminCodeText.setVisible(false);
        add(adminCodeText);

        loginAsChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginAsChoice.getSelectedItem();
                if(user.equals("Admin")){
                    adminCodeLabel.setVisible(true);
                    adminCodeText.setVisible(true);
                }else{
                    adminCodeLabel.setVisible(false);
                    adminCodeText.setVisible(false);
                }
            }
        });
        nameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }
            @Override
            public void focusLost(FocusEvent e) {

                try{
                    Db db = new Db();
                    String q = "select * from signup where meter_no = ?";
                    PreparedStatement ps = db.connection.prepareStatement(q);
                    ps.setString(1, meterText.getText().toString());

                    ResultSet resultSet = ps.executeQuery();
                    if(resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }
                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password: ");
        password.setFont(new Font("System", Font.BOLD, 14));
        password.setBounds(30, 230, 120, 20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(170, 230, 180, 20);
        add(passwordText);

        loginAsChoice.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                String user = loginAsChoice.getSelectedItem();
                if(user.equals("Admin")){
                    employer.setVisible(true);
                    employerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }else{
                    employer.setVisible(false);
                    nameText.setEditable(false);
                    employerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }
            }
        });
        create = new JButton("Create");
        create.setBackground(Color.GREEN);
        create.setForeground(Color.WHITE);
        create.setFont(new Font("System", Font.BOLD, 12));
        create.setBounds(170, 280, 80, 25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("System", Font.BOLD, 12));
        back.setBounds(270, 280, 80, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/loginFrame2.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(642, 360, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(0, 0, 642, 360);
        add(imageLabel);

        setSize(642, 360);
        setLocation(342, 200);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == create){

            String sLoginAs = loginAsChoice.getSelectedItem();
            String sUserName = userNameText.getText();
            String sName = nameText.getText();
            String sPassword = passwordText.getText();
            String sMeter = meterText.getText();
            String sEmployeeId = employerText.getText();
            try{
                if(userNameText.getText().equals("") || passwordText.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Fill UseName and Password Field ");
                    return;
                }
                if(sLoginAs.equals("Admin")){
                    String adminCode = adminCodeText.getText();
                    if(!adminCode.equals("admin123")){
                        JOptionPane.showMessageDialog(null, "Invalid Admin Code!");
                        return;
                    }
                }
                Db db = new Db();
                String q = null;
                PreparedStatement ps;
                if(sLoginAs.equals("Admin")){
                    q = "insert into signup (meter_no, userName, name, password, userType) values (?, ?, ?, ?, ?)";
                    ps = db.connection.prepareStatement(q);
                    ps.setString(1, sEmployeeId);
                    ps.setString(2, sUserName);
                    ps.setString(3, sName);
                    ps.setString(4, sPassword);
                    ps.setString(5, sLoginAs);
                }else{
                    q = "update signup set userName = ?, password = ?, userType = ? where meter_no = ?";
                    ps = db.connection.prepareStatement(q);
                    ps.setString(1, sUserName);
                    ps.setString(2, sPassword);
                    ps.setString(3, sLoginAs);
                    ps.setString(4, sMeter);
                }

                int rowAffected = ps.executeUpdate();
                if(rowAffected > 0){
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                    setVisible(false);
                    new Login();
                }
            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource() == back){
            setVisible(false);
            new Login();
        }
    }
    public static void main(String args[]){
        new Signup();
    }
}

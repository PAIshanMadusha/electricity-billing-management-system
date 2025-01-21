package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class NewCustomer extends JFrame implements ActionListener{
    JLabel heading, customerName, meterNo, meterNoText, address, city, state, email, phone;
    JTextField nameText, addressText, cityText, stateText, emailText, phoneText;
    JButton next, cancel;
    NewCustomer(){
        super("New Customer Form");

        Border backLine = BorderFactory.createLineBorder(Color.gray);

        setSize(700, 500);
        setLocation(340, 130);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 255));
        add(panel);

        heading =new JLabel("NEW CUSTOMER");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(105, 10, 200, 25);
        panel.add(heading);

        customerName = new JLabel("Customer name: ");
        customerName.setFont(new Font("serif", Font.BOLD, 14));
        customerName.setBounds(20, 80, 120, 20);
        panel.add(customerName);

        nameText = new JTextField();
        nameText.setBounds(150, 80, 160, 20);
        panel.add(nameText);

        meterNo = new JLabel("Meter Number: ");
        meterNo.setFont(new Font("serif", Font.BOLD, 14));
        meterNo.setBounds(20, 120, 120, 20);
        panel.add(meterNo);

        meterNoText = new JLabel();
        meterNoText.setBorder(backLine);
        meterNoText.setFont(new Font("serif", Font.BOLD, 14));
        meterNoText.setBounds(150, 120, 160, 20);
        panel.add(meterNoText);

        Random random = new Random();
        long number = random.nextLong() % 1000000;
        meterNoText.setText("" + Math.abs(number));

        address = new JLabel("Address: ");
        address.setFont(new Font("serif", Font.BOLD, 14));
        address.setBounds(20, 160, 100, 20);
        panel.add(address);

        addressText = new JTextField();
        addressText.setBounds(150, 160, 160, 20);
        panel.add(addressText);

        city = new JLabel("City: ");
        city.setFont(new Font("serif", Font.BOLD, 14));
        city.setBounds(20, 200, 100, 20);
        panel.add(city);

        cityText = new JTextField();
        cityText.setBounds(150, 200, 160, 20);
        panel.add(cityText);

        state = new JLabel("State: ");
        state.setFont(new Font("serif", Font.BOLD, 14));
        state.setBounds(20, 240, 100, 20);
        panel.add(state);

        stateText = new JTextField();
        stateText.setBounds(150, 240, 160, 20);
        panel.add(stateText);

        email = new JLabel("Email: ");
        email.setFont(new Font("serif", Font.BOLD, 14));
        email.setBounds(20, 280, 100, 20);
        panel.add(email);

        emailText = new JTextField();
        emailText.setBounds(150, 280, 160, 20);
        panel.add(emailText);

        phone = new JLabel("Phone Number: ");
        phone.setFont(new Font("serif", Font.BOLD, 14));
        phone.setBounds(20, 320, 120, 20);
        panel.add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(150, 320, 160, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBackground(Color.GREEN);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Serif", Font.BOLD, 12));
        next.setBounds(75, 390, 100, 25);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Serif", Font.BOLD, 12));
        cancel.setBounds(185, 390, 100, 25);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/newCustomerWall.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(330, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel, "West");

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == next){
            String sName = nameText.getText();
            String sMeter = meterNoText.getText();
            String sAddress = addressText.getText();
            String sCity = cityText.getText();
            String sState = stateText.getText();
            String sEmail = emailText.getText();
            String sPhone = phoneText.getText();

            try{
                if(!validateForm()) {
                    return;
                }
                Db db = new Db();
                String q1 = "insert into new_customer(name, meterNo, address, city, state, email, phoneNo) values(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1 = db.connection.prepareStatement(q1);

                ps1.setString(1, sName);
                ps1.setString(2, sMeter);
                ps1.setString(3, sAddress);
                ps1.setString(4, sCity);
                ps1.setString(5, sState);
                ps1.setString(6, sEmail);
                ps1.setString(7, sPhone);

                ps1.executeUpdate();

                String q2 = "insert into signup(meter_no, userName, name, password, userType) values(?, '', ?, '', '')";
                PreparedStatement ps2 = db.connection.prepareStatement(q2);

                ps2.setString(1, sMeter);
                ps2.setString(2, sName);

                ps2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully!");

                new MeterInformation(sMeter);
                setVisible(false);
            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource() == cancel){
            this.dispose();
        }
    }
    private boolean validateForm(){
        if(nameText.getText().isEmpty()){
            showError("Fill the Name Field");
            return false;
        }
        if(addressText.getText().isEmpty()){
            showError("Fill the Address Field");
            return false;
        }
        if(cityText.getText().isEmpty()){
            showError("Fill the City Field");
            return false;
        }
        if(stateText.getText().isEmpty()){
            showError("Fill the State Field");
            return false;
        }
        if(!isValidEmail(emailText.getText())){
            showError("Enter a Valid Email Address");
            return false;
        }
        if(phoneText.getText().isEmpty()){
            showError("Fill the Phone Number Field");
            return false;
        }
        return true;
    }
    public static boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private void showError(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    public static void main(String args[]){
        new NewCustomer();
    }
}
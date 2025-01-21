package electricity.billing.management.system;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Update_Information extends JFrame implements ActionListener {
    JLabel nameText;
    JTextField addressText, cityText, stateText, emailText, phoneNoText;
    JButton update, cancel;
    String meter;
    Update_Information(String meter){

        super("Update Information");

        this.meter = meter;
        setBounds(340, 130, 700, 500);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setLayout(null);

        Border backLine = BorderFactory.createLineBorder(Color.gray);

        JLabel headline = new JLabel("Update Customer Information");
        headline.setFont(new Font("Serif", Font.BOLD, 20));
        headline.setBounds(70, 5, 500, 40);
        add(headline);

        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("Serif", Font.BOLD, 17));
        name.setBounds(50, 80, 100, 20);
        add(name);

        nameText = new JLabel("");
        nameText.setBorder(backLine);
        nameText.setBounds(150, 80, 200, 20);
        add(nameText);

        JLabel meterNo = new JLabel("Meter No: ");
        meterNo.setFont(new Font("Serif", Font.BOLD, 17));
        meterNo.setBounds(50, 120, 100, 20);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBorder(backLine);
        meterText.setBounds(150, 120, 200, 20);
        add(meterText);

        JLabel address = new JLabel("Address: ");
        address.setFont(new Font("serif", Font.BOLD, 17));
        address.setBounds(50, 160, 100, 20);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(150, 160, 200, 20);
        add(addressText);

        JLabel city = new JLabel("City: ");
        city.setFont(new Font("serif", Font.BOLD, 17));
        city.setBounds(50, 200, 100, 20);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(150, 200, 200, 20);
        add(cityText);

        JLabel state = new JLabel("State: ");
        state.setFont(new Font("serif", Font.BOLD, 17));
        state.setBounds(50, 240, 100, 20);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(150, 240, 200, 20);
        add(stateText);

        JLabel email = new JLabel("Email: ");
        email.setFont(new Font("serif", Font.BOLD, 17));
        email.setBounds(50, 280, 100, 20);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(150, 280, 200, 20);
        add(emailText);

        JLabel phone = new JLabel("Phone No: ");
        phone.setFont(new Font("serif", Font.BOLD, 17));
        phone.setBounds(50, 320, 100, 20);
        add(phone);

        phoneNoText = new JTextField();
        phoneNoText.setBounds(150, 320, 200, 20);
        add(phoneNoText);

        try {
            Db db = new Db();
            String q = "select * from new_customer where meterNo = ?";
            PreparedStatement ps = db.connection.prepareStatement(q);
            ps.setString(1, meter.toString());

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meterNo"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneNoText.setText(resultSet.getString("phoneNo"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Serif", Font.BOLD, 16));
        cancel.setBounds(80, 390, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        update = new JButton("Update");
        update.setBackground(Color.GREEN);
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Serif", Font.BOLD, 16));
        update.setBounds(200, 390, 100, 25);
        update.addActionListener(this);
        add(update);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(310, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(355, 0, 350, 500);
        add(imageLabel);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == update){
            String sAddress = addressText.getText();
            String sCity = cityText.getText();
            String sState = stateText.getText();
            String sEmail = emailText.getText();
            String sPhoneNo = phoneNoText.getText();

            try{
                if(!validateForm()){
                    return;
                }
                Db db = new Db();
                String q = "update new_customer set address = ?, city = ?, state = ?, email = ?, phoneNo = ? where meterNo = ?";
                PreparedStatement ps = db.connection.prepareStatement(q);

                ps.setString(1, sAddress.toString());
                ps.setString(2, sCity.toString());
                ps.setString(3, sState.toString());
                ps.setString(4, sEmail.toString());
                ps.setString(5, sPhoneNo.toString());
                ps.setString(6, meter.toString());

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Details Updated Successfully!");
                setVisible(false);

            }catch(Exception E){
                E.printStackTrace();
            }
        }else{
            this.dispose();
        }
    }
    private boolean validateForm(){
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
        if(!isValidateEmail(emailText.getText())){
            showError("Enter a Valid Email");
            return false;
        }
        if(phoneNoText.getText().isEmpty()){
            showError("Fill the Phone Number Field");
            return false;
        }
        return true;
    }
    private boolean isValidateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private void showError(String error){
        JOptionPane.showMessageDialog(null, error);
    }
    public static void main(String[] args) {
        new Update_Information("");
    }
}

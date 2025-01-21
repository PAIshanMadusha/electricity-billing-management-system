package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
    Choice meterNumChoice, monthChoice;
    JLabel nameText, addressText;
    JTextField unitText;
    JButton submit, cancel;
    CalculateBill(){

        super("Calculate Bill Form");

        Border backLine = BorderFactory.createLineBorder(Color.GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground( Color.WHITE);
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setFont(new Font("serif", Font.BOLD, 18));
        heading.setBounds(70, 10, 300, 30);
        panel.add(heading);

        JLabel meterNum = new JLabel("Meter Number: ");
        meterNum.setFont(new Font("Serif", Font.BOLD, 14));
        meterNum.setBounds(50, 80, 100, 20);
        panel.add(meterNum);

        meterNumChoice = new Choice();
        try{

            Db db = new Db();
            String q1 = "select * from new_customer";
            PreparedStatement ps1 = db.connection.prepareStatement(q1);

            ResultSet resultSet = ps1.executeQuery();
            while(resultSet.next()){
                meterNumChoice.add(resultSet.getString("meterNo"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        meterNumChoice.setBounds(180, 80, 170, 20);
        panel.add(meterNumChoice);

        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("serif", Font.BOLD, 14));
        name.setBounds(50, 120, 100, 20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setFont(new Font("serif", Font.BOLD, 14));
        nameText.setBorder(backLine);
        nameText.setBounds(180, 120, 170, 20);
        panel.add(nameText);

        JLabel address = new JLabel("Address: ");
        address.setFont(new Font("serif", Font.BOLD, 14));
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setFont(new Font("Serif", Font.BOLD, 14));
        addressText.setBorder(backLine);
        addressText.setBounds(180, 160, 170, 20);
        panel.add(addressText);

        try{
            Db db = new Db();
            String q2 = "select * from new_customer where meterNo = ?";
            PreparedStatement ps2 = db.connection.prepareStatement(q2);
            ps2.setString(1, meterNumChoice.getSelectedItem().toString());

            ResultSet resultSet = ps2.executeQuery();
            while(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
            }
        }catch(Exception E){
            E.printStackTrace();
        }
        meterNumChoice.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                try{
                    Db db = new Db();
                    String q2 = "select * from new_customer where meterNo = ?";
                    PreparedStatement ps2 = db.connection.prepareStatement(q2);
                    ps2.setString(1, meterNumChoice.getSelectedItem().toString());

                    ResultSet resultSet = ps2.executeQuery();
                    while(resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                    }
                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel unitConsumed = new JLabel("Unit Consumed: ");
        unitConsumed.setFont(new Font("serif", Font.BOLD, 14));
        unitConsumed.setBounds(50, 200, 120, 20);
        panel.add(unitConsumed);

        unitText = new JTextField();
        unitText.setBounds(180, 200, 170, 20);
        panel.add(unitText);

        JLabel month = new JLabel("Month: ");
        month.setFont(new Font("serif", Font.BOLD, 14));
        month.setBounds(50, 240, 100, 20);
        panel.add(month);

        monthChoice = new Choice();
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("may");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");
        monthChoice.setBounds(180, 240, 170, 20);
        panel.add(monthChoice);

        submit = new JButton("Submit");
        submit.setBackground(Color.GREEN);
        submit.setFont(new Font("serif", Font.BOLD, 12));
        submit.setForeground(Color.WHITE);
        submit.setBounds(80, 310, 100, 25);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setFont(new Font("serif", Font.BOLD, 12));
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(200, 310, 100, 25);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/bulb.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(220, 368, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel, "East");

        setSize(600, 400);
        setLocation(380, 170);
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit){

            String sMeterNo = meterNumChoice.getSelectedItem();
            String sMonth = monthChoice.getSelectedItem();
            String sUnit = unitText.getText();

            if(unitText.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill the Unit Consumed Field");
                return;
            }
            if(!unitText.getText().matches("\\d+")){
                JOptionPane.showMessageDialog(null, "Please Enter Only Numeric Values for Units Consumed!");
                return;
            }
            try{
                Db db = new Db();

                String q = "select * from tax";
                PreparedStatement ps = db.connection.prepareStatement(q);
                ResultSet resultSet = ps.executeQuery();

                int totalBill = 0;
                int units = Integer.parseInt(sUnit);

                while(resultSet.next()) {
                    totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("service_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("vat_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }

                String q2 = "insert into bill(meterNo, month, unit, total_bill, status) values(?, ?, ?, ?, 'Not Paid')";
                PreparedStatement ps2 = db.connection.prepareStatement(q2);

                ps2.setString(1, sMeterNo);
                ps2.setString(2, sMonth);
                ps2.setString(3, sUnit);
                ps2.setInt(4, totalBill);

                ps2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully!");
                setVisible(false);

            }catch(Exception E){
                E.printStackTrace();
            }
        }else{
            this.dispose();
        }
    }
    public static void main(String args[]){
        new CalculateBill();
    }
}

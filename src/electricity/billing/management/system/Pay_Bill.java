package electricity.billing.management.system;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pay_Bill extends JFrame implements ActionListener {
    Choice monthChoice;
    String meter;
    JButton pay, back;
    Pay_Bill(String meter){

        super("Pay Bill");

        this.meter = meter;

        setSize(700, 500);
        setLocation(340, 130);
        setResizable(false);
        setLayout(null);

        Border backLine = BorderFactory.createLineBorder(Color.GRAY);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(160, 10, 100, 40);
        add(heading);

        JLabel meterLabel = new JLabel("Meter No: ");
        meterLabel.setFont(new Font("Serif", Font.BOLD, 17));
        meterLabel.setBounds(50, 80, 100, 20);
        add(meterLabel);

        JLabel meterLabelText = new JLabel("");
        meterLabelText.setBorder(backLine);
        meterLabelText.setBounds(150, 80, 200, 20);
        add(meterLabelText);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        nameLabel.setBounds(50, 120, 100, 20);
        add(nameLabel);

        JLabel nameLabelText = new JLabel("");
        nameLabelText.setBorder(backLine);
        nameLabelText.setBounds(150, 120, 200, 20);
        add(nameLabelText);

        JLabel months = new JLabel("Month: ");
        months.setFont(new Font("Serif", Font.BOLD, 17));
        months.setBounds(50, 160, 100, 20);
        add(months);

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
        monthChoice.setBounds(150, 160, 200, 20);
        add(monthChoice);

        JLabel unitLabel = new JLabel("Unit: ");
        unitLabel.setFont(new Font("Serif", Font.BOLD, 17));
        unitLabel.setBounds(50, 200, 100, 20);
        add(unitLabel);

        JLabel unitLabelText = new JLabel("");
        unitLabelText.setBorder(backLine);
        unitLabelText.setBounds(150, 200, 200, 20);
        add(unitLabelText);

        JLabel totalLabel = new JLabel("Total Bill: ");
        totalLabel.setFont(new Font("Serif", Font.BOLD, 17));
        totalLabel.setBounds(50, 240, 100, 20);
        add(totalLabel);

        JLabel totalLabelText = new JLabel("");
        totalLabelText.setBorder(backLine);
        totalLabelText.setBounds(150, 240, 200, 20);
        add(totalLabelText);

        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setFont(new Font("Serif", Font.BOLD, 17));
        statusLabel.setBounds(50, 280, 100, 20);
        add(statusLabel);

        JLabel statusLabelText = new JLabel("");
        statusLabelText.setBorder(backLine);
        statusLabelText.setBounds(150, 280, 200, 20);
        add(statusLabelText);

        try{
            Db db = new Db();
            String q = "select * from new_customer where meterNo = ?";
            PreparedStatement ps = db.connection.prepareStatement(q);
            ps.setString(1, meter.toString());

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                meterLabelText.setText(meter);
                nameLabelText.setText(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        monthChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Db db = new Db();
                    String q2 = "select * from bill where meterNo = ? and month = ?";
                    PreparedStatement ps2 = db.connection.prepareStatement(q2);
                    ps2.setString(1, meter.toString());
                    ps2.setString(2, monthChoice.getSelectedItem().toString());

                    ResultSet resultSet = ps2.executeQuery();
                    while(resultSet.next()){
                        unitLabelText.setText(resultSet.getString("unit"));
                        totalLabelText.setText(resultSet.getString("total_bill"));
                        statusLabelText.setText(resultSet.getString("status"));
                    }
                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        back = new JButton("back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Serif", Font.BOLD, 16));
        back.setBounds(80, 370, 100, 25);
        back.addActionListener(this);
        add(back);

        pay = new JButton("Pay");
        pay.setBackground(Color.GREEN);
        pay.setForeground(Color.WHITE);
        pay.setFont(new Font("Serif", Font.BOLD, 16));
        pay.setBounds(200, 370, 100, 25);
        pay.addActionListener(this);
        add(pay);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/wall.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(310, 550, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(355, 0, 350, 500);
        add(imageLabel);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == pay){
            try{
                Db db = new Db();
                String q3 = "update bill set status = 'Paid' where meterNo = ? and month = ?";
                PreparedStatement ps3 = db.connection.prepareStatement(q3);
                ps3.setString(1, meter.toString());
                ps3.setString(2, monthChoice.getSelectedItem().toString());
                ps3.executeUpdate();

                setVisible(false);
                new Payment_Bill(meter);
            }catch(Exception E){
                E.printStackTrace();
            }
        }else{
            this.dispose();
        }
    }
    public static void main(String args[]){
        new Pay_Bill("");
    }
}

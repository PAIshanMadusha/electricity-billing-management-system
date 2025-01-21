package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MeterInformation extends JFrame implements ActionListener{
    Choice meterLocChoice, meterTypeChoice, phaseCodeChoice, billTypeChoice;
    JButton submit;
    String meterNum;
    MeterInformation(String meterNum){
        super("Meter Information Form");

        this.meterNum = meterNum;

        Border backLine = BorderFactory.createLineBorder(Color.gray);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 255));
        add(panel);

        JLabel heading = new JLabel("METER INFORMATION");
        heading.setFont(new Font("serif", Font.BOLD, 20));
        heading.setBounds(65, 10, 250, 25);;
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number: ");
        meterNumber.setFont(new Font("Serif", Font.BOLD, 14));
        meterNumber.setBounds(20, 80, 120, 20);
        panel.add(meterNumber);

        JLabel meterNumberText = new JLabel(meterNum);
        meterNumberText.setBorder(backLine);
        meterNumberText.setFont(new Font("Serif", Font.BOLD, 14));
        meterNumberText.setBounds(150, 80, 160, 20);
        panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Location: ");
        meterLoc.setFont(new Font("serif", Font.BOLD, 14));
        meterLoc.setBounds(20, 120, 120, 20);
        panel.add(meterLoc);

        meterLocChoice = new Choice();
        meterLocChoice.add("Outside");
        meterLocChoice.add("Inside");
        meterLocChoice.setBounds(150, 120, 160, 20);
        panel.add(meterLocChoice);

        JLabel meterType = new JLabel("Meter Type: ");
        meterType.setFont(new Font("serif", Font.BOLD, 14));
        meterType.setBounds(20, 160, 120, 20);
        panel.add(meterType);

        meterTypeChoice = new Choice();
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        meterTypeChoice.setBounds(150, 160, 160, 20);
        panel.add(meterTypeChoice);

        JLabel phaseCode = new JLabel("Phase Code: ");
        phaseCode.setFont(new Font("serif", Font.BOLD, 14));
        phaseCode.setBounds(20, 200, 120, 20);
        panel.add(phaseCode);

        phaseCodeChoice = new Choice();
        phaseCodeChoice.add("001");
        phaseCodeChoice.add("002");
        phaseCodeChoice.add("003");
        phaseCodeChoice.add("004");
        phaseCodeChoice.add("005");
        phaseCodeChoice.add("006");
        phaseCodeChoice.add("007");
        phaseCodeChoice.add("008");
        phaseCodeChoice.add("009");
        phaseCodeChoice.add("010");
        phaseCodeChoice.setBounds(150, 200, 160, 20);
        panel.add(phaseCodeChoice);

        JLabel billType = new JLabel("Bill Type: ");
        billType.setFont(new Font("serif", Font.BOLD, 14));
        billType.setBounds(20, 240, 120, 20);
        panel.add(billType);

        billTypeChoice = new Choice();
        billTypeChoice.add("Normal");
        billTypeChoice.add("Industrial");
        billTypeChoice.setBounds(150, 240, 160, 20);
        panel.add(billTypeChoice);

        JLabel day = new JLabel("30 Days Billing Time...");
        day.setFont(new Font("serif", Font.BOLD, 14));
        day.setBounds(20, 280, 200, 20);
        panel.add(day);

        JLabel note = new JLabel("NOTE");
        note.setFont(new Font("serif", Font.BOLD, 18));
        note.setForeground(Color.DARK_GRAY);
        note.setBounds(150, 330, 200, 20);
        panel.add(note);

        JLabel note1 = new JLabel("By default, a bill is calculated for 30 days only.");
        note1.setFont(new Font("serif", Font.BOLD, 14));
        note1.setBounds(35, 355, 290, 20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBackground(Color.GREEN);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("serif", Font.BOLD, 12));
        submit.setBounds(130, 405, 100, 25);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/newCustomerWall.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(330, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel, "East");

        setSize(700, 500);
        setLocation(340, 130);
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit){

            String sMeter = meterNum;
            String sMeterLocation = meterLocChoice.getSelectedItem();
            String sMeterType = meterTypeChoice.getSelectedItem();
            String sPhaseCode = phaseCodeChoice.getSelectedItem();
            String sBillType = billTypeChoice.getSelectedItem();
            String sDay = "30";

            int response = JOptionPane.showConfirmDialog(null, "Are you sure these Detail are Right?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                try{
                    Db db = new Db();
                    String q = "insert into meter_info(meter_number, meter_location, meter_type, phase_code, bill_type, days) values(?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = db.connection.prepareStatement(q);

                    ps.setString(1, sMeter);
                    ps.setString(2, sMeterLocation);
                    ps.setString(3, sMeterType);
                    ps.setString(4, sPhaseCode);
                    ps.setString(5, sBillType);
                    ps.setString(6, sDay);

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Details Added Successfully!");
                    setVisible(false);

                }catch(Exception E){
                    E.printStackTrace();
                }
            }else{
                System.out.println("No");
            }
        }
    }
    public static void main(String args[]){
        new MeterInformation("");
    }
}

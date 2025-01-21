package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Generate_Bill extends JFrame implements ActionListener{
    String meter;
    Choice monthChoice;
    JTextArea area;
    JButton bill;
    Generate_Bill(String meter){

        super("Generate Bill");
        this.meter = meter;

        setSize(700, 500);
        setLocation(340, 130);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate Bill");
        heading.setFont(new Font("Serif", Font.BOLD, 17));

        JLabel meterNo = new JLabel(meter);

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

        area = new JTextArea(50, 15);
        area.setText("\n-----------------------------------------------------Click on the Generate Bill------------------------------------------------");
        area.setFont(new Font("Serif", Font.ITALIC, 16));

        JScrollPane scrollPane = new JScrollPane(area);

        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

        add(scrollPane);

        setVisible(true);

        panel.add(heading);
        panel.add(meterNo);
        panel.add(monthChoice);
        add(panel, "North");
        add(bill, "South");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == bill){
            try{
                Db db = new Db();
                String sMonth = monthChoice.getSelectedItem();
                area.setText("\n Power Limited \n Electricity Bill for Month of "+sMonth+", 2025\n");

                ResultSet resultSet = db.statement.executeQuery("select * from new_customer where meterNo = '"+meter+"'");
                if (resultSet.next()){
                    area.append("\n    Customer Name        : "+resultSet.getString("name"));
                    area.append("\n    Customer Meter Number: "+resultSet.getString("meterNo"));
                    area.append("\n    Customer Address     : "+resultSet.getString("address"));
                    area.append("\n    Customer City        : "+resultSet.getString("city"));
                    area.append("\n    Customer State       : "+resultSet.getString("state"));
                    area.append("\n    Customer Email       : "+resultSet.getString("email"));
                    area.append("\n    Customer Phone Number       : "+resultSet.getString("phoneNo"));
                }
                resultSet = db.statement.executeQuery("select * from meter_info where meter_number ='"+meter+"'");
                if (resultSet.next()){
                    area.append("\n    Customer Meter Location        : "+resultSet.getString("meter_location"));
                    area.append("\n    Customer Meter Type: "+resultSet.getString("meter_type"));
                    area.append("\n    Customer Phase Code   : "+resultSet.getString("phase_code"));
                    area.append("\n    Customer Bill Type        : "+resultSet.getString("bill_type"));
                    area.append("\n    Customer Days      : "+resultSet.getString("Days"));
                }
                resultSet = db.statement.executeQuery("select * from tax");
                if (resultSet.next()){
                    area.append("\n    Cost Per Unit        : "+resultSet.getString("cost_per_unit"));
                    area.append("\n   Meter Rent: "+resultSet.getString("meter_rent"));
                    area.append("\n   Service Charge   : "+resultSet.getString("service_charge"));
                    area.append("\n   Service Tax        : "+resultSet.getString("service_tax"));
                    area.append("\n   Vat Tax     : "+resultSet.getString("vat_tax"));
                    area.append("\n   Fixed Tax     : "+resultSet.getString("fixed_tax"));
                }
                resultSet = db.statement.executeQuery("select * from bill where meterNo = '"+meter+"' and month = '"+monthChoice.getSelectedItem()+"'");
                if (resultSet.next()) {
                    area.append("\n    Current Month       : " + resultSet.getString("month"));
                    area.append("\n   Units Consumed: " + resultSet.getString("unit"));
                    area.append("\n   Total Charges   : " + resultSet.getString("total_bill"));
                    area.append("\n Total Payable: "+resultSet.getString("total_bill"));
                }
            }catch(Exception E){
                E.printStackTrace();
            }
        }
    }
    public static void main(String args[]){
        new Generate_Bill("");
    }
}

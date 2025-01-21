package electricity.billing.management.system;
import javax.swing.*;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Deposit_Details extends JFrame implements ActionListener {
    Choice searchMeterChoice, searchMonthChoice;
    JTable table;
    JButton search, print, close;
    Deposit_Details(){
        super("Deposit Details");

        setSize(700, 500);
        setLocation(340, 130);
        setResizable(false);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search by Meter Number: ");
        searchMeter.setFont(new Font("Serif", Font.BOLD, 15));
        searchMeter.setBounds(20, 20, 180, 20);
        add(searchMeter);

        searchMeterChoice = new Choice();
        searchMeterChoice.setBounds(200, 20, 100, 20);
        add(searchMeterChoice);

        try{
            Db db = new Db();
            String q = "select * from bill";
            PreparedStatement ps = db.connection.prepareStatement(q);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                searchMeterChoice.add(resultSet.getString("meterNo"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel searchMonth = new JLabel("Search By Selected Month: ");
        searchMonth.setFont(new Font("Serif", Font.BOLD, 15));
        searchMonth.setBounds(370, 20, 190, 20);
        add(searchMonth);

        searchMonthChoice = new Choice();
        searchMonthChoice.add("January");
        searchMonthChoice.add("February");
        searchMonthChoice.add("March");
        searchMonthChoice.add("April");
        searchMonthChoice.add("may");
        searchMonthChoice.add("June");
        searchMonthChoice.add("July");
        searchMonthChoice.add("August");
        searchMonthChoice.add("September");
        searchMonthChoice.add("October");
        searchMonthChoice.add("November");
        searchMonthChoice.add("December");
        searchMonthChoice.setBounds(565, 20, 100, 20);
        add(searchMonthChoice);

        table = new JTable();

        try {
            Db db = new Db();
            String q2 = "select * from bill";
            PreparedStatement ps2 = db.connection.prepareStatement(q2);
            ResultSet resultSet2 = ps2.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet2));

        }catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 700, 500);
        scrollPane.setBackground(new Color(56, 105, 126, 136));
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(Color.GREEN);
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Serif", Font.BOLD, 14));
        search.setBounds(20, 60, 80, 25);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.GREEN);
        print.setForeground(Color.WHITE);
        print.setFont(new Font("Serif", Font.BOLD, 14));
        print.setBounds(110, 60, 80, 25);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.BLACK);
        close.setForeground(Color.WHITE);
        close.setFont(new Font("Serif", Font.BOLD, 14));
        close.setBounds(585, 60, 80, 25);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            try{
                Db db = new Db();
                String q = "select * from bill where meterNo = ? and month = ?";
                PreparedStatement ps = db.connection.prepareStatement(q);

                ps.setString(1, searchMeterChoice.getSelectedItem().toString());
                ps.setString(2, searchMonthChoice.getSelectedItem().toString());

                ResultSet resultSet = ps.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource() == print){
            try{
                table.print();
            }catch(Exception E){
                E.printStackTrace();
            }
        }else{
            this.dispose();
        }
    }
    public static void main(String args[]){
        new Deposit_Details();
    }
}

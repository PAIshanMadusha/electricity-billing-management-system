package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerDetails extends JFrame implements ActionListener{
    Choice searchMeterChoice, searchNameChoice;
    JTable table;
    JButton search, print, close;
    CustomerDetails(){
        super("Customer Details");
        setSize(700, 500);
        setLocation(340, 130);
        setResizable(false);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search by Meter Number:");
        searchMeter.setFont(new Font("serif", Font.BOLD, 15));
        searchMeter.setBounds(20, 20, 180, 20);
        add(searchMeter);

        searchMeterChoice = new Choice();
        searchMeterChoice.setBounds(200, 20, 100, 20);
        add(searchMeterChoice);

        JLabel searchName = new JLabel("Search by Customer Name:");
        searchName.setFont(new Font("serif", Font.BOLD, 15));
        searchName.setBounds(370, 20, 180, 20);
        add(searchName);

        searchNameChoice = new Choice();
        searchNameChoice.setBounds(565, 20, 100, 20);
        add(searchNameChoice);

        try{
            Db db = new Db();
            String q = "select * from new_customer";
            PreparedStatement ps = db.connection.prepareStatement(q);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                searchMeterChoice.add(resultSet.getString("meterNo"));
                searchNameChoice.add(resultSet.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        try{
            Db db = new Db();
            String q = "select * from new_customer";
            PreparedStatement ps = db.connection.prepareStatement(q);
            ResultSet resultset = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultset));
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
        close.setBounds(590, 60, 80, 25);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == search){

            try {
                Db db = new Db();
                String q = "select * from new_customer where meterNo = ? and name = ?";
                PreparedStatement ps = db.connection.prepareStatement(q);
                ps.setString(1, searchMeterChoice.getSelectedItem().toString());
                ps.setString(2, searchNameChoice.getSelectedItem().toString());

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
    public static void main(String[] args) {
        new CustomerDetails();
    }
}

package electricity.billing.management.system;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Bill_Details extends JFrame {
    String meter;
    Bill_Details(String meter){

        super("Bill Details");

        this.meter = meter;

        setSize(700, 500);
        setLocation(340, 130);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);

        JTable table = new JTable();
        try{
            Db db = new Db();
            String q = "select * from bill where meterNo = ?";
            PreparedStatement ps = db.connection.prepareStatement(q);
            ps.setString(1, meter.toString());

            ResultSet resultSet = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch(Exception e){
            e.printStackTrace();;
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 700, 500);
        add(scrollPane);
    }
    public static void main(String[] args) {
        new Bill_Details("");
    }
}

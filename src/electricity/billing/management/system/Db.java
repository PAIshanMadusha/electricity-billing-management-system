package electricity.billing.management.system;
import java.sql.*;
public class Db {
    Connection connection;
    Statement statement;
    Db(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricity_bill_system", "root", "");
            statement = connection.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

package gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class JDBCdemo {

    static final String DB_URL = "jdbc:mysql://localhost:3306/vrd";
    static final String USER = "root";
    static final String PASS = "$)NlvDb7`&68";
    static List<String> firstCol = new ArrayList<String>();
    static List<String> secondCol = new ArrayList<String>();
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from canteen");
            
            while(resultSet.next()){
                firstCol.add(resultSet.getString(2));
                secondCol.add(resultSet.getString(3));
            }   
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
            }
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

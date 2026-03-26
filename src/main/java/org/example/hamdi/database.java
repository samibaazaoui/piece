package org.example.hamdi;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class database {
    public static Connection connectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hamdi", "root","");
            return connection;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}

package com.onepiecejoker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DriverManagerDemo {
    
    public static void main(String[] args) throws NamingException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 使用DriverManager连接Mysql
            // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123");
            // 使用连接池
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MySQLDB");
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from user");
            int count = 0;
            while (rs.next()) {
                System.out.println("id: " + rs.getString("id"));
                count++;
            }
            System.out.println("rows: " + count);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {}
                stmt = null;
            }
        }
    }
}

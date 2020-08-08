package com.dxtd.dbtest.cptest;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class CPTest2 {

    private static Connection getConnection() {
        Connection connection = null;

        Properties properties = new Properties();
        InputStream inputStream = CPTest2.class.getResourceAsStream(("/druid.properties"));
        try {
            properties.load(inputStream);
            try {
                DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
                connection = dataSource.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static int insertData(String name, String cardNo) {

        Connection conn = getConnection();
        int count = -1;
        if (conn == null) {
            System.out.println("failed to open connection to database");
            return count;
        }

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into ACCOUNT (NAME , CARDNO) values (?, ?)");
            ps.setString(1, name);
            ps.setString(2, cardNo);
            count = ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return count;
    }


    public static void main(String[] args) {
        int count = insertData("user4", "0011");

        if (count > 0)
            System.out.println("inserted " + count + " record(s) successfully.");
        else
            System.out.println("failed to insert record");
    }
}

package com.dxtd.dbtest.jdbctest;

/**
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 */
public class JDBCTest {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://47.93.212.169:3306/BANK?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String USER = "xinping";
    private  static final String PASSWORDd = "123456";

    public static int insertData(String name, String cardNo) throws Exception{
        Class.forName(DB_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORDd);
        PreparedStatement ps = conn.prepareStatement("insert into ACCOUNT (NAME , CARDNO) values (?, ?)");
        ps.setString(1, name);
        ps.setString(2, cardNo);
        int count = ps.executeUpdate();
        conn.close();
        return count;
    }

    public static void main(String[] args) throws Exception {
        int count = insertData("user1", "001");
        if (count > 0)
            System.out.println("inserted " + count + " record(s) successfully.");
        else
            System.out.println("failed to insert record");
    }

    // select * from ACCOUNT;
}

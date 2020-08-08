package com.dxtd.dbtest.cptest;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CPTest1 {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://47.93.212.169:3306/BANK?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String USER = "xinping";
    private  static final String PASSWORDd = "123456";

    private static Connection getConnection() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORDd);

        //初始连接数
        dataSource.setInitialSize(20);
        //最大连接数
        dataSource.setMaxActive(100);
        //最小闲置数
        dataSource.setMinIdle(10);
        //获取连接的最大等待时间，单位毫秒
        dataSource.setMaxWait(2000);
        //缓存PreparedStatement
        dataSource.setPoolPreparedStatements(true);
        //缓存PreparedStatement的最大数量
        dataSource.setMaxOpenPreparedStatements(100);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
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
        int count = insertData("user3", "0000");

        if (count > 0)
            System.out.println("inserted " + count + " record(s) successfully.");
        else
            System.out.println("failed to insert record");
    }
}

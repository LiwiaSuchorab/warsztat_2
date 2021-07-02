package pl.coderslab.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBUtil {

    //polecenia zrobione w konsoli
    private static final String QUERY1 = "create database workshop2\n" +
            "CHARACTER SET utf8mb4\n" +
            "COLLATE utf8mb4_unicode_ci;";

    private static final String QUERY2 = "create table users (\n" +
            "    id int(11) primary key auto_increment,\n" +
            "    email varchar(255) unique NOT NULL ,\n" +
            "    username varchar(255) NOT NULL ,\n" +
            "    password varchar(60) NOT NULL\n" +
            ");";


    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}

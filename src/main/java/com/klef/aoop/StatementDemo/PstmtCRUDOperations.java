package com.klef.aoop.StatementDemo;

import java.sql.*;
import java.util.Scanner;

public class PstmtCRUDOperations {
    public static void main(String[] args) {
    	PstmtCRUDOperations operations = new PstmtCRUDOperations();
         operations.insertFaculty();
        //viewAllFaculty();
    }

    public void insertFaculty() {
        try {
            Connection con = null;
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver Loaded Successfully");

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbccrudoperations", "postgres", "Saketh@123");

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter Name: ");
            String fname = sc.nextLine();
            System.out.println("Enter Faculty Location:");
            String flocation = sc.nextLine();
            System.out.println("Enter Faculty ID: ");
            int fid = sc.nextInt();
            sc.nextLine(); // Consume newline character after nextInt
            System.out.println("Enter Faculty Course:");
            String fcourse = sc.nextLine();
            System.out.println("Enter Faculty Age:");
            int fage = sc.nextInt();
            sc.nextLine(); // Consume newline character after nextInt
            System.out.println("Enter Faculty Email: ");
            String femail = sc.next();
            System.out.println("Enter Faculty Contact:");
            String fcon = sc.next();

            PreparedStatement pstmt = con.prepareStatement("insert into faculty values(?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, fid);
            pstmt.setString(2, fname);
            pstmt.setString(3, flocation);
            pstmt.setString(4, fcourse);
            pstmt.setInt(5, fage);
            pstmt.setString(6, femail);
            pstmt.setString(7, fcon);

            int n = pstmt.executeUpdate();
            if (n > 0) {
                System.out.println(n + " Faculty Record(s) Inserted Successfully");
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewAllFaculty() {
        try {
            Connection con = null;
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver Loaded Successfully");

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbccrudoperations", "postgres", "Saketh@123");
            System.out.println("Connection Established Successfully");

            PreparedStatement pstmt = con.prepareStatement("select * from faculty");
            ResultSet rs = pstmt.executeQuery();
            int i = 1;

            System.out.println("-------------Faculty Records--------------------");
            while (rs.next()) {
                System.out.println("Faculty Record: " + i);
                System.out.println("Faculty ID: " + rs.getInt(1));
                System.out.println("Faculty Name: " + rs.getString(2));
                System.out.println("Faculty Location: " + rs.getString(3));
                System.out.println("Faculty Course: " + rs.getString(4));
                System.out.println("Faculty Age: " + rs.getInt(5));
                System.out.println("Faculty Email: " + rs.getString(6));
                System.out.println("Faculty Contact: " + rs.getString(7));
                i++;
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

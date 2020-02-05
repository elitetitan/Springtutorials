package com.bfm.training.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleJDBC {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
        displayEmployeeData();
//        updateSalaryCommission(7839, 4000, 1500);
//        ConnectionUtil.getConnection().commit();
//        displayEmployeeData();
//        runWrongQuery(100, 4000, 2000);
//        displayEmployeeData();

    }//end main

    public static void displayEmployeeData() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 3: Open a connection
            conn = ConnectionUtil.getConnection();
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM EMP  ";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("empno");
                int sal = rs.getInt("sal");
                int comm = rs.getInt("comm");
                String name = rs.getString("ename");
                String mgrId
                        = rs.getString("mgr");

                //Display values
                System.out.print("ID: " + id+ "  Salary: "+sal+ "  Commission: "+comm);
                System.out.print(", Name: " + name);
                System.out.println(", MgrId: " + mgrId);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    public static void updateSalaryCommission(int empno, int sal, int comm) {
        String sql = "update EMP set sal=? , comm=? where empno=?";
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            preparedStatement.setInt(1, sal);
            preparedStatement.setInt(2, comm);
            preparedStatement.setInt(3, empno);
//            preparedStatement.addBatch();
//            int rowsAffected = preparedStatement.executeBatch();
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void runWrongQuery(int empno, int sal, int comm) {
        String sql = "update EMP set sal=? , comm=? where empno=?";
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            preparedStatement.setInt(1, sal);
            preparedStatement.setInt(2, comm);
//            preparedStatement.addBatch();
//            int rowsAffected = preparedStatement.executeBatch();
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
//            try {
//               connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
        }
    }
}



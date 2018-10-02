package utils;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCConnector {


    /** The name of the MySQL account to use (or empty for anonymous) */
    private final String userName = "root";

    /** The password for the MySQL account (or empty for anonymous) */
    private final String password = "root";

    /** The name of the computer running MySQL */
    private final String serverName = "localhost";

    /** The port of the MySQL server (default is 3306) */
    private final int portNumber = 3306;

    /** The name of the database we are testing with (this default is installed with MySQL) */
    private final String dbName = "assignmentDB";


    public Connection getConnection() throws SQLException {
        Connection conn;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);

        return conn;
    }

    public void addRecord(String ssn, String dob, String name, String address, int salary, String gender) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String sqlInsert = "INSERT INTO Employee VALUES ('" + ssn + "','" + dob + "','" + name + "','" + address + "'," + salary + ",'" + gender + "')";
        stmt.executeUpdate(sqlInsert);
        stmt.close();

    }

    public void deleteRecord(){
        // todo delete record
    }

    public void updateRecord(){
        // todo update record
    }

    public List getRecords() throws SQLException{
        Statement stmt = getConnection().createStatement();
        String sqlGet = "SELECT * FROM Employee";
        stmt.executeQuery(sqlGet);
        ResultSet rs = stmt.getResultSet();
        List<Employee> employees = new ArrayList<Employee>();
        while(rs.next()){
            String ssn = rs.getString("ssn");
            String dob = rs.getString("dob");
            String name = rs.getString("name");
            String address = rs.getString("address");
            int salary = rs.getInt("salary");
            String gender = rs.getString("gender");

            Employee employee = new Employee(ssn, dob, name, address, salary, gender);
            employees.add(employee);
        }
        return employees;
    }
}

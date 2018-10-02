import java.sql.*;
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

    public void getRecords() throws SQLException{
        Statement stmt = getConnection().createStatement();
        String sqlGet = "SELECT * FROM Employee";
        stmt.executeQuery(sqlGet);
        ResultSet rs = stmt.getResultSet();
        while (rs.next()){
            String ssnVal = rs.getString("ssn");
            String dobVal = rs.getString("dob");
            String nameVal = rs.getString("name");
            String addressVal = rs.getString("address");
            String salaryVal = rs.getString("salary");
            String genderVal = rs.getString("gender");
            System.out.println(ssnVal + " " + dobVal + " " + nameVal + " " + addressVal + " " + salaryVal + " " + genderVal);
        }
        rs.close();
        stmt.close();

    }
}

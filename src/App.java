import utils.JDBCConnector;
import view.MainFrame;

import javax.swing.*;
import java.sql.SQLException;

/**
 * @author ciaranroche
 */
public class App {


    /**
     * Creates a JDBC connection, creates ResultSet of Records
     * Creates a new MainFrame
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        JDBCConnector jdbc = new JDBCConnector();
        try {
            jdbc.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc.getRecords();
        MainFrame app = new MainFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.pack();
        app.setVisible(true);
    }

}



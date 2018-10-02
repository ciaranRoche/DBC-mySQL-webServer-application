import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 *
 * @author ciaranroche
 */
public class EmployeeSql extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("JDBC Assignment");
        stage.setScene(scene);
        stage.show();
    }

    /**
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
        launch(args);
    }

}

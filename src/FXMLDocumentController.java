import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author ciaranroche
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField ssnTxt;

    @FXML
    private TextField dobTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField salaryTxt;

    @FXML
    private TextField genderTxt;

    JDBCConnector conn = new JDBCConnector();

    @FXML
    private void handleAddAction(ActionEvent event) {
        try {
            conn.addRecord(ssnTxt.getText(), dobTxt.getText(), nameTxt.getText(), addressTxt.getText(), Integer.parseInt(salaryTxt.getText()), genderTxt.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



}

import java.net.URL;
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
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!" + ssnTxt.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

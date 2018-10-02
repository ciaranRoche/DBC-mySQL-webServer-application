package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import utils.JDBCConnector;

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

    ResultSet set;

    @FXML
    private void handleAddAction(ActionEvent event) throws SQLException {
        set.moveToInsertRow();
        set.updateString("ssn", ssnTxt.getText());
        set.updateString("dob", dobTxt.getText());
        set.updateString("name", nameTxt.getText());
        set.updateString("address", addressTxt.getText());
        set.updateInt("salary", Integer.parseInt(salaryTxt.getText()));
        set.updateString("gender", genderTxt.getText());
        set.insertRow();
    }

    @FXML
    private void handlePreviousAction(ActionEvent event) throws SQLException {
        if(set.previous()){
            setText(set.getString(
                    "ssn"),
                    set.getString("dob"),
                    set.getString("name"),
                    set.getString("address"),
                    set.getInt("salary"),
                    set.getString("gender"));
        }
    }

    @FXML
    private void handleNextAction(ActionEvent event) throws SQLException {
        if(set.next()){
            setText(set.getString(
                    "ssn"),
                    set.getString("dob"),
                    set.getString("name"),
                    set.getString("address"),
                    set.getInt("salary"),
                    set.getString("gender"));
        }
    }

    @FXML
    private void handleClearAction(ActionEvent event) throws SQLException {
        set.isBeforeFirst();
        ssnTxt.clear();
        dobTxt.clear();
        nameTxt.clear();
        addressTxt.clear();
        salaryTxt.clear();
        genderTxt.clear();
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) throws SQLException {
        if(Objects.equals(ssnTxt.getText().toLowerCase(), set.getString("ssn").toLowerCase())) {
            set.updateString("ssn", ssnTxt.getText());
            set.updateString("dob", dobTxt.getText());
            set.updateString("name", nameTxt.getText());
            set.updateString("address", addressTxt.getText());
            set.updateInt("salary", Integer.parseInt(salaryTxt.getText()));
            set.updateString("gender", genderTxt.getText());
            set.updateRow();
        }
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) throws SQLException {
        set.deleteRow();
        getRecords();
    }

    @FXML
    private void setText(String ssn, String dob, String name, String address, int salary, String gender){
        ssnTxt.setText(ssn);
        dobTxt.setText(dob);
        nameTxt.setText(name);
        addressTxt.setText(address);
        salaryTxt.setText(String.valueOf(salary));
        genderTxt.setText(gender);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            set = conn.getRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getRecords();
    }

    public void getRecords(){
        try {
            if(set.next()){
                setText(set.getString(
                        "ssn"),
                        set.getString("dob"),
                        set.getString("name"),
                        set.getString("address"),
                        set.getInt("salary"),
                        set.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

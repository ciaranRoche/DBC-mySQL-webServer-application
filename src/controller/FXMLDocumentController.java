package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Employee;
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

    private int record = 0;

    private List<Employee> set;

    JDBCConnector conn = new JDBCConnector();

    @FXML
    private void handleAddAction(ActionEvent event) {
        try {
            conn.addRecord(ssnTxt.getText(), dobTxt.getText(), nameTxt.getText(), addressTxt.getText(), Integer.parseInt(salaryTxt.getText()), genderTxt.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePreviousAction(ActionEvent event){
        if (record != 0){
            --record;
            Employee employee = set.get(record);
            setText(employee.getSsn(), employee.getDob(), employee.getName(), employee.getAddress(), employee.getSalary(), employee.getGender());
        }
    }

    @FXML
    private void handleNextAction(ActionEvent event){
        if (record < (set.size() - 1))
            ++record;
            Employee employee = set.get(record);
            setText(employee.getSsn(), employee.getDob(), employee.getName(), employee.getAddress(), employee.getSalary(), employee.getGender());
    }

    @FXML
    private void handleClearAction(ActionEvent event){
        ssnTxt.clear();
        dobTxt.clear();
        nameTxt.clear();
        addressTxt.clear();
        salaryTxt.clear();
        genderTxt.clear();
    }

    @FXML
    private void handleUpdateAction(ActionEvent event){

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
            if (set.size() != 0){
                Employee employee = set.get(0);
                setText(employee.getSsn(), employee.getDob(), employee.getName(), employee.getAddress(), employee.getSalary(), employee.getGender());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

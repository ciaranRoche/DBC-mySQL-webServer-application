package view;

import utils.JDBCConnector;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/*
MainFrame class, generates and controls Java Swing Main Frame
 */
public class MainFrame extends JFrame implements ActionListener {

    private JFrame frame;
    private JButton addBtn;
    private JTextField ssnTxt;
    private JTextField dobTxt;
    private JTextField nameTxt;
    private JTextField addressTxt;
    private JTextField salaryTxt;
    private JTextField genderTxt;
    private JButton deleteBtn;
    private JButton updateBtn;
    private JButton previousBtn;
    private JButton nextBtn;
    private JButton clearBtn;
    private JLabel ssn;
    private JLabel dob;
    private JLabel name;
    private JLabel address;
    private JLabel salary;
    private JLabel gender;
    private JLabel employeeDetails;

    private JDBCConnector conn = new JDBCConnector();

    private ResultSet set;

    /*
    Constructs MainFrame components
     */
    public MainFrame() throws SQLException {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(100, 100, 500, 368);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        addBtn = new JButton();
        addBtn.setBounds(93, 273, 64, 25);
        addBtn.setText("Add");
        frame.getContentPane().add(addBtn);

        ssnTxt = new JTextField();
        ssnTxt.setBounds(93, 55, 315, 27);
        frame.getContentPane().add(ssnTxt);

        ssn = new JLabel("SSn");
        ssn.setBounds(35, 60, 46, 20);
        frame.getContentPane().add(ssn);

        dobTxt = new JTextField();
        dobTxt.setBounds(93, 93, 315, 27);
        frame.getContentPane().add(dobTxt);

        dob = new JLabel("DOB");
        dob.setBounds(35, 98, 46, 20);
        frame.getContentPane().add(dob);

        nameTxt = new JTextField();
        nameTxt.setBounds(93, 130, 315, 27);
        frame.getContentPane().add(nameTxt);

        name = new JLabel("Name");
        name.setBounds(35, 135, 46, 20);
        frame.getContentPane().add(name);

        addressTxt = new JTextField();
        addressTxt.setBounds(93, 165, 315, 27);
        frame.getContentPane().add(addressTxt);

        address = new JLabel("Address");
        address.setBounds(35, 170, 55, 20);
        frame.getContentPane().add(address);

        salaryTxt = new JTextField();
        salaryTxt.setBounds(93, 201, 315, 27);
        frame.getContentPane().add(salaryTxt);

        salary = new JLabel("Salary");
        salary.setBounds(35, 206, 46, 20);
        frame.getContentPane().add(salary);

        genderTxt = new JTextField();
        genderTxt.setBounds(93, 237, 315, 27);
        frame.getContentPane().add(genderTxt);

        gender = new JLabel("Gender");
        gender.setBounds(35, 242, 46, 20);
        frame.getContentPane().add(gender);

        employeeDetails = new JLabel("Employee Details");
        employeeDetails.setBounds(198, 24, 315,30);
        frame.getContentPane().add(employeeDetails);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(144, 273, 80, 25);
        frame.getContentPane().add(deleteBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(415, 273, 80, 25);
        frame.getContentPane().add(clearBtn);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(211, 273, 80, 25);
        frame.getContentPane().add(updateBtn);

        previousBtn = new JButton("Previous");
        previousBtn.setBounds(415, 55, 80, 25);
        frame.getContentPane().add(previousBtn);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(415, 80, 80, 25);
        frame.getContentPane().add(nextBtn);

        addBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        previousBtn.addActionListener(this);
        nextBtn.addActionListener(this);

        // gets records and populates textfields
        getRecords();
    }

    /*
    Handles action, calling a handler based on action
     */
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Clear":
                handleClear();
                break;
            case "Next":
                try {
                    handleNext();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Previous":
                try {
                    handlePrevious();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Add":
                try {
                    handleAdd();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Delete":
                try {
                    handleDelete();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "Update":
                try {
                    handleUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
        }
    }


    /*
    Gets records from connection and sets textfields
     */
    private void getRecords() throws SQLException {
        set = conn.getRecords();
        if (set.next()) {
            setText(set.getString(
                    "ssn"),
                    set.getString("dob"),
                    set.getString("name"),
                    set.getString("address"),
                    set.getInt("salary"),
                    set.getString("gender"));
        }
    }

    /*
    Gets next record from result set and sets textfields
     */
    private void handleNext() throws SQLException {
        if(set.next()) setText(set.getString(
                "ssn"),
                set.getString("dob"),
                set.getString("name"),
                set.getString("address"),
                set.getInt("salary"),
                set.getString("gender"));
    }

    /*
    Checks if primary keys are equal and then updates record
     */
    private void handleUpdate() throws SQLException {
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

    /*
    gets previous record from result set
     */
    private void handlePrevious() throws SQLException {
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

    /*
    Moves to insert row in result set and inserts new record
     */
    private void handleAdd() throws SQLException {
        set.moveToInsertRow();
        set.updateString("ssn", ssnTxt.getText());
        set.updateString("dob", dobTxt.getText());
        set.updateString("name", nameTxt.getText());
        set.updateString("address", addressTxt.getText());
        set.updateInt("salary", Integer.parseInt(salaryTxt.getText()));
        set.updateString("gender", genderTxt.getText());
        set.insertRow();
    }

    /*
    Deletes current row and recalls records from result set
     */
    private void handleDelete() throws SQLException {
        set.deleteRow();
        getRecords();
    }

    /*
    Moves to empty row in result set and clears text fields
     */
    private void handleClear(){
        try {
            set.isBeforeFirst();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        ssnTxt.setText("");
        dobTxt.setText("");
        nameTxt.setText("");
        addressTxt.setText("");
        salaryTxt.setText("");
        genderTxt.setText("");
    }

    /*
    Sets text fields
     */
    private void setText(String ssn, String dob, String name, String address, int salary, String gender){
        ssnTxt.setText(ssn);
        dobTxt.setText(dob);
        nameTxt.setText(name);
        addressTxt.setText(address);
        salaryTxt.setText(String.valueOf(salary));
        genderTxt.setText(gender);
    }


}
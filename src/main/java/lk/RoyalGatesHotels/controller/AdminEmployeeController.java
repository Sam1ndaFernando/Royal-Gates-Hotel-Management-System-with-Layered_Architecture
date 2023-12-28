package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.EmployeeBO;
import lk.RoyalGatesHotels.dto.HallDTO;
import lk.RoyalGatesHotels.model.EmployeeModel;
import lk.RoyalGatesHotels.dto.EmployeeDTO;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.RegExPattern;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminEmployeeController implements Initializable {
    public AnchorPane adminEmployeeContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtFullName;
    public JFXTextField txtNic;
    public JFXTextField txtMobile;
    public JFXTextField txtAddress;
    public JFXDatePicker datepickerDate;
    public JFXTextField txtJobRole;
    public JFXTextField txtEmail;
    EmployeeBO employeeBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminEmployeeContext).play();

        setEmployeeId();
        datepickerDate.setValue(LocalDate.now());

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

    }


    private void setEmployeeId() {

        try {
            /*String lastEmployeeId= EmployeeModel.getLastEmployeeId();
            if(lastEmployeeId==null){
                txtEmployeeId.setText("E0001");
            }else{
                String[] split=lastEmployeeId.split("[E]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newEmployeeId=String.format("E%04d", lastDigits);
                txtEmployeeId.setText(newEmployeeId);
            }*/

            String nextEmpId = employeeBO.getNextId();
            txtEmployeeId.setText(nextEmpId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,adminEmployeeContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,adminEmployeeContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,adminEmployeeContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,adminEmployeeContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,adminEmployeeContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,adminEmployeeContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,adminEmployeeContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,adminEmployeeContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,adminEmployeeContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,adminEmployeeContext);
    }

    public void btnAddUser(ActionEvent actionEvent) {

        boolean isNameMatched = RegExPattern.getNamePattern().matcher(txtFullName.getText()).matches();
        boolean isNicMatched = RegExPattern.getIdPattern().matcher(txtNic.getText()).matches();
        boolean isOldNicMatched = RegExPattern.getOldIDPattern().matcher(txtNic.getText()).matches();
        boolean isContactNoMatched = RegExPattern.getMobilePattern().matcher(txtMobile.getText()).matches();
        boolean isEmailMatched = RegExPattern.getEmailPattern().matcher(txtEmail.getText()).matches();

        if(isNameMatched){
            if(isNicMatched || isOldNicMatched){
                if(isContactNoMatched){
                    if(isEmailMatched){

                        String EmpId = txtEmployeeId.getText();
                        String Name = txtFullName.getText();
                        String Address = txtAddress.getText();
                        String date = String.valueOf(datepickerDate.getValue());
                        String Nic = txtNic.getText();
                        String Email = txtEmail.getText();
                        String Mobile = txtMobile.getText();
                        String JobRole = txtJobRole.getText();

                        /*EmployeeDTO employee = new EmployeeDTO(
                                txtEmployeeId.getText(),
                                txtFullName.getText(),
                                txtAddress.getText(),
                                String.valueOf(datepickerDate.getValue()),
                                txtNic.getText(),
                                txtEmail.getText(),
                                txtMobile.getText(),
                                txtJobRole.getText()
                        );*/

                        try {

                            /*boolean isAdd = EmployeeModel.addEmployee(employee);*/

                            boolean isAdd = employeeBO.add(new EmployeeDTO(EmpId, Name, Address, date, Nic, Email, Mobile, JobRole));

                            if(isAdd){
                                new Alert(Alert.AlertType.CONFIRMATION,"Employee Added Successfully!").show();
                                clearAll();
                                setEmployeeId();

                            }else{
                                new Alert(Alert.AlertType.ERROR,"Employee Not Added!").show();
                            }

                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                    }else{txtEmail.requestFocus();}
                }else{txtMobile.requestFocus();}
            }else{txtNic.requestFocus();}
        }else{txtFullName.requestFocus();}

    }

    private void clearAll() {
                txtEmployeeId.clear();
                txtFullName.clear();
                txtAddress.clear();
                txtNic.clear();
                txtEmail.clear();
                txtMobile.clear();
                txtJobRole.clear();

    }

    public void btnUpdateUser(ActionEvent actionEvent) {

        boolean isNameMatched = RegExPattern.getNamePattern().matcher(txtFullName.getText()).matches();
        boolean isNicMatched = RegExPattern.getIdPattern().matcher(txtNic.getText()).matches();
        boolean isOldNicMatched = RegExPattern.getOldIDPattern().matcher(txtNic.getText()).matches();
        boolean isContactNoMatched = RegExPattern.getMobilePattern().matcher(txtMobile.getText()).matches();
        boolean isEmailMatched = RegExPattern.getEmailPattern().matcher(txtEmail.getText()).matches();

        if(isNameMatched){
            if(isNicMatched || isOldNicMatched){
                if(isContactNoMatched){
                    if(isEmailMatched){

                        /*EmployeeDTO employee = new EmployeeDTO(
                                txtEmployeeId.getText(),
                                txtFullName.getText(),
                                txtAddress.getText(),
                                String.valueOf(datepickerDate.getValue()),
                                txtNic.getText(),
                                txtEmail.getText(),
                                txtMobile.getText(),
                                txtJobRole.getText()
                        );*/

                        String EmpId = txtEmployeeId.getText();
                        String Name = txtFullName.getText();
                        String Address = txtAddress.getText();
                        String date = String.valueOf(datepickerDate.getValue());
                        String Nic = txtNic.getText();
                        String Email = txtEmail.getText();
                        String Mobile = txtMobile.getText();
                        String JobRole = txtJobRole.getText();

                        try {
                            /*boolean isUpdate = EmployeeModel.updateEmployee(employee);*/

                            boolean isUpdate = employeeBO.update(new EmployeeDTO(EmpId, Name, Address, date, Nic, Email, Mobile, JobRole));

                            if(isUpdate){
                                new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Employee Not Updated!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                    }else{txtEmail.requestFocus();}
                }else{txtMobile.requestFocus();}
            }else{txtNic.requestFocus();}
        }else{txtFullName.requestFocus();}

    }


    public void btnCancel(ActionEvent actionEvent) {
       clearAll();
    }

    public void txtEmployeeIdOnAction(ActionEvent actionEvent) {
        try {
            String employeeId = txtEmployeeId.getText();

            try {
                EmployeeDTO employee = employeeBO.setFields(employeeId);

                if (employee != null) {
                    txtEmployeeId.setText(employee.getEmployeeId());
                    txtFullName.setText(employee.getName());
                    txtAddress.setText(employee.getAddress());
                    datepickerDate.setValue(LocalDate.parse(employee.getJoin_date()));
                    txtNic.setText(employee.getNic());
                    txtEmail.setText(employee.getEmail());
                    txtMobile.setText(employee.getMobile());
                    txtJobRole.setText(employee.getJobRole());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee Not Found!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Oops! Something went wrong :(").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Oops! Something unexpected went wrong :(").show();
        }
    }
}

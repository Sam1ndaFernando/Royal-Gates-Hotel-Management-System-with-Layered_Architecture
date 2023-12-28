package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.HallBO;
import lk.RoyalGatesHotels.dto.HallDTO;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.RegExPattern;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminBanquetHallsController implements Initializable {
    public AnchorPane adminHallContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtHallNumber;
    public JFXTextField txtHallType;
    public JFXTextField txtPrice;
    public JFXComboBox cmbAvailability;
    private final HallBO hallBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminHallContext).play();
        setAdminHall();
        generateNextId();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

    }


    private void generateNextId() {
        try {

            String nextId = hallBO.getNextId();
            txtHallNumber.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAdminHall() {
        try {
        /*    String lastAdminHallId = HallsModel.getLastAdminHallId();
            if (lastAdminHallId == null) {
                txtHallNumber.setText("H0001");
            } else {
                String[] split = lastAdminHallId.split("[H]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newAdminHallId = String.format("H%04d", lastDigits);
                txtHallNumber.setText(newAdminHallId);
            }*/

            String nextId = hallBO.getNextId();
            txtHallNumber.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD, adminHallContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM, adminHallContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN, adminHallContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER, adminHallContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE, adminHallContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE, adminHallContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS, adminHallContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT, adminHallContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS, adminHallContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT, adminHallContext);
    }

    public void btnAddHall(ActionEvent actionEvent) throws IOException {

        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if (isPriceMatched) {

//            HallDTO hall = new HallDTO(
//                    txtHallNumber.getText(),
//                    txtHallType.getText(),
//                    String.valueOf(cmbAvailability.getValue()),
//                    Double.parseDouble(txtPrice.getText())
//            );

            String HallNumber = txtHallNumber.getText();
            String HallType = txtHallType.getText();
            String HallStatus = String.valueOf(cmbAvailability.getValue());
            double Price = Double.parseDouble(txtPrice.getText());

            try {

                //boolean isAdd = HallsModel.addHall(hall);

                boolean isAdd = hallBO.add(new HallDTO(HallNumber, HallType, HallStatus, Price));

                if (isAdd) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Hall Added Successfully!").show();
                    clearAll();
                    setAdminHall();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Hall Not Added!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            txtPrice.requestFocus();
        }
    }


    public void btnUpdateHall(ActionEvent actionEvent) throws IOException {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if (isPriceMatched) {
           /* HallDTO hall = new HallDTO(
                    txtHallNumber.getText(),
                    txtHallType.getText(),
                    String.valueOf(cmbAvailability.getValue()),
                    Double.parseDouble(txtPrice.getText())
            );*/

            String HallNumber = txtHallNumber.getText();
            String HallType = txtHallType.getText();
            String HallStatus = String.valueOf(cmbAvailability.getValue());
            double Price = Double.parseDouble(txtPrice.getText());

            try {

                //boolean isUpdate = HallsModel.updateHall(hall);

                boolean isUpdate = hallBO.update(new HallDTO(HallNumber, HallType, HallStatus, Price));

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Hall Updated Successfully!").show();
                    clearAll();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Hall Not Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            txtPrice.requestFocus();
        }
    }

    private void clearAll() {
        txtHallNumber.clear();
        txtHallType.clear();
        txtPrice.clear();
        cmbAvailability.setPromptText(null);
    }

    public void btnCancelHall(ActionEvent actionEvent) {

        clearAll();

    }

    public void txtHallNumberOnAction(ActionEvent actionEvent) {
        String HallNumber = txtHallNumber.getText();
        try {

            HallDTO hall = hallBO.setFields(HallNumber);

            if (hall != null) {
                txtHallNumber.setText(hall.getHallNumber());
                txtHallType.setText(hall.getHallType());
                cmbAvailability.setValue(hall.getStatus());
                txtPrice.setText(String.valueOf(hall.getPrice()));

            } else {
                new Alert(Alert.AlertType.WARNING, "no Hall found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }

    public void AvailabilityOnAction(ActionEvent actionEvent) {
        ObservableList<String> data = FXCollections.observableArrayList(
                "Available",
                "Under Maintenance"
        );
        cmbAvailability.setItems(data);
    }
}

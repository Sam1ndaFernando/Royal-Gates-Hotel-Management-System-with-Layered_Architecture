package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.ComplaintBO;
import lk.RoyalGatesHotels.model.ComplainModel;
import lk.RoyalGatesHotels.dto.ComplainDTO;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.RoomsModel;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class ReceptionistComplainController implements Initializable {
    public AnchorPane receptionComplainContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxHallNumber;
    public JFXComboBox comBxRoomNumber;
    public JFXTextField txtComplainId;
    public JFXDatePicker DatepickerDate;
    public JFXTimePicker timePikrTime;
    public JFXTextArea txtAreaDescription;
    public JFXTextField txtGuestId;
    ComplaintBO complaintBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COMPLAINT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(receptionComplainContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setComplainId();
        setRoomNumbers();
        setHallNumbers();
    }

    private void setHallNumbers() {
        try {
            /*ObservableList<String> option = HallsModel.loadHallNumbers();
            comBxHallNumber.setItems(option);*/

            List<String> HIds = complaintBO.getHIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String HallIds : HIds){
                obList.add(HallIds);
            }
            comBxHallNumber.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRoomNumbers() {
        try {
            /*ObservableList<String> options = RoomsModel.loadRoomNumbers();
            comBxRoomNumber.setItems(options);*/

            List<String> RIds = complaintBO.getRIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String RoomIds : RIds){
                obList.add(RoomIds);
            }
            comBxRoomNumber.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setComplainId() {
        try {
            /*String lastComplainId= ComplainModel.getLastComplainId();
            if(lastComplainId==null){
                txtComplainId.setText("C0001");
            }else{
                String[] split=lastComplainId.split("[C]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newComplainId=String.format("C%04d", lastDigits);
                txtComplainId.setText(newComplainId);
            }*/

            String nextId = complaintBO.getNextId();
            txtComplainId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,receptionComplainContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,receptionComplainContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,receptionComplainContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,receptionComplainContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,receptionComplainContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,receptionComplainContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,receptionComplainContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,receptionComplainContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,receptionComplainContext);
    }


    public void btnAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        /*ComplainDTO complain = new ComplainDTO(
                String.valueOf(comBxRoomNumber.getValue()),
                String.valueOf(comBxHallNumber.getValue()),
                txtComplainId.getText(),
                txtGuestId.getText(),
                String.valueOf(DatepickerDate.getValue()),
                String.valueOf(timePikrTime.getValue()),
                txtAreaDescription.getText()
        );*/

        String roomId = String.valueOf(comBxRoomNumber.getValue());
        String hallId = String.valueOf(comBxHallNumber.getValue());
        String complainId = txtComplainId.getText();
        String guestId = txtGuestId.getText();
        String date = String.valueOf(DatepickerDate.getValue());
        String time = String.valueOf(timePikrTime.getValue());
        String description = txtAreaDescription.getText();

        try {
            /*boolean isAdded = ComplainModel.addComplain(complain);*/

            boolean isAdded = complaintBO.add(new ComplainDTO(roomId, hallId, complainId, guestId, date, time, description));

            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Complaint Added Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Added!").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnUpdate(ActionEvent actionEvent) {
        /*ComplainDTO complain = new ComplainDTO(
                String.valueOf(comBxRoomNumber.getValue()),
                String.valueOf(comBxHallNumber.getValue()),
                txtComplainId.getText(),
                txtGuestId.getText(),
                String.valueOf(DatepickerDate.getValue()),
                String.valueOf(timePikrTime.getValue()),
                txtAreaDescription.getText()
        );*/

        String roomId = String.valueOf(comBxRoomNumber.getValue());
        String hallId = String.valueOf(comBxHallNumber.getValue());
        String complainId = txtComplainId.getText();
        String guestId = txtGuestId.getText();
        String date = String.valueOf(DatepickerDate.getValue());
        String time = String.valueOf(timePikrTime.getValue());
        String description = txtAreaDescription.getText();

        try {

            boolean isUpdate = complaintBO.update(new ComplainDTO(roomId, hallId, complainId, guestId, date, time, description));

            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Complaint Updated Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCancel(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
    }

    public void txtComplainIdOnAction(ActionEvent actionEvent) {

        String complainid= txtComplainId.getText();

        try {
            /*ResultSet result = ComplainModel.searchComplain(txtComplainId.getText());*/

            ComplainDTO complaint = complaintBO.setFields(complainid);

            if(complaint!=null){
                comBxRoomNumber.setValue(complaint.getRoomNumber());
                comBxHallNumber.setValue(complaint.getHallNumber());
                txtComplainId.setText(complaint.getComplainId());
                txtGuestId.setText(complaint.getCustomerId());
                DatepickerDate.setValue(LocalDate.parse(complaint.getDate()));
                timePikrTime.setValue(LocalTime.parse(complaint.getTime()));
                txtAreaDescription.setText(complaint.getDescription());

            }else {
                new Alert(Alert.AlertType.WARNING, "no Complaint found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

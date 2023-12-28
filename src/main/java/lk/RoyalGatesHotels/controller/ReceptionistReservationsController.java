package lk.RoyalGatesHotels.controller;


import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.*;
import lk.RoyalGatesHotels.model.*;
import lk.RoyalGatesHotels.dto.HallReservationDTO;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReceptionistReservationsController implements Initializable {
    public AnchorPane reservationContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxGuestId;
    public JFXComboBox comBxRoomNumber;
    public JFXComboBox comBxHallNumber;
    public JFXDatePicker DatepickerCheckInDate;
    public JFXDatePicker DatepickerCheckOutDate;
    public JFXTextField txtHallReservationId;
    public JFXTextField txtRoomReservationId;
    private Connection connection;
    RoomReservationDetailsBO roomReservationDetailsBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMRESERVATIONDETAILS);
    HallReservationDetailsBO hallReservationDetailsBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLRESERVATIONDETAILS);
    HallReservationBO hallReservationBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLRESERVATION);
    HallBO hallBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALL);
    RoomBO roomBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    GuestBO guestBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUEST);
    RoomReservationBO roomReservationBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMRESERVATION);



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(reservationContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setRoomReserveNo();
        setHallReserveNo();

        setGuestIds();
        setRoomNumbers();
        setHallNumbers();

    }

    private void setHallNumbers() {
        try {
            /*ObservableList<String> option = HallsModel.loadHallNumbers();
            comBxHallNumber.setItems(option);*/

            List<String> hIds = hallBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String hallIds : hIds){
                obList.add(hallIds);
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

            List<String> rIds = roomBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String roomIds : rIds){
                obList.add(roomIds);
            }
            comBxRoomNumber.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setGuestIds() {
        try {
            ObservableList<String> options = GuestModel.loadGuestIds();
            comBxGuestId.setItems(options);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRoomReserveNo() {
        try {
            /*String lastRoomReservationId= RoomReservationModel.getLastRoomReservationId();
            if(lastRoomReservationId==null){
                txtRoomReservationId.setText("RR0001");
            }else{
                String[] split=lastRoomReservationId.split("[R][R]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newRoomReservationId=String.format("RR%04d", lastDigits);
                txtRoomReservationId.setText(newRoomReservationId);
            }*/
            String nextId = roomReservationBO.getNextId();
            txtRoomReservationId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    private void setHallReserveNo() {
        try {
            /*String lastHallReservationId= HallReservationModel.getLastHallReservationId();
            if(lastHallReservationId==null){
                txtHallReservationId.setText("HR0001");
            }else{
                String[] split=lastHallReservationId.split("[H][R]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newHallReservationId=String.format("HR%04d", lastDigits);
                txtHallReservationId.setText(newHallReservationId);
            }*/

            String nextId = hallReservationBO.getNextId();
            txtHallReservationId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,reservationContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,reservationContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,reservationContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,reservationContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,reservationContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,reservationContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,reservationContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,reservationContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,reservationContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,reservationContext);
    }

    public void btnRoomReserve(ActionEvent actionEvent) throws ClassNotFoundException {

        try {

        /*RoomReservationDTO roomReservation = new RoomReservationDTO(
                String.valueOf(comBxRoomNumber.getValue()),
                String.valueOf(comBxGuestId.getValue()),
                txtRoomReservationId.getText(),
                String.valueOf(DatepickerCheckOutDate.getValue()),
                String.valueOf(DatepickerCheckInDate.getValue())
        );*/

            String RoomNumber = String.valueOf(comBxRoomNumber.getValue());
            String GuestId = String.valueOf(comBxGuestId.getValue());
            String RoomReservationId = txtRoomReservationId.getText();
            String CheckOutDate = String.valueOf(DatepickerCheckOutDate.getValue());
            String CheckInDate = String.valueOf(DatepickerCheckInDate.getValue());

            /*boolean isAdd = RoomReservationModel.addReservation(roomReservation, connection);*/

            boolean isAdd = roomReservationBO.add(new RoomReservationDTO(RoomNumber, GuestId, RoomReservationId, CheckOutDate, CheckInDate));

            if(isAdd){
                new Alert(Alert.AlertType.ERROR,"Room Reserved Unsuccessfully!").show();
                clearAll();
                //setRoomReserveNo();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Room Reserved Successfully!").show();
            clearAll();
            setRoomReserveNo();
        }
    }

    private void clearAll() {
        comBxRoomNumber.setValue(null);
        comBxHallNumber.setValue(null);
        comBxGuestId.setValue(null);
        DatepickerCheckOutDate.setValue(null);
        DatepickerCheckInDate.setValue(null);
    }

    public void btnHallReserve(ActionEvent actionEvent) {

        /*HallReservationDTO hallReservation = new HallReservationDTO(
                String.valueOf(comBxHallNumber.getValue()),
                String.valueOf(comBxGuestId.getValue()),
                txtHallReservationId.getText(),
                String.valueOf(DatepickerCheckOutDate.getValue()),
                String.valueOf(DatepickerCheckInDate.getValue())
        );*/

        String HallNumber = String.valueOf(comBxHallNumber.getValue());
        String GuestId = String.valueOf(comBxGuestId.getValue());
        String HallReservationId = txtHallReservationId.getText();
        String CheckOutDate = String.valueOf(DatepickerCheckOutDate.getValue());
        String CheckInDate = String.valueOf(DatepickerCheckInDate.getValue());

        try {
            /*boolean isAdd = HallReservationModel.addReservation(hallReservation);*/

            boolean isAdd = hallReservationBO.add(new HallReservationDTO(HallNumber, GuestId, HallReservationId, CheckOutDate, CheckInDate));

            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Hall Reserved Successfully!").show();
                clearAll();
                setHallReserveNo();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnCancelReserve(ActionEvent actionEvent) {
        clearAll();
    }


    public void btnReservationDetails(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECERVATIONDETAILS,reservationContext);
    }
}

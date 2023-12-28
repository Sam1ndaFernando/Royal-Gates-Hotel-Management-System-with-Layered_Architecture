package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.HallBO;
import lk.RoyalGatesHotels.bo.custom.HallMaintenanceBO;
import lk.RoyalGatesHotels.bo.custom.RoomBO;
import lk.RoyalGatesHotels.bo.custom.RoomMaintenanceBO;
import lk.RoyalGatesHotels.dto.HallMaintenanceDTO;
import lk.RoyalGatesHotels.dto.RoomMaintenanceDTO;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.MaintenanceModel;
import lk.RoyalGatesHotels.dto.MaintenanceDTO;
import lk.RoyalGatesHotels.model.RoomsModel;
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

public class ReceptionistMarkMaintenanceController implements Initializable {
    public AnchorPane markMaintanceContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxRoomNumber;
    public JFXTextField txtMaintenanceId;
    public JFXDatePicker datepickerDate;
    public JFXComboBox comBxHallNumber;
    public JFXTimePicker timePikrStartTime;
    public JFXTimePicker timePikrEndTime;
    private Connection connection;

    HallMaintenanceBO hallMaintenanceBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLMAINTENANCE);
    RoomMaintenanceBO roomMaintenanceBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMMAINTENANCE);
    HallBO hallBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALL);
    RoomBO roomBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(markMaintanceContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setRoomNumbers();
        setHallNumbers();
        setRoomMaintenanceId();
        setHallMaintenanceId();
    }


    private void setHallNumbers() {
        try {
            /*ObservableList<String> option = HallsModel.loadHallNumbers();
            comBxHallNumber.setItems(option);*/

            List<String> HallIds = hallBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String hIds : HallIds){
                obList.add(hIds);
            }
            comBxHallNumber.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRoomNumbers() {
        try {
           /* ObservableList<String> options = RoomsModel.loadRoomNumbers();
            comBxRoomNumber.setItems(options);*/

            List<String> RoomIds = roomBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String rIds : RoomIds){
                obList.add(rIds);
            }
            comBxRoomNumber.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setRoomMaintenanceId() {
        try {
         /*   String lastMaintenanceId = MaintenanceModel.getLastRoomMaintenanceId();
            if (lastMaintenanceId == null) {
                txtMaintenanceId.setText("RM0001");
            } else {
                String[] split = lastMaintenanceId.split("[R][M]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newMaintenanceId = String.format("RM%04d", lastDigits);
                txtMaintenanceId.setText(newMaintenanceId);
            }*/

            String nextId = roomBO.getNextId();
            txtMaintenanceId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void setHallMaintenanceId() {
        try {
            /*String lastMaintenanceId = MaintenanceModel.getLastHallMaintenanceId();
            if (lastMaintenanceId == null) {
                txtMaintenanceId.setText("HM0001");
            } else {
                String[] split = lastMaintenanceId.split("[H][M]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newMaintenanceId = String.format("HM%04d", lastDigits);
                txtMaintenanceId.setText(newMaintenanceId);
            }*/
            String nextId = hallBO.getNextId();
            txtMaintenanceId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,markMaintanceContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,markMaintanceContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,markMaintanceContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,markMaintanceContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,markMaintanceContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,markMaintanceContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,markMaintanceContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,markMaintanceContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,markMaintanceContext);
    }

    public void btnMark(ActionEvent actionEvent) {

        String number=null;
        if(comBxHallNumber.getValue()!=null){
            number=String.valueOf(comBxHallNumber.getValue());
        }else{
            number=String.valueOf(comBxRoomNumber.getValue());
        }

        /*MaintenanceDTO maintenance = new MaintenanceDTO(
                txtMaintenanceId.getText(),
                number,
                String.valueOf(datepickerDate.getValue()),
                String.valueOf(timePikrStartTime.getValue()),
                String.valueOf(timePikrEndTime.getValue())
        );*/

        String mainteceId = txtMaintenanceId.getText();
        String numbers = number;
        String date = String.valueOf(datepickerDate.getValue());
        String StrtTime = String.valueOf(timePikrStartTime.getValue());
        String EndTime = String.valueOf(timePikrEndTime.getValue());

        try {
        if(comBxRoomNumber.getValue()!=null){
            boolean isAdd = roomMaintenanceBO.add(new RoomMaintenanceDTO(mainteceId, numbers, date, StrtTime, EndTime));
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Added!").show();
            }

        }else if(comBxHallNumber.getValue()!=null){
            boolean isAdd = hallMaintenanceBO.add(new HallMaintenanceDTO(mainteceId, numbers, date, StrtTime, EndTime));
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Added!").show();
            }
        }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdate(ActionEvent actionEvent) {
        String number=null;
        if(comBxHallNumber.getValue()!=null){
            number=String.valueOf(comBxHallNumber.getValue());
        }else{
            number=String.valueOf(comBxRoomNumber.getValue());
        }

     /*   MaintenanceDTO maintenance = new MaintenanceDTO(
                txtMaintenanceId.getText(),
                number,
                String.valueOf(datepickerDate.getValue()),
                String.valueOf(timePikrStartTime.getValue()),
                String.valueOf(timePikrEndTime.getValue())
        );*/

        String mainteceId = txtMaintenanceId.getText();
        String numbers = number;
        String date = String.valueOf(datepickerDate.getValue());
        String StrtTime = String.valueOf(timePikrStartTime.getValue());
        String EndTime = String.valueOf(timePikrEndTime.getValue());

        try {
            if(comBxRoomNumber.getValue()!=null){

                /*boolean isUpdate = MaintenanceModel.updateRoomMaintenance(maintenance, connection);*/

                boolean isUpdate = roomMaintenanceBO.update(new RoomMaintenanceDTO(mainteceId, numbers, date, StrtTime, EndTime));

                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();
                    Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Not Updated!").show();
                }

            }else if(comBxHallNumber.getValue()!=null){
                /*boolean isUpdate = MaintenanceModel.updateHallMaintenance(maintenance);*/

                boolean isUpdate = hallMaintenanceBO.update(new HallMaintenanceDTO(mainteceId, numbers, date, StrtTime, EndTime));

                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Not Updated!").show();
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCancel(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
    }

    public void comBxRoomNumberOnAction(ActionEvent actionEvent) {
        setRoomMaintenanceId();
    }

    public void comBxHallNumberOnAction(ActionEvent actionEvent) {
        setHallMaintenanceId();
    }
}

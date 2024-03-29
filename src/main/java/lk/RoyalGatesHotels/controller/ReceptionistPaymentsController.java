package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.*;
import lk.RoyalGatesHotels.dto.MealOdersDTO;
import lk.RoyalGatesHotels.dto.MealPackgesDTO;
import lk.RoyalGatesHotels.dto.PaymentDTO;
import lk.RoyalGatesHotels.model.*;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ReceptionistPaymentsController implements Initializable {
    @FXML
    public AnchorPane paymntsContext;
    @FXML
    public Label lblTime;
    @FXML
    public Label lblDate;
    @FXML
    public JFXTextField txtPaymentId;
    @FXML
    public JFXComboBox<String> comBxOrderId;
    @FXML
    public JFXDatePicker DatepickerDate;
    @FXML
    public JFXTimePicker timePikrTime;
    @FXML
    public Label lblAmount;
    @FXML
    public JFXTextField txtGuestId;
    @FXML
    public JFXComboBox<String> comBxRoomReservationId;
    @FXML
    public JFXComboBox<String> comBxHallReservationId;

    PaymentBO paymentBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    MealOrderDetailsBO mealOrderDetailsBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEALORDERDETAILS);
    RoomReservationDetailsBO roomReservationDetailsBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMRESERVATIONDETAILS);
    HallReservationDetailsBO hallReservationDetailsBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLRESERVATIONDETAILS);
    MealOrderBO mealOrderBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEALORDER);
    HallReservationBO hallReservationBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLRESERVATION);
    MealPlansBO mealPlansBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEALPLANS);
    HallBO hallBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALL);
    RoomBO roomBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    GuestBO guestBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUEST);
    RoomReservationBO roomReservationBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMRESERVATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            new Pulse(paymntsContext).play();
            lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
            DateTime.localTime(lblTime);

            setReceptionistPayments();

            setOrderIds();
            setRoomReservationNo();
            setHallReservationNo();

            lblAmount.setText("0.00");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setAmount() {
        try {
            double amount = 0;

            if (comBxOrderId.getValue() != null) {
               // ResultSet result = MealOdersModel.searchOrder(String.valueOf(comBxOrderId.getValue()));
                MealOdersDTO fields = mealOrderBO.getFields(String.valueOf(comBxOrderId.getValue()));
                int qty = fields.getQty();

                List<MealPackgesDTO> all = mealPlansBO.getAll();
                for (MealPackgesDTO mealPackgesDTO : all) {
                 if(fields.getPkgId().equals(mealPackgesDTO.getPkg_id())){
                     double price = mealPackgesDTO.getPrice();
                     amount += qty * price;
                 }
                }
            }

            if (comBxRoomReservationId.getValue() != null) {
                String roomNo = RoomReservationModel.getReservationDetails(String.valueOf(comBxRoomReservationId.getValue()));
                double price = RoomsModel.getRoomPrice(roomNo);
                amount += price;
            }

            if (comBxHallReservationId.getValue() != null) {
                String hallNo = HallReservationModel.getReservationDetails(String.valueOf(comBxHallReservationId.getValue()));
                double price = HallsModel.getHallPrice(hallNo);
                amount += price;
            }

            lblAmount.setText(String.valueOf(amount));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setHallReservationNo() {
        try {
           /* ObservableList<String> options = HallReservationModel.loadReservationIds();
            comBxHallReservationId.setItems(options);*/

            List<String> HallIds = paymentBO.getHIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String hIds : HallIds){
                obList.add(hIds);
            }
            comBxHallReservationId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRoomReservationNo() {

        try {
            /*ObservableList<String> options = RoomReservationModel.loadReservationIds();
            comBxRoomReservationId.setItems(options);*/

            List<String> RoomIds = paymentBO.getRds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String rIds : RoomIds){
                obList.add(rIds);
            }
            comBxRoomReservationId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setOrderIds() {
        try {
            /*ObservableList<String> options = MealOdersModel.loadOrderIds();
            comBxOrderId.setItems(options);*/

            List<String> OrderIds = paymentBO.getOIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String oIds : OrderIds){
                obList.add(oIds);
            }
            comBxOrderId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setReceptionistPayments() {
        try {
            /*String lastPaymentId = PaymentModel.getLastPaymentId();
            if (lastPaymentId == null) {
                txtPaymentId.setText("P0001");
            } else {
                String[] split = lastPaymentId.split("[P]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newPaymentId = String.format("P%04d", lastDigits);
                txtPaymentId.setText(newPaymentId);
            }*/

            String nextId = paymentBO.getNextId();
            txtPaymentId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD, paymntsContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN, paymntsContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE, paymntsContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS, paymntsContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION, paymntsContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT, paymntsContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST, paymntsContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE, paymntsContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS, paymntsContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT, paymntsContext);
    }

    public void btnAddPayment(ActionEvent actionEvent) {

//        PaymentDTO payment = new PaymentDTO();
//        txtPaymentId.getText(),
//                String.valueOf(comBxHallReservationId.getValue()),
//                String.valueOf(timePikrTime.getValue()),
//                String.valueOf(DatepickerDate.getValue()),
//                String.valueOf(comBxOrderId.getValue()),
//                txtGuestId.getText(),
//                String.valueOf(comBxRoomReservationId.getValue()),
//                Double.parseDouble(lblAmount.getText())

        String paymentID = txtPaymentId.getText();
        String ReservationId = String.valueOf(comBxRoomReservationId.getValue());
        String time = String.valueOf(timePikrTime.getValue());
        String date = String.valueOf(DatepickerDate.getValue());
        String orderId = String.valueOf(comBxOrderId.getValue());
        String guestId = txtGuestId.getText();
        double amount = Double.parseDouble(lblAmount.getText());

        try {
            /*boolean isAdd = PaymentModel.addPayment(payment);*/

            PaymentDTO payment = new PaymentDTO (paymentID, ReservationId, time, date, orderId, guestId, amount);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(payment);
            System.out.println("-------------------------------------------------------------------------------------");

            boolean isAdd = paymentBO.add(payment);

            if (isAdd) {
                new Thread(this::printBill).start();
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added Successfully!").showAndWait();
                Navigation.navigate(Routes.RECEPTIONPAYMENT, paymntsContext);

            } else {
                new Alert(Alert.AlertType.ERROR, "Payment Not Added!").show();
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCancelPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT, paymntsContext);
    }

    public void btnPrintBill(ActionEvent actionEvent) {
        printBill();
    }

    void printBill(){
        try {
            MealOdersDTO fields = mealOrderBO.getFields(String.valueOf(comBxOrderId.getValue()));
            String mealOrderId = "";
            String qty = "";
            if (comBxOrderId.getValue() != null) {
                mealOrderId = String.valueOf(comBxOrderId.getValue());
                qty = String.valueOf(fields.getQty());
            }

            HashMap<String, Object> hm = new HashMap<>();
            hm.put("customerId", txtGuestId.getText());
            hm.put("amount", lblAmount.getText());
            hm.put("paymentCode", txtPaymentId.getText());
            hm.put("orderId", mealOrderId);
            hm.put("qty", qty);
            hm.put("roomReservationId", comBxRoomReservationId.getValue());

            InputStream inputStream = this.getClass().getResourceAsStream("/lk/RoyalGatesHotels/report/paymentReportNew.jrxml");

            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, hm, new JREmptyDataSource());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void comBxOrderIdOnAction(ActionEvent actionEvent) {
        setAmount();
    }

    public void comBxRoomReservationIdOnAction(ActionEvent actionEvent) {
        setAmount();
    }

    public void comBxHallReservationIdOnAction(ActionEvent actionEvent) {
        setAmount();
    }
}

package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.*;
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
                ResultSet result = MealOdersModel.searchOrder(String.valueOf(comBxOrderId.getValue()));
                if (result.next()) {
                    int qty = Integer.parseInt(result.getString("qty"));
                    double price = MealPackgesModel.getPackagePrice(result.getString("pkg_id"));
                    amount += qty * price;
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
            ObservableList<String> options = HallReservationModel.loadReservationIds();
            comBxHallReservationId.setItems(options);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRoomReservationNo() {
        try {
            ObservableList<String> options = RoomReservationModel.loadReservationIds();
            comBxRoomReservationId.setItems(options);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setOrderIds() {
        try {
            ObservableList<String> options = MealOdersModel.loadOrderIds();
            comBxOrderId.setItems(options);
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

        String Payment = txtPaymentId.getText();
        String HallReservationId = String.valueOf(comBxHallReservationId.getValue());
        String Time = String.valueOf(timePikrTime.getValue());
        String Date = String.valueOf(DatepickerDate.getValue());
        String OrderId = String.valueOf(comBxOrderId.getValue());
        String GuestId = txtGuestId.getText();
        String RoomReservationId = String.valueOf(comBxRoomReservationId.getValue());
        double Amount = Double.parseDouble(lblAmount.getText());

        try {
            /*boolean isAdd = PaymentModel.addPayment(payment);*/

            boolean isAdd = paymentBO.add(new PaymentDTO(Payment, HallReservationId, Time, Date, OrderId, GuestId, RoomReservationId, Amount));

            if (isAdd) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added Successfully!").show();
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

        try {

            ResultSet result1 = GuestModel.searchGuest(txtGuestId.getText());
            ResultSet result3 = MealOdersModel.searchOrder(String.valueOf(comBxOrderId.getValue()));

            String mealUnitPrice = "";

            String meal_plan = "";
            String meal_type = "";
            if (result3.next()) {
                mealUnitPrice = String.valueOf(MealPackgesModel.searchMealPackgeData(result3.getString(5)));
                ResultSet result2 = MealPackgesModel.searchMealPlan(result3.getString("pkg_id"));
                if (result2.next()) {

                    meal_plan = result2.getString("meal_plan");
                    meal_type = result2.getString("type");
                }
            }


            String mealOrderId = "";
            String qty = "";

            String mealTotalPrice = "";
            String customerName = "";

            if (result1.next()) {
                customerName = result1.getString("customer_name");
            }

            if (comBxOrderId.getValue() != null) {
                mealOrderId = String.valueOf(comBxOrderId.getValue());

                qty = result3.getString("qty");


                mealTotalPrice = String.valueOf(Integer.valueOf(result3.getString("qty")) * MealPackgesModel.searchMealPackgeData(result3.getString(5)));

            }

            String roomNo = "";
            String roomReservationId = "";

            String roomPrice = "";
            String hallPrice = "";

            if (comBxRoomReservationId.getValue() != null) {
                roomNo = HallReservationModel.getReservationDetails(String.valueOf(comBxHallReservationId.getValue()));
                roomReservationId = String.valueOf(comBxHallReservationId.getValue());
                roomPrice = String.valueOf(RoomsModel.getRoomPrice(roomNo));
            }

            String hallNo = "";
            String hallReservationId = "";

            if (comBxHallReservationId.getValue() != null) {
                hallNo = HallReservationModel.getReservationDetails(String.valueOf(comBxHallReservationId.getValue()));
                hallReservationId = String.valueOf(comBxHallReservationId.getValue());
                hallPrice = String.valueOf(HallsModel.getHallPrice(hallNo));
            }


            HashMap<String, Object> hm = new HashMap<>();
            hm.put("customerId", txtGuestId.getText());
            hm.put("name", customerName);
            hm.put("amount", lblAmount.getText());
            hm.put("paymentCode", txtPaymentId.getText());
            hm.put("orderId", mealOrderId);
            hm.put("mealPlan", meal_plan);
            hm.put("mealType", meal_type);
            hm.put("qty", qty);
            hm.put("mealUnitPrice", mealUnitPrice);
            hm.put("mealTotalPrice", mealTotalPrice);
            hm.put("hallNo", hallNo);
            hm.put("hallReservationId", hallReservationId);
            hm.put("roomNo", roomNo);
            hm.put("roomReservationId", roomReservationId);
            hm.put("hallPrice", hallPrice);
            hm.put("roomPrice", roomPrice);

            InputStream inputStream = this.getClass().getResourceAsStream("/lk/RoyalGatesHotels/report/paymentReport.jrxml");

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

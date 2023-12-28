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
import lk.RoyalGatesHotels.bo.custom.GuestBO;
import lk.RoyalGatesHotels.bo.custom.MealOrderBO;
import lk.RoyalGatesHotels.bo.custom.MealOrderDetailsBO;
import lk.RoyalGatesHotels.bo.custom.MealPlansBO;
import lk.RoyalGatesHotels.dto.MealOdersDTO;
import lk.RoyalGatesHotels.dto.MealPackgesDTO;
import lk.RoyalGatesHotels.entity.Guest;
import lk.RoyalGatesHotels.model.GuestModel;
import lk.RoyalGatesHotels.model.MealOdersModel;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReceptionistMealOrdersController implements Initializable {

    public AnchorPane receptionMealOdersContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxGuestId;
    public JFXComboBox comBxPackageId;
    public JFXTextField txtOrderId;
    public JFXDatePicker DatepickerDate;
    public JFXTextField txtQty;

    MealOrderBO mealOrderBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEALORDER);
    MealPlansBO mealPlansBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEALPLANS);
    GuestBO  guestBO= BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUEST);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(receptionMealOdersContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setMealOrdersId();
        setGuestIds();
        setPackageIds();
    }

    private void setPackageIds() {
        try {
            List<MealPackgesDTO> all = mealPlansBO.getAll();
            ObservableList<String> ids = FXCollections.observableArrayList();
            for (MealPackgesDTO element : all) {
                ids.add(element.getPkg_id());
            }
            comBxPackageId.setItems(ids);
//           comBxPackageId.setItems(FXCollections.observableArrayList(mealPlansBO.getAll().stream().map(element -> element.getPkg_id()).collect(Collectors.toList())));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setGuestIds() {
        try {
           /* ObservableList<String> options = GuestModel.loadGuestIds();
            comBxGuestId.setItems(options);*/

            List<String> gIds = guestBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String guestIds : gIds){
                obList.add(guestIds);
            }
            comBxGuestId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setMealOrdersId() {
        try {
            /*String lastMealOrdersId = MealOdersModel.getLastMealOrdersId();
            if (lastMealOrdersId == null) {
                txtOrderId.setText("MO0001");
            } else {
                String[] split = lastMealOrdersId.split("[M][O]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newMealOrdersId = String.format("MO%04d", lastDigits);
                txtOrderId.setText(newMealOrdersId);
            }*/
            String nextId = mealOrderBO.getNextId();
            txtOrderId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
        }

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD, receptionMealOdersContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN, receptionMealOdersContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE, receptionMealOdersContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS, receptionMealOdersContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION, receptionMealOdersContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT, receptionMealOdersContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST, receptionMealOdersContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE, receptionMealOdersContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS, receptionMealOdersContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT, receptionMealOdersContext);
    }

    public void btnOrderNow(ActionEvent actionEvent) {
        /*MealOdersDTO mealOders = new MealOdersDTO(
                txtOrderId.getText(),
                String.valueOf(comBxGuestId.getValue()),
                String.valueOf(DatepickerDate.getValue()),
                Integer.parseInt(txtQty.getText()),
                String.valueOf(comBxPackageId.getValue())
        );*/

        String OrderID = txtOrderId.getText();
        String GuestID = String.valueOf(comBxGuestId.getValue());
        String Date = String.valueOf(DatepickerDate.getValue());
        int Qty = Integer.parseInt(txtQty.getText());
        String PackageID = String.valueOf(comBxPackageId.getValue());

        try {
            /*boolean isAdd = MealOdersModel.addOders(mealOders);*/

            boolean isAdd = mealOrderBO.add(new MealOdersDTO(OrderID, GuestID, Date, Qty, PackageID));

            if (isAdd) {
                new Alert(Alert.AlertType.CONFIRMATION, "Meal Oder Added Successfully!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Meal Oder Not Added!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateOrder(ActionEvent actionEvent) {
        String guestId = null;
        if (comBxGuestId.getValue() != null) {
            guestId = String.valueOf(comBxGuestId.getValue());
        } else {
            guestId = comBxGuestId.getPromptText();
        }

        String pkgId = null;
        if (comBxPackageId.getValue() != null) {
            pkgId = String.valueOf(comBxPackageId.getValue());
        } else {
            pkgId = comBxPackageId.getPromptText();
        }

        /*MealOdersDTO mealOders = new MealOdersDTO(
                txtOrderId.getText(),
                guestId,
                String.valueOf(DatepickerDate.getValue()),
                Integer.parseInt(txtQty.getText()),
                pkgId
        );*/

        String OrderID = txtOrderId.getText();
        String GuestID = String.valueOf(comBxGuestId.getValue());
        String Date = String.valueOf(DatepickerDate.getValue());
        int Qty = Integer.parseInt(txtQty.getText());
        String PackageID = String.valueOf(comBxPackageId.getValue());

        try {
            /*boolean isUpdate = MealOdersModel.updateOrder(mealOders);*/

            boolean isUpdate = mealOrderBO.update(new MealOdersDTO(OrderID, GuestID, Date, Qty, PackageID));

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Meal Oder Updated Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONMEALODERS, receptionMealOdersContext);
            } else {
                new Alert(Alert.AlertType.ERROR, "Not Updated!").show();
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void btnCancelOrder(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.RECEPTIONMEALODERS, receptionMealOdersContext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void comBxGuestIdOnAction(ActionEvent actionEvent) {

        String id = txtOrderId.getText();
        try {
            /*ResultSet result = MealOdersModel.searchOrder(txtOrderId.getText());*/
            MealOdersDTO mealOdersDTO = mealOrderBO.setFields(id);

            if (mealOdersDTO != null) {

                /*comBxGuestId.setPromptText(result.getString("customer_id"));
                DatepickerDate.setValue(LocalDate.parse(result.getString("date")));
                txtQty.setText(result.getString("qty"));
                comBxPackageId.setPromptText(result.getString("pkg_id"));*/

                txtOrderId.setText(mealOdersDTO.getOrderId());
                comBxGuestId.setValue(mealOdersDTO.getCustomerId());
                DatepickerDate.setValue(LocalDate.parse(mealOdersDTO.getDate()));
                txtQty.setText(String.valueOf(mealOdersDTO.getQty()));
                comBxPackageId.setValue(mealOdersDTO.getPkgId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

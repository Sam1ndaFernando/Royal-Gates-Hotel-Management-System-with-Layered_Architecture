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
import lk.RoyalGatesHotels.bo.custom.GuestBO;
import lk.RoyalGatesHotels.bo.custom.UserBO;
import lk.RoyalGatesHotels.model.GuestModel;
import lk.RoyalGatesHotels.dto.GuestDTO;
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

public class ReceptionistGuestController implements Initializable {
    public AnchorPane guestReportContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtMobile;
    public JFXDatePicker DatepickerDate;
    public JFXTextField txtNIC;
    public JFXTextField txtEmail;
    public JFXTextField txtGuestName;
    public JFXTextField txtProvince;
    public JFXTextField txtAddress;
    public JFXTextField txtGuestID;

    GuestBO guestBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUEST);
    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(guestReportContext).play();
        setGuestId();
        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

    }

    public void setGuestId() {
        try {
           /* String lastGuestId = GuestModel.getLastGuestId();
            if (lastGuestId == null) {
                txtGuestID.setText("C0001");
            } else {
                String[] split = lastGuestId.split("[C]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newGuestId = String.format("C%04d", lastDigits);
                txtGuestID.setText(newGuestId);
            }*/

            String nextId = guestBO.getNextId();
            txtGuestID.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error getting last guest ID: " + e.getMessage()).show();
        }

    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,guestReportContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,guestReportContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,guestReportContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,guestReportContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,guestReportContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,guestReportContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,guestReportContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,guestReportContext);
    }

    public void btnAddGuest(ActionEvent actionEvent) {

        boolean isNameMatched = RegExPattern.getNamePattern().matcher(txtGuestName.getText()).matches();
        boolean isNicMatched = RegExPattern.getIdPattern().matcher(txtNIC.getText()).matches();
        boolean isOldNicMatched = RegExPattern.getOldIDPattern().matcher(txtNIC.getText()).matches();
        boolean isContactNoMatched = RegExPattern.getMobilePattern().matcher(txtMobile.getText()).matches();
        boolean isEmailMatched = RegExPattern.getEmailPattern().matcher(txtEmail.getText()).matches();

        if(isNameMatched) {
            if (isNicMatched || isOldNicMatched) {
                if (isContactNoMatched) {
                    if (isEmailMatched) {

                        /*GuestDTO guest = new GuestDTO(
                                txtGuestID.getText(),
                                txtGuestName.getText(),
                                String.valueOf(DatepickerDate.getValue()),
                                txtNIC.getText(),
                                txtAddress.getText(),
                                txtMobile.getText(),
                                txtEmail.getText(),
                                txtProvince.getText()
                        );*/

                        String GuestID = txtGuestID.getText();
                        String GuestName = txtGuestName.getText();
                        String Date = String.valueOf(DatepickerDate.getValue());
                        String NIC = txtNIC.getText();
                        String Address = txtAddress.getText();
                        String Mobile = txtMobile.getText();
                        String Email = txtEmail.getText();
                        String Province = txtProvince.getText();

                        try {
                            /*boolean isAdd = GuestModel.addGuest(guest);*/

                            boolean isAdd = guestBO.add(new GuestDTO(GuestID, GuestName, Date, NIC, Address, Mobile, Email, Province));

                            if(isAdd){
                                new Alert(Alert.AlertType.CONFIRMATION,"Guest Added Successfully!").show();
                                Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Guest Not Added!").show();
                            }

                        } catch (SQLException | ClassNotFoundException | IOException e) {
                            throw new RuntimeException(e);
                        }

                    }else{txtEmail.requestFocus();}
                }else{txtMobile.requestFocus();}
            }else{txtNIC.requestFocus();}
        }else{txtGuestName.requestFocus();}


    }

    public void btnUpdateGuest(ActionEvent actionEvent) {
        boolean isNameMatched = RegExPattern.getNamePattern().matcher(txtGuestName.getText()).matches();
        boolean isNicMatched = RegExPattern.getIdPattern().matcher(txtNIC.getText()).matches();
        boolean isOldNicMatched = RegExPattern.getOldIDPattern().matcher(txtNIC.getText()).matches();
        boolean isContactNoMatched = RegExPattern.getMobilePattern().matcher(txtMobile.getText()).matches();
        boolean isEmailMatched = RegExPattern.getEmailPattern().matcher(txtEmail.getText()).matches();

        if(isNameMatched){
            if(isNicMatched || isOldNicMatched){
                if(isContactNoMatched){
                    if(isEmailMatched){

                        /*GuestDTO guest = new GuestDTO(
                                txtGuestID.getText(),
                                txtGuestName.getText(),
                                String.valueOf(DatepickerDate.getValue()),
                                txtNIC.getText(),
                                txtAddress.getText(),
                                txtMobile.getText(),
                                txtEmail.getText(),
                                txtProvince.getText()
                        );*/

                        String GuestID = txtGuestID.getText();
                        String GuestName = txtGuestName.getText();
                        String Date = String.valueOf(DatepickerDate.getValue());
                        String NIC = txtNIC.getText();
                        String Address = txtAddress.getText();
                        String Mobile = txtMobile.getText();
                        String Email = txtEmail.getText();
                        String Province = txtProvince.getText();

                        try {
                            /*boolean isUpdate = GuestModel.updateGuest(guest);*/

                            boolean isUpdate = guestBO.update(new GuestDTO(GuestID, GuestName, Date, NIC, Address, Mobile, Email, Province));

                            if(isUpdate){
                                new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();
                                Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Employee Not Updated!").show();
                            }
                        } catch (SQLException | ClassNotFoundException | IOException e) {
                            throw new RuntimeException(e);
                        }

                    }else{txtEmail.requestFocus();}
                }else{txtMobile.requestFocus();}
            }else{txtNIC.requestFocus();}
        }else{txtGuestName.requestFocus();}

    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,guestReportContext);
    }


    public void btnCancelGuest(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
    }


    public void txtGuestIDOnAction(ActionEvent actionEvent) {
        String GuestId = txtGuestID.getText();

        try {
            /*ResultSet result = GuestModel.searchGuest(txtGuestID.getText());*/
            GuestDTO guest = guestBO.setFields(GuestId);

            if(guest != null){

                /*txtGuestName.setText(result.getString("customer_name"));
                txtNIC.setText(result.getString("nic"));
                txtAddress.setText(result.getString("address"));
                txtMobile.setText(result.getString("Mobile"));
                txtEmail.setText(result.getString("Email"));
                txtProvince.setText(result.getString("Province"));
                DatepickerDate.setValue(LocalDate.parse(result.getString("date")));*/

                txtGuestID.setText(guest.getCustomerId());
                txtGuestName.setText(guest.getCustomer_name());
                DatepickerDate.setValue(LocalDate.parse(guest.getDate()));
                txtNIC.setText(guest.getNic());
                txtAddress.setText(guest.getAddress());
                txtMobile.setText(guest.getMobile());
                txtEmail.setText(guest.getEmail());
                txtProvince.setText(guest.getProvince());

            } else {
                new Alert(Alert.AlertType.WARNING, "no Guest found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

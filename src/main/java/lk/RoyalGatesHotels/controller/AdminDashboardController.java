package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.DashboardBO;
import lk.RoyalGatesHotels.bo.custom.RoomBO;
import lk.RoyalGatesHotels.model.ComplainModel;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.PaymentModel;
import lk.RoyalGatesHotels.model.RoomsModel;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable{
    DashboardBO dashboardBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);

    public AnchorPane adminDshbrdContext;
    public Label lblTime;
    public Label lblTotalRooms;
    public Label lblBookedRooms;
    public Label lblTotalEarnings;
    public Label lblBookedHalls;
    public Label lblAvailableHalls;
    public Label lblAvailableRooms;
    public Label lblComplaints;
    public JFXButton btnAboutUs;
    public JFXButton btnContact;
    public Label lblDateNow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminDshbrdContext).play();
        lblDateNow.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);
        setDashboardLabels();
    }

    public void setDashboardLabels(){

        try {
            int roomCount = dashboardBO.getTotalRooms();
            lblTotalRooms.setText(String.valueOf(roomCount));

            int complaint = dashboardBO.getComplaints();
            lblComplaints.setText(String.valueOf(complaint));

            int bookedRooms = dashboardBO.getBookedRooms();
            lblBookedRooms.setText(String.valueOf(bookedRooms));

            int availableRooms = dashboardBO.getAvailableRooms();
            lblAvailableRooms.setText(String.valueOf(availableRooms));

            int bookedHalls = dashboardBO.getBookedHalls();
            lblBookedHalls.setText(String.valueOf(bookedHalls));

            //int availableHallsCount = dashboardBO.getTotalHalls() - dashboardBO.getBookedHalls();
            //lblAvailableHalls.setText(String.valueOf(availableHallsCount));

            double totalEarnings = PaymentModel.getTodayEarnings(String.valueOf(LocalDate.now()));
            lblTotalEarnings.setText(String.valueOf(totalEarnings));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD, adminDshbrdContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,adminDshbrdContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN, adminDshbrdContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,adminDshbrdContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,adminDshbrdContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,adminDshbrdContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,adminDshbrdContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        System.out.println("Done");
        Navigation.navigate(Routes.CONTACT,adminDshbrdContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,adminDshbrdContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,adminDshbrdContext);
    }
    public void btnTotalRooms(ActionEvent actionEvent) {
    }

    public void btnBookedRooms(ActionEvent actionEvent) {
    }

    public void btnTotalEarnings(ActionEvent actionEvent) {
    }

    public void btnBookedHalls(ActionEvent actionEvent) {
    }

    public void btnAvailableHalls(ActionEvent actionEvent) {
    }

    public void btnAvailableRooms(ActionEvent actionEvent) {
    }

    public void btnComplaints(ActionEvent actionEvent) {
    }

    public void btnAboutUsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,adminDshbrdContext);

    }

    public void btnContactOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,adminDshbrdContext);

    }
}

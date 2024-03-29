package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.MealPlansBO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.HallDTO;
import lk.RoyalGatesHotels.dto.MealPackgesDTO;
import lk.RoyalGatesHotels.dto.tm.HallTM;
import lk.RoyalGatesHotels.model.MealPackgesModel;
import lk.RoyalGatesHotels.dto.tm.MealPackgesTM;
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
import java.util.List;
import java.util.ResourceBundle;

public class AdminMealPackagesReportController implements Initializable {
    //public AnchorPane guestReportContext;
    public Label lblTime;
    public Label lblDate;
    public AnchorPane MealPackageContext;
    public TableColumn colPackageId;
    public TableColumn colMealPlan;
    public TableColumn colType;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableView TblMealPkg;

    MealPlansBO mealPlansBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEALPLANS);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(MealPackageContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        colPackageId.setCellValueFactory(new PropertyValueFactory<>("pkg_id"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMealPlan.setCellValueFactory(new PropertyValueFactory<>("meal_plan"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        loadMealPackagesData();

    }

    private void loadMealPackagesData() {
        /*ObservableList<MealPackgesTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = MealPackgesModel.getMealPackgeData();
            while (result.next()){

                data.add(
                        new MealPackgesTM(
                                result.getString("pkg_id"),
                                result.getString("price"),
                                result.getString("description"),
                                result.getString("meal_Plan"),
                                result.getString("type")
                        ));

            }
            TblMealPkg.setItems(data);*/

        try {
            List<MealPackgesDTO> mealPackgesDTOs = mealPlansBO.getAll();
            ObservableList<MealPackgesTM> data = FXCollections.observableArrayList();

            for (MealPackgesDTO mealPackgesDTO : mealPackgesDTOs) {
                data.add(new MealPackgesTM(
                        mealPackgesDTO.getPkg_id(),
                        mealPackgesDTO.getPrice(),
                        mealPackgesDTO.getDescription(),
                        mealPackgesDTO.getMeal_plan(),
                        mealPackgesDTO.getType()
                ));
            }
            TblMealPkg.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }
    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,MealPackageContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,MealPackageContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,MealPackageContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,MealPackageContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,MealPackageContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,MealPackageContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,MealPackageContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,MealPackageContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,MealPackageContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,MealPackageContext);
    }

    public void btnHallReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLREPORT,MealPackageContext);
    }

    public void btnEmployeeReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEEREPORT,MealPackageContext);
    }

    public void btnRoomReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOMREPORT,MealPackageContext);
    }

    public void btnPrint(ActionEvent actionEvent) {
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/RoyalGatesHotels/report/mealpackages.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}

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
import lk.RoyalGatesHotels.bo.custom.HallBO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.HallDTO;
import lk.RoyalGatesHotels.dto.tm.EmployeeTM;
import lk.RoyalGatesHotels.dto.tm.HallTM;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminHallReportController implements Initializable {
    //public AnchorPane guestReportContext;
    public Label lblTime;
    public Label lblDate;

    public AnchorPane HallReportContext;
    public TableView tblHallReport;
    public TableColumn colHallNumber;
    public TableColumn colHallType;
    public TableColumn colPrice;
    public TableColumn colStatus;

    private HallBO hallBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALL);

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(HallReportContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        colHallType.setCellValueFactory(new PropertyValueFactory<>("hallType"));
        colHallNumber.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        loadHallData();
    }

    private void loadHallData() throws SQLException, ClassNotFoundException {
      /*  ObservableList<HallTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = HallsModel.getHallData();
            while (result.next()){

                data.add(
                        new HallTM(
                                result.getString("hallNumber"),
                                result.getString("hall_type"),
                                result.getString("status"),
                                result.getString("price")
                        ));

            }
            tblHallReport.setItems(data);*/

        try {
            List<HallDTO> halls = hallBO.getAllHalls();
            ObservableList<HallTM> data = FXCollections.observableArrayList();

            for (HallDTO hall : halls) {
                data.add(new HallTM(
                        hall.getHallNumber(),
                        hall.getHallType(),
                        hall.getStatus(),
                        hall.getPrice()
                ));
            }

            tblHallReport.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,HallReportContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,HallReportContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,HallReportContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,HallReportContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,HallReportContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,HallReportContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,HallReportContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,HallReportContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,HallReportContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,HallReportContext);
    }

    public void btnRoomReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOMREPORT,HallReportContext);
    }

    public void btnEmployeeReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEEREPORT,HallReportContext);
    }

    public void btnMealPackageReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,HallReportContext);
    }


    public void btnPrint(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {

        InputStream inputStream = this.getClass().getResourceAsStream("/lk/RoyalGatesHotels/report/hallReport.jrxml");
        try{
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}

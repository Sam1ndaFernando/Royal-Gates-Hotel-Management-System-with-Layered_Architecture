package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.bo.BOFactory;
import lk.RoyalGatesHotels.bo.custom.MealPlansBO;
import lk.RoyalGatesHotels.model.MealPackgesModel;
import lk.RoyalGatesHotels.dto.MealPackgesDTO;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.RegExPattern;
import lk.RoyalGatesHotels.util.Routes;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminMealPlansController implements Initializable {
    public JFXTextArea txtAreaDescription;
    public JFXTextField txtPrice;
    public AnchorPane adminMealplansContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtPackageId;
    public JFXTextField txtMealPlans;
    public JFXTextField txtMealType;

    MealPlansBO mealPlansBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEALPLANS);

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminMealplansContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setMealPackage();
    }

    private void setMealPackage() throws SQLException, ClassNotFoundException {
        try {
            /*String lastMealPkgId= MealPackgesModel.getLastMealPkgId();
            if(lastMealPkgId==null){
                txtPackageId.setText("M0001");
            }else{
                String[] split=lastMealPkgId.split("[M]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newMealPkgId=String.format("M%04d", lastDigits);
                txtPackageId.setText(newMealPkgId);*/

            String nextId = mealPlansBO.getNextId();
            txtPackageId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public void btnAddMeal(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){

            /*MealPackgesDTO mealPackges = new MealPackgesDTO(
                    txtPackageId.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    txtAreaDescription.getText(),
                    txtMealPlans.getText(),
                    txtMealType.getText()
            );*/

            String pkgId = txtPackageId.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String description = txtAreaDescription.getText();
            String mealPlan = txtMealPlans.getText();
            String mealType = txtMealType.getText();

            try {

               /* boolean isAdd = MealPackgesModel.addPackage(mealPackges);*/

                boolean isAdd = mealPlansBO.add(new MealPackgesDTO(pkgId,price,description,mealPlan,mealType));

                if(isAdd){
                    new Alert(Alert.AlertType.CONFIRMATION,"Meal Package Added Successfully!").show();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Meal Package Not Added!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }
    }


    public void btnUpdateMeal(ActionEvent actionEvent) {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            /*MealPackgesDTO mealPackges = new MealPackgesDTO(
                    txtPackageId.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    txtAreaDescription.getText(),
                    txtMealPlans.getText(),
                    txtMealType.getText()
            );*/

            String pkgId = txtPackageId.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String description = txtAreaDescription.getText();
            String mealPlan = txtMealPlans.getText();
            String mealType = txtMealType.getText();

            try {
                /*boolean isUpdate = MealPackgesModel.updatePackage(mealPackges);*/

                boolean isUpdate = mealPlansBO.update(new MealPackgesDTO(pkgId,price,description,mealPlan,mealType));

                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Meal Updated Successfully!").show();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Meal Not Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }

    }

    public void btnCancel(ActionEvent actionEvent) {
        clearAll();
    }

    public void clearAll(){
        txtPrice.setText(null);
        txtAreaDescription.setText(null);
        txtMealPlans.setText(null);
        txtMealType.setText(null);
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,adminMealplansContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,adminMealplansContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,adminMealplansContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,adminMealplansContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,adminMealplansContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,adminMealplansContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,adminMealplansContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,adminMealplansContext);
    }


    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,adminMealplansContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,adminMealplansContext);
    }


    public void PackageIdOnAction(ActionEvent actionEvent) {
        String PackageId    =txtPackageId.getText();
        try {
            /*ResultSet result = MealPackgesModel.searchMealPlan(txtPackageId.getText());*/

            MealPackgesDTO mealPackges= mealPlansBO.setFields(PackageId);

            if (mealPackges != null) {

                txtPackageId.setText(mealPackges.getPkg_id());
                txtPrice.setText(String.valueOf(mealPackges.getPrice()));
                txtAreaDescription.setText(mealPackges.getDescription());
                txtMealPlans.setText(mealPackges.getMeal_plan());
                txtMealType.setText(mealPackges.getType());

            } else {
                new Alert(Alert.AlertType.ERROR, "Meal Package does not exist!").show();
                clearAll();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

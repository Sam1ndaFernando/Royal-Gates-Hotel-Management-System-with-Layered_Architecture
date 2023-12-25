package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.MealOrderDetailsBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.MealOrderDetailsDAO;

import java.sql.SQLException;

public class MealOrderDetailsBOImpl implements MealOrderDetailsBO {
    MealOrderDetailsDAO mealOrderDetailsDAO = (MealOrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALORDERDETAILS);


    @Override
    public boolean add(String orderId, String packageId) throws SQLException, ClassNotFoundException {
        return mealOrderDetailsDAO.add(packageId,orderId);
    }

    @Override
    public boolean delete(String orderId) throws SQLException, ClassNotFoundException {
        return mealOrderDetailsDAO.delete(orderId);
    }

    @Override
    public String getpkg(String cmbOrderId) throws SQLException, ClassNotFoundException {
        return mealOrderDetailsDAO.getpkg(cmbOrderId);
    }
}

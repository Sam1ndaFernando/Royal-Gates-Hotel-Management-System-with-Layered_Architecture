package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.MealOrderBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.MealOrderDAO;
import lk.RoyalGatesHotels.dao.custom.MealOrderDetailsDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.MealOdersDTO;
import lk.RoyalGatesHotels.entity.MealOders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MealOrderBOImpl implements MealOrderBO {
    MealOrderDAO mealOrderDAO = (MealOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALORDER);
    MealOrderDetailsDAO mealOrderDetailsDAO = (MealOrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALORDERDETAILS);


    @Override
    public MealOdersDTO getFields(String id) throws SQLException, ClassNotFoundException {
        MealOders mealOrder = mealOrderDAO.getFields(id);
        return new MealOdersDTO(mealOrder.getOrderId(), mealOrder.getCustomerId(), mealOrder.getDate(), mealOrder.getQty(), mealOrder.getPkgId());
    }

    @Override
    public String getQty(String id) throws SQLException, ClassNotFoundException {
        return mealOrderDAO.getQty(id);
    }

    @Override
    public boolean Order(String orderId, String guestId, String packageId, String date, int qty) throws SQLException {
        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = mealOrderDAO.add(new MealOders(orderId,guestId,date,qty,packageId));
            if(isSaved){
                boolean isAdded= mealOrderDetailsDAO.add(orderId, packageId);
                if (isAdded){
                    con.commit();
                    return true;
                }
            }
            return false;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return mealOrderDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("O%04d", newNum);
            return newId;
        }
        return "O0001";
    }

    @Override
    public boolean add(MealOdersDTO dto) throws SQLException, ClassNotFoundException {
        return mealOrderDAO.add(new MealOders(dto.getOrderId(),dto.getCustomerId(),dto.getDate(),dto.getQty(),dto.getPkgId()));
    }

    @Override
    public boolean update(MealOdersDTO dto) throws SQLException, ClassNotFoundException {
        return mealOrderDAO.update(new MealOders(dto.getOrderId(),dto.getCustomerId(),dto.getDate(),dto.getQty(),dto.getPkgId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return mealOrderDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public MealOdersDTO setFields(String id) throws SQLException, ClassNotFoundException {
        MealOders mealOrder = mealOrderDAO.setFields(id);
        return new MealOdersDTO(mealOrder.getOrderId(),mealOrder.getCustomerId(),mealOrder.getDate(),mealOrder.getQty(),mealOrder.getPkgId());

    }
}

package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.MealPlansBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.MealPlansDAO;
import lk.RoyalGatesHotels.dto.MealPackgesDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPlansBOImpl implements MealPlansBO {
    MealPlansDAO mealPlansDAO = (MealPlansDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALPLANS);


    @Override
    public List<MealPackgesDTO> getAll() throws SQLException, ClassNotFoundException {
        List<MealPackgesDTO> data = new ArrayList<>();
        List<MealPackgesDTO> allEntityData = mealPlansDAO.getAll();

        for (MealPackgesDTO mealplans : allEntityData) {
            data.add(new MealPackgesDTO(mealplans.getPkg_id(),mealplans.getPrice(),mealplans.getDescription(),mealplans.getMeal_plan(),mealplans.getType()));
        }
        return data;
    }

    @Override
    public String getItems(String packageId) throws SQLException, ClassNotFoundException {
        return mealPlansDAO.getItems(packageId);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return mealPlansDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    @Override
    public boolean add(MealPackgesDTO dto) throws SQLException, ClassNotFoundException {
        return mealPlansDAO.add(new MealPackgesDTO(dto.getPkg_id(),dto.getPrice(),dto.getDescription(),dto.getMeal_plan(),dto.getType()));
    }

    @Override
    public boolean update(MealPackgesDTO dto) throws SQLException, ClassNotFoundException {
        return mealPlansDAO.update(new MealPackgesDTO(dto.getPkg_id(),dto.getPrice(),dto.getDescription(),dto.getMeal_plan(),dto.getType()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return mealPlansDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return mealPlansDAO.getIds();
    }

    @Override
    public MealPackgesDTO setFields(String id) throws SQLException, ClassNotFoundException {
        MealPackgesDTO mealPackges = mealPlansDAO.setFields(id);
        return new MealPackgesDTO(mealPackges.getPkg_id(),mealPackges.getPrice(),mealPackges.getDescription(),mealPackges.getMeal_plan(),mealPackges.getType());
    }
}

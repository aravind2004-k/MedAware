
package com.medaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medaware.model.Medicine;
import com.medaware.util.DBConnection;

public class MedicineDAO {

    public List<Medicine> getMedicinesByConditionId(int conditionId) {

        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT M.MEDICINE_ID, M.MEDICINE_NAME, "
                   + "M.GENERAL_USES, M.PRECAUTIONS, "
                   + "M.SIDE_EFFECTS, M.WARNINGS "
                   + "FROM MEDICINES M "
                   + "INNER JOIN CONDITION_MEDICINE CM "
                   + "ON M.MEDICINE_ID = CM.MEDICINE_ID "
                   + "WHERE CM.CONDITION_ID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, conditionId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Medicine medicine = new Medicine();

                    medicine.setMedicineId(rs.getInt("MEDICINE_ID"));
                    medicine.setMedicineName(rs.getString("MEDICINE_NAME"));
                    medicine.setGeneralUses(rs.getString("GENERAL_USES"));
                    medicine.setPrecautions(rs.getString("PRECAUTIONS"));
                    medicine.setSideEffects(rs.getString("SIDE_EFFECTS"));
                    medicine.setWarnings(rs.getString("WARNINGS"));

                    medicines.add(medicine);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return medicines;
    }
}

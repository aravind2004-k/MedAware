
package com.medaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medaware.model.SearchHistory;
import com.medaware.util.DBConnection;

public class SearchHistoryDAO {

    // Save a user's search history
    public void saveSearchHistory(int userId, int conditionId) {

        String sql = "INSERT INTO SEARCH_HISTORY "
                   + "(HISTORY_ID, USER_ID, CONDITION_ID, SEARCH_DATE) "
                   + "VALUES ("
                   + "(SELECT NVL(MAX(HISTORY_ID), 0) + 1 "
                   + "FROM SEARCH_HISTORY), ?, ?, CURRENT_TIMESTAMP)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, conditionId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve search history for a particular user
    public List<SearchHistory> getSearchHistoryByUserId(int userId) {

        List<SearchHistory> historyList = new ArrayList<>();

        String sql = "SELECT SH.HISTORY_ID, SH.USER_ID, "
                   + "SH.CONDITION_ID, C.CONDITION_NAME, SH.SEARCH_DATE "
                   + "FROM SEARCH_HISTORY SH "
                   + "INNER JOIN CONDITIONS C "
                   + "ON SH.CONDITION_ID = C.CONDITION_ID "
                   + "WHERE SH.USER_ID = ? "
                   + "ORDER BY SH.SEARCH_DATE DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    SearchHistory history = new SearchHistory();

                    history.setHistoryId(rs.getInt("HISTORY_ID"));
                    history.setUserId(rs.getInt("USER_ID"));
                    history.setConditionId(rs.getInt("CONDITION_ID"));
                    history.setConditionName(
                            rs.getString("CONDITION_NAME"));
                    history.setSearchDate(
                            rs.getTimestamp("SEARCH_DATE"));

                    historyList.add(history);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }
}

package ru.gubenko.test.dao;

import ru.gubenko.test.model.Part;
import ru.gubenko.test.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartDao {

    private Connection connection;

    public PartDao() {
        connection = DbUtil.getConnection();
    }

    public List<Part> getAllParts() {
        List<Part> parts = new ArrayList<Part>();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from parts");
            while (set.next()) {
                Part part = new Part();
                part.setName(set.getString("partname"));
                part.setNumber(set.getString("partnumber"));
                part.setVendor(set.getString("vendor"));
                part.setQty(set.getInt("qty"));
                part.setShipped(set.getDate("shipped"));
                part.setReceived(set.getDate("received"));
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }
}

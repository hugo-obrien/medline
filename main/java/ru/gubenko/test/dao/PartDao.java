package ru.gubenko.test.dao;

import ru.gubenko.test.model.Part;
import ru.gubenko.test.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PartDao {

    private Connection connection;

    public PartDao() {
        connection = DbUtil.getConnection();
    }

    public List<Part> getAllParts() {
        return getParts("select * from parts");
    }

    public List<Part> getParts(HashMap<String, String> params) {
        StringBuilder builder = new StringBuilder("select * from parts");

        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        if (iter.hasNext()) {
            builder.append(" where ");
        }
        while (iter.hasNext()) {
            Map.Entry<String, String> next = iter.next();
            {
                builder.append(getValue(next.getKey(), String.valueOf(next.getValue())));
            }
            if (iter.hasNext()) {
                builder.append(" AND ");
            }
        }
        builder.append(";");
        return getParts(builder.toString());
    }

    public List<Part> getParts(String query) {
        List<Part> parts = new ArrayList<Part>();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
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

    private String getValue(String param, String value) {
        switch (param) {
            case "partnumber" : return likeValue(param, value);
            case "partname" : return likeValue(param, value);
            case "vendor" : return likeValue(param, value);
            case "qty" : return moreValue(param, value);
            case "shippedafter" : return betweenValue(param, value);
            case "shippedbefore" : return betweenValue(param, value);
            case "receivedafter" : return betweenValue(param, value);
            case "receivedbefore" : return betweenValue(param, value);
        }
        return null;
    }

    private String betweenValue(String param, String value) {
        StringBuilder builder = new StringBuilder("(");
        switch (param) {
            case "shippedafter" : {
                builder.append(" shipped ").append(">= '" ).append(value).append("')");
                break;
            }
            case "shippedbefore" : {
                builder.append(" shipped ").append("<= '" ).append(value).append("')");
                break;
            }
            case "receivedafter" : {
                builder.append(" received ").append(">= '" ).append(value).append("')");
                break;
            }
            case "receivedbefore" : {
                builder.append(" received ").append("<= '" ).append(value).append("')");
            }
        }
        return builder.toString();
    }

    private String likeValue(String param, String value) {
        return "(" + param + " like '%" + value + "%')";
    }

    private String moreValue(String param, String value) {
        return "(" + param + " > " + value + ")";
    }
}

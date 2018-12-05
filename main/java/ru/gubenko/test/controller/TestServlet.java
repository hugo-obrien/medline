package ru.gubenko.test.controller;

import ru.gubenko.test.dao.PartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private enum Params {
        partname,
        partnumber,
        vendor,
        qty,
        shippedafter,
        shippedbefore,
        receivedafter,
        receivedbefore
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String submitted = request.getParameter("submitted");
        if (submitted != null && submitted.equals("true")) {
            request.setAttribute("show_table", true);

            PartDao dao = new PartDao();
            request.setAttribute("parts", dao.getParts(getParamsMap(request)));
        }

        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

    private HashMap<String, String> getParamsMap(HttpServletRequest request) {
        HashMap<String, String> result = new HashMap<>();
        for (Params param : Params.values()) {
            String parameter = request.getParameter(param.name());
            if (parameter != null && !"".equals(parameter)) {
                result.put(param.name(), parameter);
            }
        }
        return result;
    }
}

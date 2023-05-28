package ru.javarush.quest.bogdanov.questdelta.controller.info;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_NOT_IMPLEMENTED;

@WebServlet(name = "NotImplementedServlet", value = GO_NOT_IMPLEMENTED)
public class NotImplementedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/notimplemented.jsp").forward(request, response);
    }
}

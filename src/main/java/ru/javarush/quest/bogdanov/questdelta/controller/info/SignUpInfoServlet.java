package ru.javarush.quest.bogdanov.questdelta.controller.info;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static ru.javarush.quest.bogdanov.questdelta.utils.Go.GO_SIGNUP_INFO;

@WebServlet(name = "SignUpInfoServlet", value = GO_SIGNUP_INFO)
public class SignUpInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/signupinfo.jsp").forward(request, response);
    }
}

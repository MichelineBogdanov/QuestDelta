package ru.javarush.quest.bogdanov.questdelta.controller.info;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.javarush.quest.bogdanov.questdelta.utils.Go;

import java.io.IOException;

@WebServlet(name = "SignUpInfoServlet", value = Go.SIGNUP_INFO)
public class SignUpInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/signupinfo.jsp").forward(request, response);
    }
}

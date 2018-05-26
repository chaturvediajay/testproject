package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value={"/UserSession"})
public class UserSession
extends HttpServlet {
    private static final long serialVersionUID = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            try {
                session.removeAttribute("admin");
                session.removeAttribute("user");
                session.removeAttribute("loginSession");
                session.removeAttribute("shippingModel");
                if (session != null) {
                    session.invalidate();
                }
                String pageToForward = request.getContextPath();
               // response.sendRedirect("");
            }
            catch (Exception sqle) {
               // response.sendRedirect("index.jsp");
            }
        } else {
           // response.sendRedirect("index.jsp");
        }
        RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath());
        System.out.println("redirect:- "+request.getContextPath());
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

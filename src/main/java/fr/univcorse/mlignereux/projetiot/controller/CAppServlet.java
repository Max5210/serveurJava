package fr.univcorse.mlignereux.projetiot.controller;

import javax.faces.component.html.HtmlDataTable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 09/11/2015.
 */
@WebServlet(name = "app", urlPatterns = "/")
public class CAppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Bonjour");
    }
}

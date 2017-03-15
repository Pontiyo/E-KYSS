package ekyss.controller;

import base.servletBase;
import ekyss.model.BeanFactory;
import ekyss.model.BeanUtilities;
import ekyss.model.Database;
import ekyss.model.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name="LoginServlet",
        urlPatterns = {
                "",
                "/login",
                "/logout",
        }
)
public class LoginServlet extends servletBase {

    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        LoginBean bean = new LoginBean();
        BeanUtilities.populateBean(bean, request);



        // --- START AV TEST ----
        // Test om informationen kommer in till bönan från POST-anropet
        System.out.println("USERNAME: " + bean.getUsername());
        System.out.println("PASSWORD: " + bean.getPassword());
        System.out.println("GROUP: " + bean.getSelectedGroup());
        // Skicka tillbaka till login
        doGet(request, response);
        // --- SLUT PÅ TEST ----
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        LoginBean bean = BeanFactory.getLoginBean();

        if (request.getServletPath().equals("/login")) {
            bean.setAdminLogin(true);
        } else {
            bean.setAdminLogin(false);
        }

        forwardToView(request, response, "/login.jsp", bean);
    }

}

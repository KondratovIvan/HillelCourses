package com.example.demo.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static void positiveOut(HttpServletResponse response, String user) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("Welcome back to the team, " + user + "!");
    }
    public static void negativeOut(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("Either user name or password is wrong!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Это название 2-х параметров, которые мы передаем
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        Map<String, ArrayList<String>> credentials=new HashMap<>();

        ArrayList<String> usersPasswords=new ArrayList<String>();
        usersPasswords.add("userPassword1");
        usersPasswords.add("userPassword2");
        usersPasswords.add("userPassword3");

        ArrayList<String> vipUsersPasswords=new ArrayList<String>();
        vipUsersPasswords.add("vipUserPassword1");
        vipUsersPasswords.add("vipUserPassword2");
        vipUsersPasswords.add("vipUserPassword3");

        ArrayList<String> AdminsPasswords=new ArrayList<String>();
        AdminsPasswords.add("adminPassword1");
        AdminsPasswords.add("adminPassword2");
        AdminsPasswords.add("adminPassword3");

        credentials.put("user",usersPasswords);
        credentials.put("vipUser",vipUsersPasswords);
        credentials.put("admin",AdminsPasswords);

        ArrayList <String> passwords=credentials.get(user);

        String password="";
        for (String psw:passwords){
            if (psw.equals(pwd)){
                 password=psw;
            }
        }


        if (credentials.containsKey(user) && password.equals(pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", "user");
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            LoginServlet.positiveOut(response,user);
        } else {
            LoginServlet.negativeOut(response);
        }

    }
}

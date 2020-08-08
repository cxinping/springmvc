package com.dxtd.dbtest.servlet;

import com.dxtd.dbtest.cptest.CPTest2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CardServlet", urlPatterns = "/Card")
public class CardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String name = request.getParameter("name").trim();
        String cardNo = request.getParameter("cardNo").trim();

        int count = CPTest2.insertData(name, cardNo);
        if (count > 0) {
            out.print("inserted " + count + " record(s) successfully");

        }else {
            out.println("faild to insertd record.");
        }

        out.flush();
        out.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

        //http://localhost:8080/dbtest_1/Card?name=user01&cardNo=1100
    }
}

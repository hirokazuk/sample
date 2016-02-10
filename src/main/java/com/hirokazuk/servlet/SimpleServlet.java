package com.hirokazuk.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SimpleServlet"//servlet-name
    ,urlPatterns = { "/simple" }//servlet-mapping
        )
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        process(res);
    }

    private void process(final HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");
        res.addHeader("Cache-Control", "no-cache");
        res.addHeader("Pragma", "no-cache");
        res.addHeader("Expires", "0");
        
        final PrintWriter writer = res.getWriter();
        writer.print("<html><head></head><body>\n");
        writer.print("<p>Hello World!</p>\n");
        writer.print("</body></html>\n");
    }
}
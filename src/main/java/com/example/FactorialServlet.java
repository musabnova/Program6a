/*6a. Build a servlet program to find the factorial of a number using HTML with step by step procedure.*/

package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/factorial")
public class FactorialServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int number = Integer.parseInt(request.getParameter("number"));

            if (number < 0) {
                throw new IllegalArgumentException("Number cannot be negative");
            }
            if (number > 20) {
                throw new IllegalArgumentException("Maximum allowed is 20");
            }

            long factorial = 1;
            StringBuilder calculation = new StringBuilder();

            if (number == 0 || number == 1) {
                factorial = 1;
                calculation.append(number + "! = 1");
            } else {
                calculation.append(number + "! = ");
                for (int i = number; i >= 1; i--) {
                    factorial *= i;
                    calculation.append(i);
                    if (i > 1) {
                        calculation.append(" × ");
                    }
                }
                calculation.append(" = " + factorial);
            }

            // HTML RESPONSE
            out.println("<html>");
            out.println("<head><title>Factorial Result</title></head>");
            out.println("<body style='font-family: Arial; text-align: center;'>");

            out.println("<h2>Factorial Result</h2>");
            out.println("<p><b>Number:</b> " + number + "</p>");
            out.println("<p><b>Factorial:</b> " + factorial + "</p>");
            out.println("<p>" + calculation.toString() + "</p>");

            out.println("<a href='index.html'>Calculate Another</a>");

            out.println("</body>");
            out.println("</html>");

        } catch (NumberFormatException e) {
            displayError(out, "Please enter a valid number");
        } catch (IllegalArgumentException e) {
            displayError(out, e.getMessage());
        }
    }

    private void displayError(PrintWriter out, String message) {
        out.println("<html>");
        out.println("<head><title>Error</title></head>");
        out.println("<body style='text-align:center;'>");

        out.println("<h2 style='color:red;'>Error</h2>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='index.html'>Try Again</a>");

        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}
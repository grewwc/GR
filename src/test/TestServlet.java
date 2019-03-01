package test;

import input.g_input.CreatePythonScript;
import input.g_input.ProcessInput;
import input.g_input.RunPythonScript;
import utils.ToHTMLString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        final String projectDir = getInitParameter("projectDir");
//
//        CreatePythonScript create = new CreatePythonScript(projectDir,
//                new ArrayList<>() {{
//                    add("psi");
//                    add("theta");
//                    add("phi");
//                }});
//        create.setG(0, 0, "R**2 * cos(theta)**2");
//        create.setG(1, 1, "R**2");
//        create.setG(2, 2, "R**2 * sin(theta)**2");
//        create.writeAll();
//        create.ChristoffelSymbol();
//
//        RunPythonScript py = new RunPythonScript(projectDir);
//        byte[] res = py.run();
//        String data = new String(res, StandardCharsets.UTF_8);
//        data = ToHTMLString.to(data);
//        request.setAttribute("data", data.split("\n"));
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/test.jsp");
//        dispatcher.forward(request, response);
    }
}

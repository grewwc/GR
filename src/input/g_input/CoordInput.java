package input.g_input;

import utils.Information;
import utils.SerializeInformation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;


public class CoordInput extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coord = request.getParameter("coord").trim();
        String[] coords = coord.split("\\s+");
        var tmp = new ArrayList<>(new LinkedHashSet<>(Arrays.asList(coords)));
        coords = new String[tmp.size()];
        for (int i = 0; i < coords.length; i++) {
            coords[i] = tmp.get(i);
        }
        String projectDir = getInitParameter("projectDir");
        CreatePythonScript py = new CreatePythonScript(projectDir, Arrays.asList(coords));
        Information info = Information.getInstance(projectDir);
        info.setPy(py);

        SerializeInformation.write(Paths.get(projectDir, "info.ser").toString(), info);

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/input.jsp");
        request.setAttribute("dim", coords.length);
        request.setAttribute("coord", coords);
        System.out.println("here" + coord);
        dispatcher.forward(request, response);
    }
}

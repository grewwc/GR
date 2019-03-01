package input.g_input;

import utils.Information;
import utils.SerializeInformation;
import utils.ToHTMLString;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ProcessInputServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String basename = "b";
        String projectDir = getInitParameter("projectDir");
        List<Object> infos = SerializeInformation.read(Paths.get(projectDir, "info.ser").toString());

        Information info = (Information) infos.get(0);
        CreatePythonScript py = info.getPy();
        py.clearPyFile();
        int dim = py.getDim();
        String[][] g = new String[dim][dim];
        for (var i : IntStream.range(1, dim + 1).toArray()) {
            for (var j : IntStream.range(1, dim + 1).toArray()) {
                String name = basename + i + j;
                g[i - 1][j - 1] = request.getParameter(name);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/test.jsp");
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] == null || g[i][j].equals("")) {
                    if (i == j) g[i][j] = "1";
                    else g[i][j] = "0";
                }
            }
        }

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                py.setG(i, j, g[i][j]);
            }
        }
        py.writeAll();
        if (request.getParameter("ChristoffelSymbol") != null) {
            py.ChristoffelSymbol();
        }

        if (request.getParameter("RiemannTensor") != null) {
            py.RiemannTensor();
        }

        if (request.getParameter("RicciTensor") != null) {
            py.RicciTensor();
        }

        if (request.getParameter("RicciScalar") != null) {
            py.RicciScalar();
        }

        if (request.getParameter("EinsteinTensor") != null) {
            py.EinsteinTensor();
        }


        RunPythonScript run = new RunPythonScript(projectDir);
        byte[] res = run.run();
        String data = new String(res, StandardCharsets.UTF_8).trim();
//        if (data.equals("")) {
//            request.setAttribute("empty_result", true);
//        } else {
//            request.setAttribute("empty_result", false);
//        }
        System.out.println("final" + data);
        data = ToHTMLString.to(data);
        List<List<String>> dataSplitedTrimed = new ArrayList<>();
        for (String s : data.split("END")){
            List<String> eachTensor = new ArrayList<>();
            if(!s.trim().equals("")){
                for(String s1 : s.split("\n")){
                    if(!s1.trim().equals("")){
                        eachTensor.add(s1);
                    }
                }
            }
            dataSplitedTrimed.add(eachTensor);
        }
            request.setAttribute("data", dataSplitedTrimed);

        dispatcher.forward(request, response);
    }
}

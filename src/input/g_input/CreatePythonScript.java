package input.g_input;

import input.g_input.exceptions.DimensionsError;
import utils.SerializeInformation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

enum TensorKind {
    GeometricTensor("GeometricTensor"),
    ChristoffelSymbol("ChristoffelSymbol"),
    RiemannTensor("RiemannTensor"),
    RicciTensor("RicciTensor"),
    RicciScalar("RicciScalar"),
    EinsteinTensor("EinsteinTensor");

    final String name;

    TensorKind(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class CreatePythonScript implements Serializable {
    private static final long serialVersionUID = -3939051922592002782L;
    private String fname;
    private int dim = 3;
    static final String INSERT_AFTER = "insert";
    private final ProcessInput process;
    private String[][] g;
    private List<String> operands;
    private Set<String> allOperands = new LinkedHashSet<>();
    private StringBuilder setGij = new StringBuilder();

    public int getDim() {
        return dim;
    }

    public void clear() {
        operands = null;
        allOperands = new LinkedHashSet<>();
        setGij = new StringBuilder();
        clearPyFile();
    }

    public void clearPyFile() {
        write("", true);
    }

    public CreatePythonScript(String projDir, List<String> coord) {
        this.dim = coord.size();
        process = new ProcessInput();
        operands = coord;
        this.fname = Paths.get(projDir, "python", "test.py").toString();
        this.g = new String[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                g[i][j] = "1";
            }
        }
        allOperands.addAll(operands);
        write("", true);
    }

    private void writeOperands(List<String> allOperands) {
        StringBuilder sb = new StringBuilder();
        final String prefix = "sympy.Symbol('";
        for (String s : allOperands) {
            sb.append(s).append("=")
                    .append(prefix).append(s)
                    .append("'").append(")\n");
        }
        sb.append("g=").append(TensorKind.GeometricTensor)
                .append("(");
        for (int i = 0; i < operands.size(); i++) {
            sb.append(operands.get(i));
            if (i < operands.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")\n");
        sb.append(setGij);
        write(sb.toString(), false);
    }

    public void setG(int i, int j, String expr) {
        this.g[i][j] = expr;
        String content = "g[" + i + "," + j + "]=" + expr;
        setGij.append(content).append("\n");
        List<String> parsedOperands = process.extractOperands(expr);
        System.out.println("parsed: " + parsedOperands);
        allOperands.addAll(parsedOperands);
        if (this.operands.size() > dim) {
            throw new DimensionsError("dimension error");
        }
    }

    public void writeAll() {
        writeOperands(new ArrayList<>(allOperands));
    }

    private void create(TensorKind k) {
        final String content = k.name + "_instance = " + k.name + "(g)\nprint(" + k.name
                + "_instance)";
        write(content, false);
    }

    public void ChristoffelSymbol() {
        create(TensorKind.ChristoffelSymbol);
    }

    public void RiemannTensor() {
        create(TensorKind.RiemannTensor);
    }

    public void RicciTensor() {
        create(TensorKind.RicciTensor);
    }

    public void RicciScalar() {
        create(TensorKind.RicciScalar);
    }

    public void EinsteinTensor() {
        create(TensorKind.EinsteinTensor);
    }

    private void write(String content, boolean clear) {
        System.out.println("writting : " + content);
        StringBuilder sb = new StringBuilder();
        // first read, then write
        try (BufferedReader reader = new BufferedReader(new FileReader(fname, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(INSERT_AFTER)) {
                    if (clear) {
                        sb.append(line).append("\n");
                        break;
                    }
                }
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fname, StandardCharsets.UTF_8))) {
            sb.append(content);
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

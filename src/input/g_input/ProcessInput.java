package input.g_input;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class ProcessInput implements Serializable {
    private static final long serialVersionUID = -6802886147691372513L;

    private static final String PROGRAM_PATH = "C:\\Users\\User\\Google 云端硬盘\\GR\\gr.py";

    public ProcessInput() {
    }

    public List<String> extractOperands(String input) {
        String expr = input.trim();
        var tmpResult = expr.split("(\\+)|(\\*+)|(-)|(/)");
        var allTerms = Arrays.stream(tmpResult)
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
        Pattern betweenBrace = Pattern.compile("\\((.*)\\)");
        List<String> allOperands = new ArrayList<>();
        for (String term : allTerms) {
            term = term.trim();
            if (Character.isDigit(term.charAt(0))) {
                continue;
            }
            Matcher m = betweenBrace.matcher(term);
            if (m.find()) {
                String inner = m.group(1).trim();
                if(!Character.isDigit(inner.charAt(0)))
                    allOperands.add(inner);
            } else {
                allOperands.add(term.trim());
            }
        }

        return allOperands;
    }
}

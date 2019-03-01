import input.g_input.ProcessInput;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) throws Throwable {
        var res = gcd(100,24);
        System.out.println(res);
    }

    public static int gcd(int a, int b) {
        return gcd(b, a%b);
    }

}

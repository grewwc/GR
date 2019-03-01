package utils;

public class ToHTMLString {
    public static String to(String original){
        return original.replace("phi","&phi;")
                .replace("theta", "&theta;")
                .replace("psi", "&psi;")
                .replace("alpha","&alpha;")
                .replace("beta", "&beta;")
                .replace("sigma", "&sigma;")
                .replace(" ", "&nbsp;");
    }
}

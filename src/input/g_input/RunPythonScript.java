package input.g_input;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunPythonScript {
    private final String projectDir;

    public RunPythonScript(String projectDir) {
        this.projectDir = projectDir;
    }

    public byte[] run() {
        ProcessBuilder pb = new ProcessBuilder();
        String pythonPath = Paths.get(this.projectDir, "python", "test.py").toString();
        pb.command("python", pythonPath);
        try {
            Process p = pb.start();
            return convert(getStringFromStream(p.getInputStream(), p.getErrorStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Byte> getStringFromStream(InputStream input, InputStream err) {
        final int _8MB = 8 * 1024 * 1024;
        byte[] buffer = new byte[_8MB];
        List<Byte> res = new ArrayList<>();
        int readLen = buffer.length;
        StringBuilder sbErr = new StringBuilder();
        try {
            try (BufferedInputStream in = new BufferedInputStream(input);
                 BufferedInputStream errIn = new BufferedInputStream(err)) {
                while ((readLen = in.read(buffer)) != -1) {
                    res.addAll(Arrays.asList(
                            ArrayUtils.toObject(Arrays.copyOfRange(buffer, 0, readLen))));
                }

                Arrays.fill(buffer, (byte) 0);
                while ((readLen = errIn.read(buffer)) != -1) {
                    sbErr.append(new String(Arrays.copyOfRange(buffer, 0, readLen)));
                }
                if (sbErr.length() != 0) {
                    System.out.println(sbErr.toString());
                }
            }
            return res;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static byte[] convert(List<Byte> data){
        if(data == null){
            return new byte[0];
        }
        byte[] res= new byte[data.size()];
        for(int i=0; i<res.length; i++){
            res[i] = data.get(i);
        }
        return res;
    }

}

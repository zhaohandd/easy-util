package zju.edu.als.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author imddon
 * @create 2019-09-13 23:04
 */
public class ProcessUtil {
    private static Logger log = LoggerFactory.getLogger(ProcessUtil.class);

    public static String getResultFrom(Process process, boolean onlyLastLine) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuffer sb = new StringBuffer();
        String text = "";
        while ((line = in.readLine()) != null) {
            sb.append(line.trim()).append(" ");
            text = line;
        }
        if (onlyLastLine) {
            return text.trim();
        }
        return sb.toString().trim();
    }

    public static JSONObject getResultFrom(Process process) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String text = "";
        while ((line = in.readLine()) != null) {
            text = line;
            log.info(text);
        }
        return JSONObject.parseObject(text.trim());
    }

    public static boolean isError(Process process) throws IOException {
        return getError(process) != 0;
    }

    public static int getError(Process process) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        int sum = 0;
        while ((line = in.readLine()) != null) {
            log.error(line);
            sum += line.length();
        }
        return sum;
    }
}

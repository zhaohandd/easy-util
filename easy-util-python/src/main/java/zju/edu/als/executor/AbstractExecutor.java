package zju.edu.als.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zju.edu.als.execption.CodeNotZeroException;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.execption.OtherException;
import zju.edu.als.execption.ProcessErrorException;
import zju.edu.als.handler.Handler;
import zju.edu.als.util.ProcessUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author imddon
 * @create 2019-09-13 20:26
 */
public abstract class AbstractExecutor implements Executor {

    private static Logger log = LoggerFactory.getLogger(AbstractExecutor.class);

    AbstractExecutor() {
    }

    @Override
    public String execute(String... cmd) throws ExecuteException {
        return execute(true, cmd);
    }

    @Override
    public <T> T execute(Handler<T> handler, String... cmd) throws ExecuteException {
        String text = execute(false, cmd);
        return handler.handle(text);
    }

    String execute(boolean onlyLastLine, String... cmd) throws ExecuteException {
        int code;
        long time = System.currentTimeMillis();
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);
            code = process.waitFor();
        } catch (IOException | InterruptedException e) {
            log.error("", e);
            throw new OtherException();
        }
        String text;
        try {
            if (ProcessUtil.isError(process)) {
                throw new ProcessErrorException(cmd[1]);
            }
            text = ProcessUtil.getResultFrom(process, onlyLastLine);
        } catch (IOException e) {
            throw new ProcessErrorException();
        }
        if (code != 0) {
            throw new CodeNotZeroException();
        }
        time = System.currentTimeMillis() - time;
        log.debug("执行器={} 标识={} 耗时={}ms", cmd[0], cmd[1], time);
        return text;
    }

    protected String[] complete(String... cmd) {
        List<String> list = new ArrayList<>();
        list.add(name());
        for (int i = 0; i < cmd.length; i++) {
            list.add(cmd[0]);
        }
        String[] temp = new String[list.size()];
        return list.toArray(temp);
    }

    protected String[] complete(String file, String[] args) {
        List<String> list = new ArrayList<>();
        list.add(name());
        list.add(file);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                list.add(args[0]);
            }
        }
        String[] temp = new String[list.size()];
        return list.toArray(temp);
    }
}

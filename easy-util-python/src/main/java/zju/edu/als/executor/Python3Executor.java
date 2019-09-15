package zju.edu.als.executor;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.util.Executors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author imddon
 * @create 2019-09-13 20:13
 */
public final class Python3Executor extends AbstractExecutor {
    private static Logger log = LoggerFactory.getLogger(Python3Executor.class);

    private Python3Executor() {
        super();
    }

    static {
        log.debug("注册 {}", Python3Executor.getInstance().name());
    }

    @Override
    public String name() {
        return Executors.PYTHON3.getId();
    }

    private static class Holder {
        private static Python3Executor instance = new Python3Executor();
    }

    public static Python3Executor getInstance() {
        return Holder.instance;
    }

    public <T> T execute(Class<T> resType, String... cmd) throws ExecuteException {
        String text = execute(complete(cmd));
        JSONObject jsonObject = JSONObject.parseObject(text.trim());
        return jsonObject.toJavaObject(resType);
    }

    public String execute(String srcPath, String[] args) throws ExecuteException {
        return execute(complete(srcPath, args));
    }
}

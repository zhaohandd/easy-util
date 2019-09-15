package zju.edu.als.executor;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.util.Executors;


/**
 * @author imddon
 * @create 2019-09-13 20:00
 */
public final class Python2Executor extends AbstractExecutor {

    private Python2Executor() {
        super();
    }

    private static Logger log = LoggerFactory.getLogger(Python2Executor.class);

    static {
        log.debug("注册 {}", Python2Executor.getInstance().name());
    }

    @Override
    public String name() {
        return Executors.PYTHON.getId();
    }

    private static class Holder {
        private static Python2Executor instance = new Python2Executor();
    }

    public static Python2Executor getInstance() {
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

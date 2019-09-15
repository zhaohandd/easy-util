package zju.edu.als.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zju.edu.als.execption.ExecutorNotFoundExecption;
import zju.edu.als.executor.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author imddon
 * @create 2019-09-13 19:58
 */
public final class DefaultExecutorFactory {

    private static Logger log = LoggerFactory.getLogger(DefaultExecutorFactory.class);
    private static final ConcurrentHashMap<Class, Executor> EXECUTORS = new ConcurrentHashMap<>();

    static {
        DefaultExecutorFactory.register(Python3Executor.class);
        DefaultExecutorFactory.register(Python2Executor.class);
        DefaultExecutorFactory.register(GeneralExecutor.class);
    }

    private DefaultExecutorFactory() {
    }

    public static <T extends AbstractExecutor> T getExecutor(Class<T> type) throws ExecutorNotFoundExecption {
        if (!EXECUTORS.containsKey(type)) {
            log.error("不存在 {}", type.getName());
            throw new ExecutorNotFoundExecption();
        }
        return (T) EXECUTORS.get(type);
    }

    private static <T extends AbstractExecutor> void register(Class<T> type) {
        Method getInstance;
        AbstractExecutor executor = null;
        try {
            getInstance = type.getMethod("getInstance");
            executor = (AbstractExecutor) getInstance.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("", e);
        }
        if (executor != null) {
            EXECUTORS.put(type, executor);
        }
    }

}

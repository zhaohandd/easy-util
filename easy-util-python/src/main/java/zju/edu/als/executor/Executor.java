package zju.edu.als.executor;

import zju.edu.als.execption.ExecuteException;
import zju.edu.als.handler.Handler;

/**
 * @author imddon
 * @create 2019-09-13 20:00
 */
public interface Executor {

    String name();

    /**
     * 默认返回最后一行结果
     * @param cmd
     * @return
     * @throws ExecuteException
     */
    String execute(String... cmd) throws ExecuteException;

    /**
     * 对返回结果不处理，交由handler来处理
     * @param handler
     * @param cmd
     * @param <T>
     * @return
     * @throws ExecuteException
     */
    <T> T execute(Handler<T> handler, String... cmd) throws ExecuteException;

}

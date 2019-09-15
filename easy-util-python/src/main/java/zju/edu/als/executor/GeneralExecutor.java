package zju.edu.als.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zju.edu.als.util.Executors;

/**
 * @author imddon
 * @create 2019-09-13 22:56
 */
public final class GeneralExecutor extends AbstractExecutor {

    private GeneralExecutor() {
        super();
    }

    private static Logger log = LoggerFactory.getLogger(GeneralExecutor.class);

    static {
        log.debug("注册 {}", GeneralExecutor.getInstance().name());
    }

    @Override
    public String name() {
        return Executors.GENERAL.getId();
    }

    private static class Holder {

        private static GeneralExecutor instance = new GeneralExecutor();
    }

    public static GeneralExecutor getInstance() {
        return GeneralExecutor.Holder.instance;
    }
}

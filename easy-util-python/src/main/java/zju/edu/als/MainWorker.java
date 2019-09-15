package zju.edu.als;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zju.edu.als.execption.ExecuteException;
import zju.edu.als.execption.ExecutorNotFoundExecption;
import zju.edu.als.executor.GeneralExecutor;
import zju.edu.als.executor.Python2Executor;
import zju.edu.als.factory.DefaultExecutorFactory;
import zju.edu.als.model.Test;


/**
 * @author imddon
 * @create 2019-09-13 17:31
 */
public class MainWorker {
    private static Logger log = LoggerFactory.getLogger(MainWorker.class);

    private static final String HOME = "/Users/imddon/alsprj/";

    public static void main(String[] args) throws ExecutorNotFoundExecption, ExecuteException {
        GeneralExecutor general = DefaultExecutorFactory.getExecutor(GeneralExecutor.class);
        /*
            特定执行器
         */
        Python2Executor python2 = DefaultExecutorFactory.getExecutor(Python2Executor.class);
        Test test = python2.execute(Test.class, HOME + "hello.py", null);
        log.info("将执行输出的json信息反序列化成java对象 {}.data = {}", test, test.getData());
        String s3 = python2.execute(HOME + "hello.py", null);
        log.info("取执行输出最后一行{}", s3);

        /*
            通用执行器
         */
        String s = general.execute("cat", HOME + "hello.py");
        log.info("取执行输出最后一行 {}", s);
        String s1 = general.execute(text -> "handler:" + text, "cat", HOME + "hello.py");
        log.info("取执行输出所有行，通过handler进行处理 {}", s1);

    }
}

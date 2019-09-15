package zju.edu.als.execption;

/**
 * @author imddon
 * @create 2019-09-13 19:12
 */
public class ProcessErrorException extends ExecuteException {
    public ProcessErrorException() {}
    public ProcessErrorException(String msg){
        super(msg);
    }
}

package zju.edu.als.execption;

/**
 * @author imddon
 * @create 2019-09-13 19:06
 */
public class CodeNotZeroException extends ExecuteException {
    public CodeNotZeroException() {

    }
    public CodeNotZeroException(String msg) {
        super(msg);
    }
}

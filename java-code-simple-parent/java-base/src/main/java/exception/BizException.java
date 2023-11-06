package exception;

/**
 * @author caochaojie
 * 2022/12/5
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }
}

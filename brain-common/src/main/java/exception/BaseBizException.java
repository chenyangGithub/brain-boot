package exception;

import lombok.Data;

@Data
public class BaseBizException extends Exception {
    private String errorCode;

    public BaseBizException(String errorCode, String errorMsg, Throwable cause){
        super(errorMsg, cause);
        this.errorCode=errorCode;
    }

    public BaseBizException(String errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode=errorCode;
    }
}

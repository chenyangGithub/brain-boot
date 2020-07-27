package base;

import lombok.Data;

@Data
public class BaseResult<T> {
    private String code = ResponseEnum.SUCCESS.getCode();

    private String msg = ResponseEnum.SUCCESS.getMsg();

    private T t;


}

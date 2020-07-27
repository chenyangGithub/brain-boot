package base;

public enum ResponseEnum {
    SUCCESS("200", "成功"),
    PARAM_NOT_NULL("1001", "参数不能为空");

    private String code;
    private String msg;

    ResponseEnum(String code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}

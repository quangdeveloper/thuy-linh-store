package vn.free.register.constant;

/**
 * @author quangnv
 */
public enum TranStatusCode {

    INIT(1, "Init"),
    WAIT(2, "Waiting"),
    PAYMENT(3, "Payment"),
    DELETE(4, "Delete"),
    ;

    private final int code;
    private final String desc;

    TranStatusCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){ return desc; }

}

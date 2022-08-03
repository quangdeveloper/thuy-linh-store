package vn.free.register.constant;

/**
 * @author quangnv
 */
public enum StatusCode {

    ACTIVE(1, "Active"),
    UN_ACTIVE(2, "UnActive"),
    DELETE(3, "Delete"),
    ;

    private final int code;
    private final String desc;

    StatusCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){ return desc; }

}

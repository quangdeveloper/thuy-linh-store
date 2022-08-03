package vn.free.register.constant;

/**
 * @author quangnv
 */
public enum  ResponseCode {

    SUCCESS("00", "Thực hiện thành công."),
    CREATE_SUCCESS("01", "Thêm mới OBJ thành công."),
    UPDATE_SUCCESS("02", "Cập nhật OBJ thành công."),

    INVALID_DATA("05", "Dữ liệu OBJ không hợp lệ."),
    EXISTS("07", "Thông tin OBJ đã tồn tại trong hệ thống."),
    NOT_EXISTS("09", "Thông tin OBJ không tồn tại trong hệ thống."),

    CREATE_FAIL("90", "Lỗi hệ thống. Thêm mới OBJ thất bại. Vui lòng thử lại."),
    ERROR("99", "Xảy ra lỗi hệ thống khi xử lí. Vui lòng thử lại."),
    LOGIN_FAIL("100", "Thông tin người dùng không hợp lệ."),
    ;

    private final String code;
    private final String desc;

    ResponseCode(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode(){
        return code;
    }

    public String getDesc(){ return desc; }

    public String getDesc(String str){ return desc.replace("OBJ", str); }

}

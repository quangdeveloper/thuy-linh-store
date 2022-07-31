package vn.free.register.constant;

/**
 * @author quangnv
 */
public enum  ResponseCode {

    SUCCESS("00", "Thực hiện thành công."),
    CREATE_SUCCESS("01", "Thêm mới thành công."),
    UPDATE_SUCCESS("02", "Cập nhật thành công."),
    INVALID_DATA("05", "Dữ liệu không hợp lệ."),
    NOT_EXISTS("09", "Thông tin không tồn tại trong hệ thống."),
    CREATE_FAIL("90", "Lỗi hệ thống. Vui lòng thử lại."),
    ERROR("99", "Lỗi hệ thống. Vui lòng thử lại."),
    LOGIN_FAIL("100", "Thông tin tài khoản không hợp lệ.");

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

}

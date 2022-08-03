package vn.free.register.request.request_txn;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class RequestTxnRQ {

    private Long id;
    private String customerMobile;
    private String customerName;
    private String dateRequest;  //format: "yyyy-mm-dd hh:mm:ss"
    private Integer status;

}

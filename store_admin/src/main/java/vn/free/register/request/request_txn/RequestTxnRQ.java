package vn.free.register.request.request_txn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class RequestTxnRQ {

    private Long id;

    @JsonProperty("customer_mobile")
    private String customerMobile;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("date_req")
    private String dateRequest;  //format: "yyyy-mm-dd hh:mm:ss"

    private Integer status;

}

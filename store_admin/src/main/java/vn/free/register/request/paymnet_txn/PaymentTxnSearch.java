package vn.free.register.request.paymnet_txn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PaymentTxnSearch {

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("cus_id")
    private Long cusId;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("from_date")
    private String fromDate;

    @JsonProperty("to_date")
    private String toDate;

}

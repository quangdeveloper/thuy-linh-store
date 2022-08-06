package vn.free.register.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ProductSearch {

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("from_date")
    private String fromDate;  //format:  YYYY-MM-DD

    @JsonProperty("to_date")  //format:  YYYY-MM-DD
    private String toDate;

}

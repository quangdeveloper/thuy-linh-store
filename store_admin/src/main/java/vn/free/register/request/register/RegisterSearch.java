package vn.free.register.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegisterSearch {

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("keyword")
    private String keyword;

    private Integer status;

    @JsonProperty("from_date")
    private String fromDate;

    @JsonProperty("to_date")
    private String toDate;

    @Override
    public String toString() {
        return "pageNo: " + pageSize
                + "\t pageSize: " + pageSize
                + "\t keyword: " + keyword;
    }

}

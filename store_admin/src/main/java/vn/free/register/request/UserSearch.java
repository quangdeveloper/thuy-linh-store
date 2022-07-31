package vn.free.register.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserSearch {

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("status")
    private Integer status;

    public String toString(){
        return "pageNo: " + pageIndex
                + "\t pageSize: " + pageSize
                + "\t keyword: " + keyword;
    }

}

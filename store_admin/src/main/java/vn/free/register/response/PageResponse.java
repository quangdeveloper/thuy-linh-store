package vn.free.register.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;

@Builder
@Data
public class PageResponse {

    @JsonProperty("data")
    @Builder.Default
    private Object data = Collections.EMPTY_LIST;

    private long total;
}

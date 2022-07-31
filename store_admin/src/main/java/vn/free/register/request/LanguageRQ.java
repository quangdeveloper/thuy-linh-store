package vn.free.register.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LanguageRQ {

    @JsonProperty("name")
    private String name;

    @JsonProperty("listening")
    private Integer listening;

    @JsonProperty("speaking")
    private Integer speaking;

    @JsonProperty("reading")
    private Integer reading;

    @JsonProperty("writing")
    private Integer writing;

}

package vn.free.register.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActionRes {

    private String code;
    private String message;

}

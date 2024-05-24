package awa.training.api.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseErrorResponse {
    private Object statusCode;
    private Object messageTh;
    private Object messageEn;
    private Object detail;
}

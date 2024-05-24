package awa.training.api.common.model;

import awa.training.api.common.StatusResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonPaginationResponse<T> {
    @Builder.Default
    protected String statusCode = StatusResponseCode.SUCCESS.getCode();
    @Builder.Default
    protected String message = StatusResponseCode.SUCCESS.getMessage();
    protected T data;
    protected Pagination pagination;
}

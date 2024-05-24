package awa.training.api.common.model;

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
public class Pagination {
    @Builder.Default
    private int page = 0;
    @Builder.Default
    private int length = 0;
    @Builder.Default
    private int totalPages = 0;
    @Builder.Default
    private long totalData = 0;

}

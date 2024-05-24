package awa.training.api.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationRequest {
    @Builder.Default
    private int page = 0;
    @Builder.Default
    private int length = 0;
    @Builder.Default
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    @Builder.Default
    private String sortProperty = "id";


}

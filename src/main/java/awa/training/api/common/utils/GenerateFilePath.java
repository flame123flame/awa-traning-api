package awa.training.api.common.utils;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.UtilityClass;


@UtilityClass
public class GenerateFilePath {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class FileSave {
        private String fileSaveBase;
        private String fileSaveDevice;
    }
}

package awa.training.api.feature.exam_scores.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamScoresDTO {

    @Getter
    @Setter
    public static class CreateExamScoresReq {
        private Long enrollmentId;
        private float midtermScore;
        private float finalScore;
        private float sumScore;
        private String semester;
        private String createBy;
    }

    @Getter
    @Setter
    public static class FindAllExamScoresRes {
        private Long id;
        private Long enrollmentId;
        private float midtermScore;
        private float finalScore;
        private float sumScore;
        private String semester;
        private String createBy;
        private String updateBy;
    }

    @Getter
    @Setter
    public static class UpdateExamScoresRes {
        private Long id;
        private Long enrollmentId;
        private float midtermScore;
        private float finalScore;
        private float sumScore;
        private String semester;
        private String updateBy;
    }

    @Getter
    @Setter
    public static class DeleteExamScoresReq {
        private Long id;
    }
}

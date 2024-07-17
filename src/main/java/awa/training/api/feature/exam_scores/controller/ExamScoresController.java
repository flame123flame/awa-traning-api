package awa.training.api.feature.exam_scores.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.exam_scores.dto.ExamScoresDTO;
import awa.training.api.feature.exam_scores.service.ExamScoresService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/exam-scores")
public class ExamScoresController {

    private final ExamScoresService examScoresService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse<Object>> create(@Valid @RequestBody ExamScoresDTO.CreateExamScoresReq dto) {
        return ResponseEntity.ok(examScoresService.create(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<CommonResponse<ExamScoresDTO.UpdateExamScoresRes>> update(@Valid @RequestBody ExamScoresDTO.UpdateExamScoresRes id ) {
        return ResponseEntity.ok(examScoresService.update(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<CommonResponse<List<ExamScoresDTO.FindAllExamScoresRes>>> findAll() {
        return ResponseEntity.ok(examScoresService.findAll());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<CommonResponse<ExamScoresDTO.FindAllExamScoresRes>> findById(@Valid @RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(examScoresService.findById(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponse<ExamScoresDTO.DeleteExamScoresReq>> delete(@Valid @RequestBody ExamScoresDTO.DeleteExamScoresReq id) {
        return ResponseEntity.ok(examScoresService.delete(id));
    }
}

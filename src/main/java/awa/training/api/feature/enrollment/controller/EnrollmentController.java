package awa.training.api.feature.enrollment.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.enrollment.dto.EnrollmentDTO;
import awa.training.api.feature.enrollment.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse<Object>> create(@Valid @RequestBody EnrollmentDTO.CreateEnrollmentReq req) {
        return ResponseEntity.ok(enrollmentService.create(req));
    }

    @PutMapping("/update}")
    public ResponseEntity<CommonResponse<EnrollmentDTO.UpdateEnrollmentRes>> update(@Valid @RequestBody EnrollmentDTO.UpdateEnrollmentRes req) {
        return ResponseEntity.ok(enrollmentService.update(req));
    }

    @GetMapping("/find-all")
    public ResponseEntity<CommonResponse<List<EnrollmentDTO.FindAllEnrollmentRes>>> findAll() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<CommonResponse<EnrollmentDTO.FindAllEnrollmentRes>> findById(@Valid @RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(enrollmentService.findById(id));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponse<EnrollmentDTO.DeleteEnrollmentReq>> delete(@Valid  @RequestBody EnrollmentDTO.DeleteEnrollmentReq id) {
        return ResponseEntity.ok(enrollmentService.delete(id));
    }
}

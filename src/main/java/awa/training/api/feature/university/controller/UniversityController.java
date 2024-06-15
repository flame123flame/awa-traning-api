package awa.training.api.feature.university.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.university.dto.UniversityDTO;
import awa.training.api.feature.university.service.UniversityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityService universityService;

    @PostMapping("/z")
    public ResponseEntity<CommonResponse<Object>> create(@Valid @RequestBody UniversityDTO.CreateUniversityReq dto) {
        return ResponseEntity.ok(universityService.create(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<CommonResponse<UniversityDTO.UpdateUniversityRes>> update(@Valid @RequestBody UniversityDTO.UpdateUniversityRes id ) {
        return ResponseEntity.ok(universityService.update(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<CommonResponse<List<UniversityDTO.FindAllUniversityRes>>> getFindAll() {
        return ResponseEntity.ok(universityService.findAll());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<CommonResponse<UniversityDTO.FindAllUniversityRes>> getFindById(@Valid @RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(universityService.findById(id));
    }

    @PostMapping("/delete")
    public ResponseEntity<CommonResponse<UniversityDTO.DeleteUniversityReq>> deleteUser(@Valid @RequestBody UniversityDTO.DeleteUniversityReq id) {
        return ResponseEntity.ok(universityService.delete(id));
    }
}
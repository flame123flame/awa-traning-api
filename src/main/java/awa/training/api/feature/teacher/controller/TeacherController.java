package awa.training.api.feature.teacher.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.teacher.dto.TeacherDTO;
import awa.training.api.feature.teacher.service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/Create")
    public ResponseEntity<CommonResponse<Object>> Create(@Valid @RequestBody TeacherDTO.CreateTeacherReq dto) {
        return ResponseEntity.ok(teacherService.create(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<CommonResponse<TeacherDTO.UpdateTeacherRes>> updateUser(@Valid @RequestBody TeacherDTO.UpdateTeacherRes id ) {
        return ResponseEntity.ok(teacherService.update(id));
    }


    @GetMapping("/find-all")
    public ResponseEntity<CommonResponse<List<TeacherDTO.FindAllTeacherRes>>> getFindAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<CommonResponse<TeacherDTO.FindAllTeacherRes>> getFindById(@Valid @RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @PostMapping("/delete")
    public ResponseEntity<CommonResponse<TeacherDTO.DeleteTeacherReq>> deleteUser(@Valid @RequestBody TeacherDTO.DeleteTeacherReq id) {
        return ResponseEntity.ok(teacherService.delete(id));
    }


}

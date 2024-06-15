package awa.training.api.feature.student.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.student.dto.StudentDTO;
import awa.training.api.feature.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse<Object>> create(@Valid @RequestBody StudentDTO.CreateStudentReq dto) {
        return ResponseEntity.ok(studentService.create(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<CommonResponse<StudentDTO.UpdateStudentRes>> update(@Valid @RequestBody StudentDTO.UpdateStudentRes id ) {
        return ResponseEntity.ok(studentService.update(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<CommonResponse<List<StudentDTO.FindAllStudentRes>>> getFindAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<CommonResponse<StudentDTO.FindAllStudentRes>> getFindById(@Valid @RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping("/delete")
    public ResponseEntity<CommonResponse<StudentDTO.DeleteStudentReq>> deleteUser(@Valid @RequestBody StudentDTO.DeleteStudentReq id) {
        return ResponseEntity.ok(studentService.delete(id));
    }
}
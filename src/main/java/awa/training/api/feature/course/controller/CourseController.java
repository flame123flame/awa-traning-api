//package awa.training.api.feature.course.controller;
//
//public class CourseController {
//}

package awa.training.api.feature.course.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.course.dto.CourseDTO;
import awa.training.api.feature.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse<Object>> create(@Valid @RequestBody CourseDTO.CreateCourseReq dto) {
        return ResponseEntity.ok(courseService.create(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<CommonResponse<CourseDTO.UpdateCourseRes>> update(@Valid @RequestBody CourseDTO.UpdateCourseRes dto) {
        return ResponseEntity.ok(courseService.update(dto));
    }

    @GetMapping("/find-all")
    public ResponseEntity<CommonResponse<List<CourseDTO.FindAllCourseRes>>> getFindAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<CommonResponse<CourseDTO.FindAllCourseRes>> getFindById(@Valid @RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @PostMapping("/delete")
    public ResponseEntity<CommonResponse<CourseDTO.DeleteCourseReq>> delete(@Valid @RequestBody CourseDTO.DeleteCourseReq dto) {
        return ResponseEntity.ok(courseService.delete(dto));
    }
}

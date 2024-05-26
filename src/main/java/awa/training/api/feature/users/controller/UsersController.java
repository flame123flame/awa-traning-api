package awa.training.api.feature.users.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.users.dto.UsersDTO;
import awa.training.api.feature.users.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<Object>> register(@Valid @RequestBody UsersDTO.RegisterReq dto) {
        return ResponseEntity.ok(usersService.register(dto));
    }

    @GetMapping("/find-all")
    public ResponseEntity<CommonResponse<List<UsersDTO.FindAllUserRes>>> getFindAll() {
        return ResponseEntity.ok(usersService.findAllUser());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<CommonResponse<UsersDTO.FindAllUserRes>> getFindById(@Valid @RequestBody Long id) {
        return ResponseEntity.ok(usersService.findByIdUser(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponse<UsersDTO.DeleteUserRes>> deleteUser(@Valid @RequestParam Long id) {
        return ResponseEntity.ok(usersService.deleteUser(id));
    }

    // @PostMapping("/update")
    // public ResponseEntity<CommonResponse<UsersDTO.UpdateUserRes>> updateUser(@RequestParam Long id, @Valid @RequestBody UsersDTO.RegisterReq dto) {
    //     return ResponseEntity.ok(usersService.updateUser(id, dto));
    // }

}

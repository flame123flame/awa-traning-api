package awa.training.api.feature.users.controller;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.users.dto.UsersDTO;
import awa.training.api.feature.users.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<Object>> register(@Valid @RequestBody UsersDTO.RegisterReq dto) {
        return ResponseEntity.ok(usersService.register(dto));
    }


}

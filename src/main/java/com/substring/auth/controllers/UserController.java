package com.substring.auth.controllers;

import com.substring.auth.dtos.ApiResponse;
import com.substring.auth.dtos.UserDto;
import com.substring.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.updateUser(userDto, userId.toString()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getById(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.getUserById(userId.toString()));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) throws Exception {
        userService.deleteUser(userId.toString());
        return ResponseEntity.ok(new ApiResponse("User deleted successfully"));
    }
}

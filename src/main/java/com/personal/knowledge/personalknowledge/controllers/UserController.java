package com.personal.knowledge.personalknowledge.controllers;

import com.personal.knowledge.personalknowledge.controllers.constants.SwaggerApiConstants;
import com.personal.knowledge.personalknowledge.controllers.entities.UserRequest;
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse;
import com.personal.knowledge.personalknowledge.entities.User;
import com.personal.knowledge.personalknowledge.usecases.CreateUser;
import com.personal.knowledge.personalknowledge.usecases.GetUserById;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Controller using Spring validation with ExceptionHandler and javax validator
 */
@Validated
@RestController
@Api(value = "/v1/user", tags = {"User"})
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private GetUserById getUserById;

    @Autowired
    private CreateUser createUser;

    @GetMapping
    public ResponseEntity<String> getUsersNames(
        @ApiParam(value = "User name to search", required = true, example = "John") @RequestParam(name = "name") final String name,
        @ApiParam(value = "User age to search", required = true, example = "18") @RequestParam(name = "age") @Min(value = 18) @Max(value = 200) final int age) {
        return ResponseEntity.ok(name);
    }

    @ApiResponses(value = {
        @ApiResponse(code = SwaggerApiConstants.OK, message = "Success found user by given ID", response = User.class),
        @ApiResponse(code = SwaggerApiConstants.BAD_REQUEST, message = "Bad request error. A response will be returned with error information", response = ErrorResponse.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable final int id) {
        return ResponseEntity.ok(getUserById.execute(id));
    }

    @PostMapping
    public void createUser(@RequestBody @Valid final UserRequest userRequest) {
        createUser.execute(userRequest);
    }

}
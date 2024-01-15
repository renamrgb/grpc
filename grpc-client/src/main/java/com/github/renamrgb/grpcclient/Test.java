package com.github.renamrgb.grpcclient;

import com.github.renamrgb.proto.lib.UserResponse;
import com.github.renamrgb.proto.lib.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class Test {

    @GrpcClient("userService")
    private UserServiceGrpc.UserServiceBlockingStub userService;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        UserResponse user = userService.createUser(com.github.renamrgb.proto.lib.CreateUserRequest.newBuilder()
                .setName(request.name())
                .build());
        return new CreateUserResponse(user.getName(), user.getId());
    }

    record CreateUserRequest(String name) {
    }

    record CreateUserResponse(String name, String id) {
    }
}

package com.github.renamrgb.grpcclient;

import com.github.renamrgb.proto.lib.CreateUserRequest;
import com.github.renamrgb.proto.lib.UserResponse;
import com.github.renamrgb.proto.lib.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GrpcClientApplicationTests {

    @GrpcClient("userService")
    private UserServiceGrpc.UserServiceBlockingStub userService;

    @Test
    void test() {
        CreateUserRequest createUserRequest = CreateUserRequest.newBuilder()
                .setName("John")
                .build();
        UserResponse userResponse = userService.createUser(createUserRequest);
        assertEquals(createUserRequest.getName(), userResponse.getName());
        assertNotNull(userResponse.getId());
    }
}


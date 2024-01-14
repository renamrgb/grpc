package com.github.renamrgb.grpcserver;

import com.github.renamrgb.proto.lib.CreateUserRequest;
import com.github.renamrgb.proto.lib.UpdateUserRequest;
import com.github.renamrgb.proto.lib.UserResponse;
import com.github.renamrgb.proto.lib.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        LOG.info("Request de Create User");
        UserResponse userResponse = UserResponse.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setName(request.getName())
                .build();
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(UpdateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        super.updateUser(request, responseObserver);
    }
}

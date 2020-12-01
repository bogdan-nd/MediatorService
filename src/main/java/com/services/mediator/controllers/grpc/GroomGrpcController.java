package com.services.mediator.controllers.grpc;

import com.services.grpc.server.groom.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GroomGrpcController extends GroomServiceGrpc.GroomServiceImplBase {

    @Override
    public void showGrooms(GroomEmpty request, StreamObserver<GroomResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();

        GroomServiceGrpc.GroomServiceBlockingStub stub = GroomServiceGrpc.newBlockingStub(channel);
        GroomResponse response = stub.showGrooms(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void showGroomById(GroomIdRequest request, StreamObserver<GroomResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();

        GroomServiceGrpc.GroomServiceBlockingStub stub = GroomServiceGrpc.newBlockingStub(channel);
        GroomResponse response = stub.showGroomById(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addGroom(GroomRequest request, StreamObserver<GroomResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();

        GroomServiceGrpc.GroomServiceBlockingStub stub = GroomServiceGrpc.newBlockingStub(channel);
        GroomResponse response = stub.addGroom(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteGroom(GroomIdRequest request, StreamObserver<GroomEmpty> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();

        GroomServiceGrpc.GroomServiceBlockingStub stub = GroomServiceGrpc.newBlockingStub(channel);
        GroomEmpty response = stub.deleteGroom(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

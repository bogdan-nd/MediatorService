package com.services.mediator.controllers.grpc;

import com.services.grpc.server.club.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class ClubGrpcController extends ClubServiceGrpc.ClubServiceImplBase {

    @Override
    public void getClub(ClubEmpty request, StreamObserver<ClubResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clubservice", 9090)
                .usePlaintext()
                .build();

        ClubServiceGrpc.ClubServiceBlockingStub stub = ClubServiceGrpc.newBlockingStub(channel);
        ClubResponse response = stub.getClub(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addClub(CreateClubRequest request, StreamObserver<ClubResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clubservice", 9090)
                .usePlaintext()
                .build();

        ClubServiceGrpc.ClubServiceBlockingStub stub = ClubServiceGrpc.newBlockingStub(channel);
        ClubResponse response = stub.addClub(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void spendMoney(ClubMoneyRequest request, StreamObserver<ClubStringResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clubservice", 9090)
                .usePlaintext()
                .build();

        ClubServiceGrpc.ClubServiceBlockingStub stub = ClubServiceGrpc.newBlockingStub(channel);
        ClubStringResponse response = stub.spendMoney(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void earnMoney(ClubMoneyRequest request, StreamObserver<ClubStringResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clubservice", 9090)
                .usePlaintext()
                .build();

        ClubServiceGrpc.ClubServiceBlockingStub stub = ClubServiceGrpc.newBlockingStub(channel);
        ClubStringResponse response = stub.earnMoney(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

package com.services.mediator.controllers.grpc;

import com.services.grpc.server.horse.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HorseGrpcController extends HorseServiceGrpc.HorseServiceImplBase {

    @Override
    public void getHorses(HorseEmpty request, StreamObserver<HorseResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);
        HorseResponse response = stub.getHorses(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getHorseById(HorseIdRequest request, StreamObserver<HorseResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);
        HorseResponse response = stub.getHorseById(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getSuitableHorse(HorsemanRequest request, StreamObserver<HorseResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);
        HorseResponse response = stub.getSuitableHorse(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addHorse(HorseRequest request, StreamObserver<HorseResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);
        HorseResponse response = stub.addHorse(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void feedHorse(HorseIdRequest request, StreamObserver<HorseStringResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);
        HorseStringResponse response = stub.feedHorse(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void recoverHorse(HorseIdRequest request, StreamObserver<HorseStringResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);
        HorseStringResponse response = stub.recoverHorse(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

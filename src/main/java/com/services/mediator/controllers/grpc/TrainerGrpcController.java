package com.services.mediator.controllers.grpc;


import com.services.grpc.server.trainer.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TrainerGrpcController extends TrainerServiceGrpc.TrainerServiceImplBase {

    @Override
    public void showTrainers(TrainerEmpty request, StreamObserver<TrainerResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("staffservice", 9090)
                .usePlaintext()
                .build();

        TrainerServiceGrpc.TrainerServiceBlockingStub stub = TrainerServiceGrpc.newBlockingStub(channel);
        TrainerResponse response = stub.showTrainers(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getSuitableTrainer(SportCategoryRequest request, StreamObserver<TrainerResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("staffservice", 9090)
                .usePlaintext()
                .build();

        TrainerServiceGrpc.TrainerServiceBlockingStub stub = TrainerServiceGrpc.newBlockingStub(channel);
        TrainerResponse response = stub.getSuitableTrainer(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void showTrainerById(TrainerIdRequest request, StreamObserver<TrainerResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("staffservice", 9090)
                .usePlaintext()
                .build();

        TrainerServiceGrpc.TrainerServiceBlockingStub stub = TrainerServiceGrpc.newBlockingStub(channel);
        TrainerResponse response = stub.showTrainerById(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addTrainer(TrainerRequest request, StreamObserver<TrainerResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("staffservice", 9090)
                .usePlaintext()
                .build();

        TrainerServiceGrpc.TrainerServiceBlockingStub stub = TrainerServiceGrpc.newBlockingStub(channel);
        TrainerResponse response = stub.addTrainer(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTrainer(TrainerIdRequest request, StreamObserver<TrainerEmpty> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("staffservice", 9090)
                .usePlaintext()
                .build();

        TrainerServiceGrpc.TrainerServiceBlockingStub stub = TrainerServiceGrpc.newBlockingStub(channel);
        TrainerEmpty response = stub.deleteTrainer(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

package com.services.mediator.controllers.grpc;

import com.services.grpc.server.training.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TrainingGrpcController extends TrainingServiceGrpc.TrainingServiceImplBase {

    @Override
    public void showTrainings(TrainingEmpty request, StreamObserver<TrainingResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://trainingservice", 9090)
                .usePlaintext()
                .build();

        TrainingServiceGrpc.TrainingServiceBlockingStub stub = TrainingServiceGrpc.newBlockingStub(channel);
        TrainingResponse response = stub.showTrainings(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void showTrainingById(IdRequest request, StreamObserver<TrainingResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://trainingservice", 9090)
                .usePlaintext()
                .build();

        TrainingServiceGrpc.TrainingServiceBlockingStub stub = TrainingServiceGrpc.newBlockingStub(channel);
        TrainingResponse response = stub.showTrainingById(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addTraining(TrainingRequest request, StreamObserver<TrainingResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://trainingservice", 9090)
                .usePlaintext()
                .build();

        TrainingServiceGrpc.TrainingServiceBlockingStub stub = TrainingServiceGrpc.newBlockingStub(channel);
        TrainingResponse response = stub.addTraining(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTraining(IdRequest request, StreamObserver<TrainingEmpty> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://trainingservice", 9090)
                .usePlaintext()
                .build();

        TrainingServiceGrpc.TrainingServiceBlockingStub stub = TrainingServiceGrpc.newBlockingStub(channel);
        TrainingEmpty response = stub.deleteTraining(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

package com.services.mediator.controllers.grpc;

import com.services.grpc.server.vet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class VetGrpcController extends VetServiceGrpc.VetServiceImplBase {

    @Override
    public void showVets(VetEmpty request, StreamObserver<VetResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();

        VetServiceGrpc.VetServiceBlockingStub stub = VetServiceGrpc.newBlockingStub(channel);
        VetResponse response = stub.showVets(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void showVetById(VetIdRequest request, StreamObserver<VetResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();

        VetServiceGrpc.VetServiceBlockingStub stub = VetServiceGrpc.newBlockingStub(channel);
        VetResponse response = stub.showVetById(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addVet(VetRequest request, StreamObserver<VetResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();

        VetServiceGrpc.VetServiceBlockingStub stub = VetServiceGrpc.newBlockingStub(channel);
        VetResponse response = stub.addVet(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteVet(VetIdRequest request, StreamObserver<VetEmpty> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://staffservice", 9090)
                .usePlaintext()
                .build();
        VetServiceGrpc.VetServiceBlockingStub stub = VetServiceGrpc.newBlockingStub(channel);
        VetEmpty response = stub.deleteVet(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
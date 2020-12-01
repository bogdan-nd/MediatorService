package com.services.mediator.controllers.grpc;

import com.services.grpc.server.horse.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ClientGrpcController extends ClientServiceGrpc.ClientServiceImplBase {

    @Override
    public void getClients(ClientEmpty request, StreamObserver<ClientResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clientservice", 9090)
                .usePlaintext()
                .build();

        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc.newBlockingStub(channel);
        ClientResponse response = stub.getClients(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getClientById(ClientIdRequest request, StreamObserver<ClientResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clientservice", 9090)
                .usePlaintext()
                .build();

        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc.newBlockingStub(channel);
        ClientResponse response = stub.getClientById(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addClient(ClientRequest request, StreamObserver<ClientResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clientservice", 9090)
                .usePlaintext()
                .build();

        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc.newBlockingStub(channel);
        ClientResponse response = stub.addClient(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void spendMoney(ClientMoneyRequest request, StreamObserver<ClientStringResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clientservice", 9090)
                .usePlaintext()
                .build();
        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc.newBlockingStub(channel);
        ClientStringResponse response = stub.spendMoney(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void earnMoney(ClientMoneyRequest request, StreamObserver<ClientStringResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://clientservice", 9090)
                .usePlaintext()
                .build();

        ClientServiceGrpc.ClientServiceBlockingStub stub = ClientServiceGrpc.newBlockingStub(channel);
        ClientStringResponse response = stub.earnMoney(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

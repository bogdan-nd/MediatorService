package com.services.mediator.controllers.grpc;

import com.services.grpc.server.club.ClubEmpty;
import com.services.grpc.server.club.ClubResponse;
import com.services.grpc.server.club.ClubServiceGrpc;
import com.services.grpc.server.club.ProtoClub;
import com.services.grpc.server.horse.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HorseGrpcController extends HorseServiceGrpc.HorseServiceImplBase {

    @Override
    public void getHorses(HorseEmpty request, StreamObserver<HorseResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("horseservice", 9090)
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
        ManagedChannel channel = ManagedChannelBuilder.forAddress("horseservice", 9090)
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
        ManagedChannel channel = ManagedChannelBuilder.forAddress("horseservice", 9090)
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
        ManagedChannel channel = ManagedChannelBuilder.forAddress("horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);

        if(request.getOwnerId().isEmpty()){
            request = markClubAsOwner(request);
        }

        HorseResponse response = stub.addHorse(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void feedHorse(HorseIdRequest request, StreamObserver<HorseStringResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("horseservice", 9090)
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
        ManagedChannel channel = ManagedChannelBuilder.forAddress("horseservice", 9090)
                .usePlaintext()
                .build();

        HorseServiceGrpc.HorseServiceBlockingStub stub = HorseServiceGrpc.newBlockingStub(channel);
        HorseStringResponse response = stub.recoverHorse(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private HorseRequest markClubAsOwner(HorseRequest request){
        HorseRequest newRequest;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("horseservice", 9090)
                .usePlaintext()
                .build();
        ClubServiceGrpc.ClubServiceBlockingStub horseStub = ClubServiceGrpc.newBlockingStub(channel);
        ClubEmpty clubEmpty = ClubEmpty.newBuilder().build();
        ClubResponse clubResponse = horseStub.getClub(clubEmpty);

        ProtoClub club = clubResponse.getClub();
        String clubId = club.getId();
        newRequest = request.toBuilder().setOwnerId(clubId).build();

        channel.shutdown();
        return newRequest;
    }
}

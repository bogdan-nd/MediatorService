package com.services.mediator.controllers.grpc;

import com.services.grpc.server.care.AppointmentRequest;
import com.services.grpc.server.care.AppointmentResponse;
import com.services.grpc.server.care.CareIdRequest;
import com.services.grpc.server.care.CareServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CareGrpcController extends CareServiceGrpc.CareServiceImplBase {
    @Override
    public void getHorsesAppointment(CareIdRequest request, StreamObserver<AppointmentResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://careservice", 9090)
                .usePlaintext()
                .build();

        CareServiceGrpc.CareServiceBlockingStub stub = CareServiceGrpc.newBlockingStub(channel);
        AppointmentResponse response = stub.getHorsesAppointment(request);
        channel.shutdown();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createAppointment(AppointmentRequest request, StreamObserver<AppointmentResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://careservice", 9090)
                .usePlaintext()
                .build();

        CareServiceGrpc.CareServiceBlockingStub stub = CareServiceGrpc.newBlockingStub(channel);
        AppointmentResponse response = stub.createAppointment(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

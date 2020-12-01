package com.services.mediator.controllers.grpc;

import com.services.grpc.server.finance.FinanceServiceGrpc;
import com.services.grpc.server.finance.PaymentIdRequest;
import com.services.grpc.server.finance.PaymentRequest;
import com.services.grpc.server.finance.PaymentResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FinanceGrpcController extends FinanceServiceGrpc.FinanceServiceImplBase {

    @Override
    public void getClientPayments(PaymentIdRequest request, StreamObserver<PaymentResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://financeservice", 9090)
                .usePlaintext()
                .build();

        FinanceServiceGrpc.FinanceServiceBlockingStub stub = FinanceServiceGrpc.newBlockingStub(channel);
        PaymentResponse response = stub.getClientPayments(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createPayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("http://financeservice", 9090)
                .usePlaintext()
                .build();

        FinanceServiceGrpc.FinanceServiceBlockingStub stub = FinanceServiceGrpc.newBlockingStub(channel);
        PaymentResponse response = stub.createPayment(request);
        channel.shutdown();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

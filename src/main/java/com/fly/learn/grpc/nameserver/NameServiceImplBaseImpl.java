package com.fly.learn.grpc.nameserver;

import io.grpc.examples.nameserver.Ip;
import io.grpc.examples.nameserver.Name;
import io.grpc.examples.nameserver.NameServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2020/10/15
 * @Description:
 */
public class NameServiceImplBaseImpl extends NameServiceGrpc.NameServiceImplBase{
    private Map<String,String> map = new HashMap<String,String>();

    private Logger logger = LoggerFactory.getLogger(NameServiceImplBaseImpl.class.getName());

    public NameServiceImplBaseImpl() {

        map.put("Sunny","125.216.242.51");

        map.put("David","117.226.178.139");

    }

    @Override
    public void getIpByName(Name request, StreamObserver<Ip> responseObserver) {
        logger.info("requst is coming. args=" + request.getName());

        Ip ip = Ip.newBuilder().setIp(getName(request.getName())).build();

        responseObserver.onNext(ip);

        responseObserver.onCompleted();

    }

    public String getName(String name){
        String ip = map.get(name);
        if(ip == null){
            return "0.0.0.0";
        }
        return ip;
    }

}

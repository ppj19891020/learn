package com.fly.learn.iott;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 云端sdk操作
 * @author: peijiepang
 * @date 2020/8/15
 * @Description:
 */
public class IottApiSdk {

    private DefaultAcsClient client;
    private String accessKey = "";
    private String accessSecret = "";
    private String productKey = "";
    private String deviceName = "";
    /**
     * 初始化
     */
    private void init(){
        DefaultProfile.addEndpoint( "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        client = new DefaultAcsClient(profile); //初始化SDK客户端
    }

    public static void main(String[] args) {
        IottApiSdk iottApiSdk = new IottApiSdk();
        iottApiSdk.init();

        iottApiSdk.setDeviceInfo();
        iottApiSdk.invokeDeviceService();

    }

    /**
     * 设置设备属性，可以同时设置多个属性
     * @return
     */
    private CommonResponse setDeviceInfo(){
        // 设置设备属性
        CommonResponse response = null;
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("iot.cn-shanghai.aliyuncs.com");
        request.setSysVersion("2018-01-20");
        request.setSysAction("SetDeviceProperty");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("Items", "{\"price\":120,\"lock\":0}");
        request.putQueryParameter("ProductKey", productKey);
        request.putQueryParameter("DeviceName", deviceName);
        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        }catch (ClientException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 调用设备提供的服务
     * @return
     */
    private CommonResponse invokeDeviceService(){
        //调用设备操作服务
        CommonResponse response = null;
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("iot.cn-shanghai.aliyuncs.com");
        request.setSysVersion("2018-01-20");
        request.setSysAction("InvokeThingService");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("Args", "{}");
        request.putQueryParameter("Identifier", "lock");
        request.putQueryParameter("ProductKey", productKey);
        request.putQueryParameter("DeviceName", deviceName);
        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        }catch (ClientException e) {
            e.printStackTrace();
        }
        return response;
    }

}

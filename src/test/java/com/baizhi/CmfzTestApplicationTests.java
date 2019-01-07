package com.baizhi;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.util.Md5Util;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzTestApplicationTests {
    @Autowired
    private Md5Util md5Util;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void contextLoads1() {
        //字符串转char数组
        char[] str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456".toCharArray();

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            //随机生成0到str长度之间的数字做为下标
            int randomIndex = random.nextInt(str.length);
            //追加到sb对象
            sb.append(str[randomIndex]);
        }
        String salt = sb.toString();
        System.out.println(salt);
        String password = md5Util.encryption("123456" + salt);
        System.out.println(password);
    }

    @Test
    public void upload() throws FileNotFoundException {
        File f = new File("F:/33.jpg");
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(f), f.length(), FilenameUtils.getExtension(f.getName()), null);
        System.out.println(storePath);
    }

    @Test
    public void download() throws IOException {
        byte[] group1s = fastFileStorageClient.downloadFile("group1", "M00/00/00/wKjHh1wj6wSAZuYZAABr_a20RKQ092.jpg", new DownloadByteArray());
        FileOutputStream out = new FileOutputStream("F:/2.jpg");
        out.write(group1s);
        out.close();
    }

    @Test
    public void Testload() {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers("15038713551");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("何腾飞");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_141606919");

        char[] str = "1234567890".toCharArray();

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            //随机生成0到str长度之间的数字做为下标
            int randomIndex = random.nextInt(str.length);
            //追加到sb对象
            sb.append(str[randomIndex]);
        }
        String code = sb.toString();

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":" + code + "}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
        }
    }

    @Test
    public void CodeTest() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        opsForList.leftPush("a", "小明");
        Object a = opsForList.leftPop("a");

    }

    @Test
    public void TestRedis2() throws InterruptedException {
        ValueOperations<String, Object> forValue = redisTemplate.opsForValue();

        // forValue.set("c","abccccc",1, TimeUnit.MINUTES);
        Object b = forValue.get("c");
        System.out.println(b);
    }
}


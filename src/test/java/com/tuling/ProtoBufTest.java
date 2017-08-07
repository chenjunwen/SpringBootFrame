package com.tuling;

import com.google.protobuf.Any;
import com.tuling.modal.Demo;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ProtoBufTest {
    public static void main(String[] args) {


        try {
            CloseableHttpClient client = HttpClients.createDefault();

            Demo.Login login = Demo.Login.newBuilder().setUserName("cavacn").setPassWord("passowrd").build();

            Demo.Request request = Demo.Request.newBuilder().setBody(Any.pack(login)).setCommand(Demo.COMMAND.LOGIN).setSequenceId(21321412l).setSessionId("gaegegw").build();

            HttpHost host = new HttpHost("localhost", 8080);
            HttpPost post = new HttpPost();
            post.setURI(new URI("/api/index"));
            post.setHeader("Content-Type", "application/octet-stream");
            post.setEntity(new ByteArrayEntity(request.toByteArray()));

            byte[] result = request.toByteArray();

            Demo.Request l = Demo.Request.parseFrom(result);



            System.out.println(l.getCommand());

            client.execute(host, post);
            CloseableHttpResponse response = client.execute(host, post);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}

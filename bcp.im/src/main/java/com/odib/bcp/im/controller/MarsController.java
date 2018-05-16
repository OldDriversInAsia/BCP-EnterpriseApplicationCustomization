/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: MarsController
 * Author: lizhuo
 * Date: 2018/5/14 下午7:50
 * Description:
 */
package com.odib.bcp.im.controller;

import com.google.protobuf.ByteString;
import com.odib.bcp.im.mars.sample.chat.proto.Chat;
import com.odib.bcp.im.mars.sample.proto.Main;
import com.odib.bcp.im.mars.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * <br>
 * 〈〉
 *  @author lizhuo
 * @create 2018/5/14
 * @since 1.0.0
 */
@RestController()
@RequestMapping("/mars")
public class MarsController {
    Logger logger = Logger.getLogger(MarsController.class.getName());
    private String[][] conDetails = new String[][]{
            new String[] {"Mars", "0", "STN 聊天室"},
            new String[] {"Mars", "1", "Xlog 聊天室"},
            new String[] {"Mars", "2", "SDT 聊天室"},
            new String[] {"Mars", "3", "老司机 聊天室"}
    };
    @RequestMapping(value = "/hello", consumes = "application/octet-stream", produces = "application/octet-stream")
    public Response hello(InputStream is) {
        try {
            System.out.println("hello");
            final Main.HelloRequest request = Main.HelloRequest.parseFrom(is);

            logger.info(LogUtils.format("request from user=%s, text=%s", request.getUser(), request.getText()));

            int retCode = 0;
            String errMsg = "congratulations, " + request.getUser();
            final Main.HelloResponse response = Main.HelloResponse.newBuilder().setRetcode(retCode).setErrmsg(errMsg).build();

            final StreamingOutput stream = new StreamingOutput() {
                public void write(OutputStream os) throws IOException {
                    response.writeTo(os);
                }
            };
            return Response.ok(stream).build();

        } catch (Exception e) {
            logger.info(LogUtils.format("%s", e));
        }

        return null;
    }
    @RequestMapping(value = "/getconvlist", consumes = "application/octet-stream", produces = "application/octet-stream")
    public ResponseEntity<StreamingResponseBody> getconvlist(InputStream is) {
        try {
            System.out.println("getconvlist");
            final Main.ConversationListRequest request = Main.ConversationListRequest.parseFrom(is);

            logger.info(LogUtils.format("request from access_token=%s, type=%d", request.getAccessToken(), request.getType()));

            List<Main.Conversation> conversationList = null;
            if (request.getType() == Main.ConversationListRequest.FilterType.DEFAULT_VALUE) {
                conversationList = new LinkedList<Main.Conversation>();

                for (String[] conv : conDetails) {
                    conversationList.add(Main.Conversation.newBuilder()
                            .setName(conv[0])
                            .setTopic(conv[1])
                            .setNotice(conv[2])
                            .build());
                }
            }

            final Main.ConversationListResponse response = Main.ConversationListResponse.newBuilder()
                    .addAllList(conversationList).build();
            final StreamingResponseBody stream = new StreamingResponseBody() {
                @Override
                public void writeTo(OutputStream outputStream) throws IOException {
                    response.writeTo(outputStream);

                }
            };

            return ResponseEntity.ok(stream);
        } catch (Exception e) {
            logger.info(LogUtils.format("request invalid", e));
        }

        return null;
    }

    @RequestMapping(value = "/hello2", consumes = "*/*", produces = "application/octet-stream")
    public Response hello2(InputStream is) {
        try {
            System.out.println("hello2");

            final Main.HelloRequest request = Main.HelloRequest.parseFrom(is);

            logger.info(LogUtils.format("mars3 request from user=%s, text=%s", request.getUser(), request.getText()));
            if(request.hasDumpContent())
                logger.info(LogUtils.format("dump content length:%d", request.getDumpContent().size()));


            int retCode = 0;
            String errMsg = "hello mars";
            int size = 0;
            if(request.getText() == "64KB")
                size = 64*1024;
            if(request.getText() == "128KB")
                size = 128*1024;
            Main.HelloResponse response = null;
            if(size > 0) {
                byte[] dump = new byte[size];
                Random rand = new Random();
                rand.nextBytes(dump);
                response = Main.HelloResponse.newBuilder().setRetcode(retCode).setErrmsg(errMsg).setDumpContent(ByteString.copyFrom(dump)).build();
            } else {
                response = Main.HelloResponse.newBuilder().setRetcode(retCode).setErrmsg(errMsg).build();
            }
            final Main.HelloResponse resp = response;

            final StreamingOutput stream = new StreamingOutput() {
                public void write(OutputStream os) throws IOException {
                    resp.writeTo(os);
                }
            };
            return Response.ok(stream).header("Content-Length", response.getSerializedSize()).build();

        } catch (Exception e) {
            logger.info("error mars hello3");
            logger.info(LogUtils.format("%s", e));
        }

        return null;
    }

    @RequestMapping(value = "/sendmessage", consumes = "application/octet-stream", produces = "application/octet-stream")
    public ResponseEntity<StreamingResponseBody> sendmessage(InputStream is) {
        try {
            System.out.println("hello2");

            final Chat.SendMessageRequest request = Chat.SendMessageRequest.parseFrom(is);

            logger.info(LogUtils.format("request from user=%s, text=%s to topic=%s", request.getFrom(), request.getText(), request.getTopic()));

            int retCode = Chat.SendMessageResponse.Error.ERR_OK_VALUE;
            String errMsg = "congratulations, " + request.getFrom();
            final Chat.SendMessageResponse response = Chat.SendMessageResponse.newBuilder()
                    .setErrCode(retCode)
                    .setErrMsg(errMsg)
                    .setFrom(request.getFrom())
                    .setText(request.getText())
                    .setTopic(request.getTopic())
                    .build();

            final StreamingResponseBody stream = new StreamingResponseBody() {
                @Override
                public void writeTo(OutputStream outputStream) throws IOException {
                    response.writeTo(outputStream);

                }
            };
            return ResponseEntity.ok(stream);

        } catch (Exception e) {
            logger.info(LogUtils.format("%s", e));
        }

        return null;
    }
}
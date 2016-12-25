package com.pku.controller;

/**
 * Created by admin on 2016/12/25.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。
/**
 类似Servlet的注解mapping。无需在web.xml中配置。
 * ownId为发起人
 * configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 */
@ServerEndpoint(value = "/websocket/{ownId}",configurator = SpringConfigurator.class)
public class NewWebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private String ownId;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    // 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String, HashMap<String, NewWebSocket>> webSocketHashMap = new ConcurrentHashMap<String, HashMap<String, NewWebSocket>>();

    //与客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    public NewWebSocket() {
    }

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("ownId") String ownId, Session session){
        this.session = session;
        this.ownId = ownId;

        HashMap<String, NewWebSocket> hashMap = null;

        if (!webSocketHashMap.containsKey(ownId)) {
            hashMap = new HashMap<String, NewWebSocket>();
            hashMap.put(session.getId(), this);
            webSocketHashMap.put(ownId, hashMap);
        } else {
            hashMap = webSocketHashMap.get(ownId);
            hashMap.put(session.getId(), this);
            webSocketHashMap.put(ownId, hashMap);
        }
        //webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        delUserWebSocket(ownId, this.session.getId());
        //webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(@PathParam("ownId") String ownId,String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        //sendMessageToAll(message);
        sendMessageToOne(ownId,"收到");
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 广播消息
     * @param message 客户端发送过来的消息
     */
    public void sendMessageToAll(String message){
        Collection<HashMap<String, NewWebSocket>> hashMapCollection = webSocketHashMap.values();
        Iterator ite = hashMapCollection.iterator();
        while(ite.hasNext()) {
            //System.out.println(ite.next());
            HashMap<String, NewWebSocket> hashMap = (HashMap<String, NewWebSocket>) ite.next();
            for (Map.Entry<String, NewWebSocket> item : hashMap.entrySet()) {
                try {
                    item.getValue().sendMessage("{\n" +
                            "  \"code\": 0,\n" +
                            "  \"msg\": \"success\",\n" +
                            "  \"data\": {\n" +
                            "    \"message\":" + message + "\n" +
                            "  }\n" +
                            "}");
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    /**
     * 指定用户发送消息
     * @param ownId 客户端编号
     * @param message 客户端发送过来的消息
     */
    public void sendMessageToOne(String ownId,String message){
        for (Map.Entry<String, NewWebSocket> item : getUserWebSocket(ownId).entrySet()) {
            try {
                item.getValue().sendMessage("{\n" +
                        "  \"code\": 0,\n" +
                        "  \"msg\": \"success\",\n" +
                        "  \"data\": {\n" +
                        "    \"lessonId\":" + ownId + ",\n" +
                        "    \"message\":" + message + "\n" +
                        "  }\n" +
                        "}");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        //保存数据到数据库
//        Content content = new Content() ;
//        content.setContent(message);
//        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd") ;
//        content.setCreateDate(sm.format(new Date()));
//        contentService.insertSelective(content) ;
//
        //this.session.getBasicRemote().sendText("哈哈哈"+message);
        this.session.getAsyncRemote().sendText(message);
    }


    public void delUserWebSocket(String ownId, String sessionId) {
        String key = ownId;
        if (webSocketHashMap.containsKey(key)) {
            HashMap<String, NewWebSocket> hashMap = webSocketHashMap.get(key);
            hashMap.remove(sessionId);
            webSocketHashMap.put(key, hashMap);
            if (webSocketHashMap.get(key).size() == 0) {
                webSocketHashMap.remove(key);
            }
        }
    }

    public HashMap<String, NewWebSocket> getUserWebSocket(String ownId) {

        String key = ownId;

        HashMap<String, NewWebSocket> hashMap = new HashMap<String, NewWebSocket>();

        if (webSocketHashMap.containsKey(key)) {
            hashMap = webSocketHashMap.get(key);
        }

        return hashMap;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        NewWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        NewWebSocket.onlineCount--;
    }

}
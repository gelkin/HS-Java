package space.harbour.hw12.sockets.chat;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import space.harbour.hw12.db.MsgDBService;
import space.harbour.hw12.db.MyMsgDBService;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author v.chibrikov
 */
public class ChatWebSocketCreator implements WebSocketCreator {
    private Set<ChatWebSocket> users;
    private MsgDBService service;

    public ChatWebSocketCreator(MsgDBService service) {
        this.users = Collections.newSetFromMap(new ConcurrentHashMap<ChatWebSocket, Boolean>());
        this.service = service;
        System.out.println("WebSocketCreator created");
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        ChatWebSocket socket = new ChatWebSocket(users, service);
        System.out.println("Socket created");
        return socket;
    }
}

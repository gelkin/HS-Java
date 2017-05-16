package space.harbour.hw12.sockets.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import space.harbour.hw12.db.dataset.ChatMsgDataSet;
import space.harbour.hw12.db.MsgDBService;

import java.util.Set;

@WebSocket
public class ChatWebSocket {
    private Set<ChatWebSocket> users;
    private Session session;
    private MsgDBService service;

    public ChatWebSocket(Set<ChatWebSocket> users, MsgDBService service) {
        this.users = users;
        this.service = service;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        String[] tokens = data.split(":");
        String login = tokens[0];
        String msg = tokens[1];
        service.addMsg(new ChatMsgDataSet(login, msg));
        System.out.println("Msg is written into db");

        for (ChatWebSocket user : users) {
            try {
                user.getSession().getRemote().sendString(data);
                System.out.println("Sending message: " + data);
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        users.add(this);
        setSession(session);
        System.out.println("onOpen");
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        users.remove(this);
        System.out.println("onClose");
    }
}

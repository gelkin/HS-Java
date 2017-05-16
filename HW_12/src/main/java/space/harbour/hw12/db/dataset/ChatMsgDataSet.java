package space.harbour.hw12.db.dataset;

public class ChatMsgDataSet {
    private String login;
    private String msg;

    public ChatMsgDataSet(String login, String msg) {
        this.login = login;
        this.msg = msg;
    }

    public String getLogin() {
        return login;
    }

    public String getMsg() {
        return msg;
    }
}

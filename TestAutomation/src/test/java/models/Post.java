package models;

public class Post {

    private String title;
    private String body;
    private int userId;
    private int id;

    public Post(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
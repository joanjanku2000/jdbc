package model;

import java.time.LocalDate;

public class Post {
    private Integer id;
    private String title;
    private String body;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private Integer userId;

    public Post() {
    }

    public Post(Integer id, String title, String body, LocalDate dateCreated, LocalDate dateModified, Integer userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", userId=" + userId +
                '}';
    }
}

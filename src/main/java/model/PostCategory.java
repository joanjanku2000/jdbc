package model;

import java.time.LocalDate;

public class PostCategory {
    private Integer id;
    private Integer postId;
    private Integer categoryId;
    private LocalDate dateCreated;
    private LocalDate dateModified;

    public PostCategory() {
    }

    public PostCategory(Integer id, Integer postId, Integer categoryId, LocalDate dateCreated, LocalDate dateModified) {
        this.id = id;
        this.postId = postId;
        this.categoryId = categoryId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "PostCategory{" +
                "id=" + id +
                ", postId=" + postId +
                ", categoryId=" + categoryId +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                '}';
    }
}

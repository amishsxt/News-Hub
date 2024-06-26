package com.example.newshub.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "news_articles", indices = {@Index(value = {"title"}, unique = true)})
public class NewsArticle {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String sourceId;
    private String sourceName;

    @ColumnInfo(name = "title")
    private String title;

    private String description;
    private String content;
    private String author;
    private String url;
    private String urlToImage;
    private Date publishedAt;

    public NewsArticle(String sourceId, String sourceName, String title, String description, String content, String author, String url, String urlToImage, Date publishedAt) {
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}


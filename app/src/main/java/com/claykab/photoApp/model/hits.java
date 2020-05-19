package com.claykab.photoApp.model;

public class hits {








    private long id;
    private String pageURL;
    private String type;
    private String tags;
    private String previewURL;
    private int previewWidth;
    private int previewHeight;
    private String webformatURL;
    private int webformatWidth;
    private int webformatHeight;
    private String largeImageURL;
    private int imageWidth;
    private int imageHeight;
    private int imageSize;
    private long views;
    private long downloads;
    private long favorites;
    private long likes;
    private long comments;
    private long user_id;
    private String user;
    private String userImageURL;

    public hits(long id, String pageURL, String type, String tags, String previewURL, int previewWidth, int previewHeight, String webformatURL, int webformatWidth, int webformatHeight, String largeImageURL, int imageWidth, int imageHeight, int imageSize, long views, long downloads, long favorites, long likes, long comments, long user_id, String user, String userImageURL) {
        this.id = id;
        this.pageURL = pageURL;
        this.type = type;
        this.tags = tags;
        this.previewURL = previewURL;
        this.previewWidth = previewWidth;
        this.previewHeight = previewHeight;
        this.webformatURL = webformatURL;
        this.webformatWidth = webformatWidth;
        this.webformatHeight = webformatHeight;
        this.largeImageURL = largeImageURL;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.imageSize = imageSize;
        this.views = views;
        this.downloads = downloads;
        this.favorites = favorites;
        this.likes = likes;
        this.comments = comments;
        this.user_id = user_id;
        this.user = user;
        this.userImageURL = userImageURL;
    }

    public long getId() {
        return id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getType() {
        return type;
    }

    public String getTags() {
        return tags;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageSize() {
        return imageSize;
    }

    public long getViews() {
        return views;
    }

    public long getDownloads() {
        return downloads;
    }

    public long getFavorites() {
        return favorites;
    }

    public long getLikes() {
        return likes;
    }

    public long getComments() {
        return comments;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getUser() {
        return user;
    }

    public String getUserImageURL() {
        return userImageURL;
    }
}

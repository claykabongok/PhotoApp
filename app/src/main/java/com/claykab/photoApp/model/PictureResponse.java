package com.claykab.photoApp.model;


import java.util.List;

public class PictureResponse  {
 private long total;
 private long totalHits;
 private List<hits> hits;
 private Throwable error;
 private int code;
 private String message;

 public PictureResponse(long total, long totalHits, List<hits> hits){
     this.total=total;
     this.totalHits=totalHits;
     this.hits = hits;
 }


    public PictureResponse(Throwable error) {
        this.error=error;

    }

    public PictureResponse(int code, String message){
     this.code=code;
     this.message=message;
    }

    public long getTotal() {
        return total;
    }

    public PictureResponse setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getTotalHits() {
        return totalHits;
    }

    public PictureResponse setTotalHits(long totalHits) {
        this.totalHits = totalHits;
        return this;
    }

    public List<hits> getHitsList() {
        return hits;
    }

    public PictureResponse setHitsList(List<hits> hitsList) {
        this.hits = hitsList;
        return this;
    }

    public Throwable getError() {
        return error;
    }

    public PictureResponse setError(Throwable error) {
        this.error = error;
        return this;
    }

    public int getCode() {
        return code;
    }

    public PictureResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PictureResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}

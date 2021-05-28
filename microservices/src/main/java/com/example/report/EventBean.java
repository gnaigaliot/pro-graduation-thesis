package com.example.report;

import java.util.Date;

public class EventBean {
    private Integer id;
    private String title;
    private String start;
    private String end;
    private String url;
    private String icon;
    
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
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getStart() {
        return start;
    }

    
    public void setStart(String start) {
        this.start = start;
    }

    
    public String getEnd() {
        return end;
    }

    
    public void setEnd(String end) {
        this.end = end;
    }

    
    public String getIcon() {
        return icon;
    }

    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
}

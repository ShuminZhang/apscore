package com.demo01.entity.sys;

import java.util.HashMap;
import java.util.Map;

public class MenuTree {
    private String id = null;
    private String name = null;
    private String title = null;
    private String url = null;
    private String type = null;

    private Map<String,MenuTree> subTree= new HashMap<String,MenuTree>();

    public MenuTree() {

    }

    public MenuTree(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.title = menu.getTitle();
        this.url = menu.getUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, MenuTree> getSubTree() {
        return subTree;
    }

    public void setSubTree(Map<String, MenuTree> subTree) {
        this.subTree = subTree;
    }


}

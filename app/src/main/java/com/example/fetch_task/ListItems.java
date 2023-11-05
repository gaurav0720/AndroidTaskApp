package com.example.fetch_task;

public class ListItems {

    private Integer id;
    private Integer listId;
    private String name;

    public ListItems(Integer id, Integer listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }
}

package com.wuxianggujun.toolbox.socket.po;

import java.io.Serializable;

public class FileData implements Serializable {
    private int size;
    private String name;
    
    private byte[] data;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

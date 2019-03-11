package com.zhang.entity;

import com.zhang.model.Rresource;

import java.io.Serializable;

public class RresourceDetaileRsp implements Serializable {
    private Rresource resource;

    public Rresource getResource() {
        return resource;
    }

    public void setResource(Rresource resource) {
        this.resource = resource == null ? new Rresource() : resource;
    }
}

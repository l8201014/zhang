package com.zhang.service;

import com.zhang.model.Rresource;

public interface RresourceService {
    public void insert(Rresource resource);

    public Rresource getResourceByUrl(String listUrl);

    public void update(Rresource resource);
}

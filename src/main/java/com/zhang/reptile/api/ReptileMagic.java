package com.zhang.reptile.api;

import com.zhang.model.Rresource;

import java.util.List;

public interface ReptileMagic {
    List<Rresource> getList(String keyWord, String pageIndex);

    Integer getTotalNum(String keyWord);

    List<Rresource> getListNew(String keyWord);

    Rresource getDetails(String id);

    Rresource getDetailsNew(String listUrl);

    Rresource getDetailsTask(Rresource resource);

    void getListTask(String[] seeds);
}

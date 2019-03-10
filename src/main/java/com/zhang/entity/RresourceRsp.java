package com.zhang.entity;

import com.zhang.model.Rresource;

import java.util.List;

public class RresourceRsp extends BesaRsp{
    private List<Rresource>  responseList;

    public List<Rresource> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Rresource> responseList) {
        this.responseList = responseList;
    }
}

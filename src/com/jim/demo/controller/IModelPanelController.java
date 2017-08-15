package com.jim.demo.controller;

/**
 * Created by Administrator on 2016/11/9.
 */
public interface IModelPanelController {
    public String getApiService(String method,String tag,String url);
    public String getApiManager(String method,String tag);
    public String getModel(String method,String tag);
    public String getCode(String method,String tag,String url);

    }

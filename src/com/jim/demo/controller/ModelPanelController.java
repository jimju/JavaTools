package com.jim.demo.controller;

/**
 * Created by Administrator on 2016/10/20.
 */
public class ModelPanelController implements IModelPanelController{
    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String PUT = "PUT";



    public String getApiService(String method,String tag,String url){
        String apiserviceHeader = "/** \n*ApiManagerService方法\n*/\n";
        StringBuilder sb = new StringBuilder(apiserviceHeader);
        method = method.toUpperCase();
        sb.append("@").append(method).append("(\"").append(url + "\")");
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        sb.append("\n");
        if (method.equals(GET)){
            sb.append(" Observable<BaseJson> ").append("retrive"+ tag).append("(@QueryMap Map<String, String> map);");
        }else if (method.equals(POST)){
            sb.append(" Observable<BaseJson> ").append("update" + tag).append("(@Body RequestBean bean, @Query(\"token\") String token);");
        }else {
            sb.append(" Observable<BaseJson> ").append("create" + tag).append("(@Body RequestBean bean, @Query(\"token\") String token);");
        }
        return sb.toString();
    }

    public String getApiManager(String method,String tag){
        String apimanagerHeader = "/** \n*ApiManager方法\n*/\n";
        StringBuilder sb = new StringBuilder(apimanagerHeader);
        sb.append("public static");
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        String ctag = "";
        if (method.equals(GET)){
            ctag = "onRetrive"+ tag;
            sb.append(" Observable<BaseJson> ").append( ctag).append("(Map<String,String> map)");
            sb.append("\n{return apiManager.").append(ctag).append("(map);}");
        }else if (method.equals(POST)){
            ctag = "onUpdate"+ tag;
            sb.append(" Observable<BaseJson> ").append("" + ctag).append("(RequestBean bean,String token)");
            sb.append("\n{return apiManager.").append(ctag).append("(bean,token);}");
        }else {
            ctag = "onCreate"+ tag;
            sb.append(" Observable<BaseJson> ").append("" + ctag).append("(RequestBean bean,String token)");
            sb.append("\n{return apiManager.").append(ctag).append("(bean,token);}");
        }
        return sb.toString();
    }

    public String getModel(String method,String tag){
        String modelHeader = "/** \n*Model方法\n*/\n";
        String modelImpHeader = "\n/** \n*ModelImp方法\n*/\n";
        StringBuilder sb = new StringBuilder(modelHeader);
        sb.append("Subscription ");
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        String ctag = "";
        if (method.equals(GET)){
            ctag = "retrive" + tag;
            sb.append(ctag).append("(Map<String,String> map);");
            sb.append("\n").append(modelImpHeader);
            sb.append("Subscription sub = ApiManager.").append(ctag).append("(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<UserInfoJson>() {");
            sb.append("\n@Override\n" +
                    "public void call(UserInfoJson json) {" +
                    " mListener.retriveSuccess(json);" +
                    " } }, new Action1<Throwable>() {\n" +
                    " @Override\n " +
                    " public void call(Throwable throwable) {" +
                    " mListener.onError(throwable);}});\n" +
                    " return sub;");
        }else if (method.equals(POST)){
            ctag = "update"+ tag;
            sb.append("" + ctag).append("(RequestBean bean,String token);");
            sb.append("\n").append(modelImpHeader);
            sb.append("Subscription sub = ApiManager.").append(ctag).append("(bean,token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseJson>() {");
            sb.append(" \n@Override\n" +
                    "public void call(UserInfoJson json) { mListener.updateSuccess(json);} }, new Action1<Throwable>() {\n@Override\n public void call(Throwable throwable) {\n" +
                    "mListener.onError(throwable); }});\nreturn sub;");
        }else {
            ctag = "create"+ tag;
            sb.append("" + ctag).append("(RequestBean bean,String token);");
            sb.append("\n").append(modelImpHeader);
            sb.append("Subscription sub = ApiManager.").append(ctag).append("(bean,token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseJson>() {");
            sb.append(" @Override\n" +
                    "public void call(BaseJson json) {mListener.updateSuccess(json);}}, new Action1<Throwable>() {\n" +
                    "@Override\n public void call(Throwable throwable) {\n mListener.onError(throwable); }});\n" +
                    " return sub;");
        }
        return sb.toString();
    }

    public String getCode(String method,String tag,String url){
        StringBuilder builder = new StringBuilder("\n");
        String apiService = getApiService(method, tag, url);
        builder.append(apiService);
        builder.append("\n\n");
        String apiManger = getApiManager(method, tag);
        builder.append(apiManger);
        builder.append("\n\n");
        String model = getModel(method, tag);
        builder.append(model);
        return builder.toString();
    }
}

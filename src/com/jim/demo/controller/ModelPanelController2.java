package com.jim.demo.controller;

/**
 * Created by Administrator on 2016/10/20.
 */
public class ModelPanelController2 implements IModelPanelController {
    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String PUT = "PUT";


    public String getApiService(String method, String tag, String url) {
        String apiserviceHeader = "/** \n*ApiManagerService方法\n*/\n";
        StringBuilder sb = new StringBuilder(apiserviceHeader);
        method = method.toUpperCase();
        sb.append("@").append(method).append("(\"").append(url + "\")");
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        sb.append("\n");
        if (method.equals(GET)) {
            sb.append(" Observable<" + tag + "Json> ").append("retrive" + tag).append("(@QueryMap Map<String, String> map, @HeaderMap Map<String,String> hmap);");
        } else if (method.equals(POST)) {
            sb.append(" Observable<" + tag + "Json> ").append("update" + tag).append("(@Body RequestBean bean,  @HeaderMap Map<String,String> hmap);");
        } else {
            sb.append(" Observable<" + tag + "Json> ").append("create" + tag).append("(@Body RequestBean bean, @HeaderMap Map<String,String> hmap);");
        }
        return sb.toString();
    }

    public String getApiManager(String method, String tag) {
        String apimanagerHeader = "/** \n*ApiManager方法\n 注意修改BNHelper.getHeader方法的Url和Function \n*/\n";
        StringBuilder sb = new StringBuilder(apimanagerHeader);
        sb.append("public static");
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        String ctag = "";
        if (method.equals(GET)) {
            ctag = "retrive" + tag;
            sb.append(" Observable<" + tag + "Json> ").append(ctag).append("(Map<String,String> map)");
            sb.append("\n{return apiManager.").append(ctag).append("(map,BNHelper.getHeader(map,\"/resources/xsa/lookup/searchLookupValues\",BaseApplication.token,Function.ACCOUNT_M.getName()));}");
        } else if (method.equals(POST)) {
            ctag = "update" + tag;
            sb.append(" Observable<" + tag + "Json> ").append("" + ctag).append("(RequestBean bean,String token)");
            sb.append("\n{return apiManager.").append(ctag).append("(bean,token);}");
        } else {
            ctag = "create" + tag;
            sb.append(" Observable<" + tag + "Json> ").append("" + ctag).append("(RequestBean bean,String token)");
            sb.append("\n{return apiManager.").append(ctag).append("(bean,token);}");
        }
        return sb.toString();
    }

    public String getModel(String method, String tag) {
        String modelHeader = "/** \n*Model方法\n*/\n";
        String modelImpHeader = "\n/** \n*ModelImp方法\n*/\n";
        StringBuilder sb = new StringBuilder(modelHeader);
        sb.append("Subscription ");
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        String ctag = "";
        String cjson = tag + "Json";
        if (method.equals(GET)) {
            ctag = "retrive" + tag;
            sb.append(ctag).append("(Map<String,String> map);");
            sb.append("\n").append(modelImpHeader);
            sb.append("\n").append("private CURLinstener<" + cjson + "," + cjson + "," + cjson + "> mListener;\n");
            sb.append("Subscription sub = ApiManager.").append(ctag).append("(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseJson>() {");
            sb.append("\n@Override\n" +
                    "public void call(" + tag + "Json json) {" +
                    " mListener.onRetrieveSuccess(json);" +
                    " } }, new Action1<Throwable>() {\n" +
                    " @Override\n " +
                    " public void call(Throwable throwable) {" +
                    " mListener.onError(throwable);}});\n" +
                    " return sub;");
        } else if (method.equals(POST)) {
            ctag = "update" + tag;
            sb.append("" + ctag).append("(RequestBean bean,String token);");
            sb.append("\n").append(modelImpHeader);
            sb.append("Subscription sub = ApiManager.").append(ctag).append("(bean,token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseJson>() {");
            sb.append(" \n@Override\n" +
                    "public void call(" + tag + "Json json) { mListener.onUpdateSuccess(json);} }, new Action1<Throwable>() {\n@Override\n public void call(Throwable throwable) {\n" +
                    "mListener.onError(throwable); }});\nreturn sub;");
        } else {
            ctag = "create" + tag;
            sb.append("" + ctag).append("(RequestBean bean,String token);");
            sb.append("\n").append(modelImpHeader);
            sb.append("Subscription sub = ApiManager.").append(ctag).append("(bean,token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseJson>() {");
            sb.append(" @Override\n" +
                    "public void call(" + tag + "Json json) {mListener.onCreateSuccess(json);}}, new Action1<Throwable>() {\n" +
                    "@Override\n public void call(Throwable throwable) {\n mListener.onError(throwable); }});\n" +
                    " return sub;");
        }
        return sb.toString();
    }

    /**
     * View代码生成
     *
     * @param method
     * @param tag
     * @return
     */
    public String getViewCode(String method, String tag) {
        String header = "/** \n*View方法\n*/\n";
        StringBuilder sb = new StringBuilder(header);
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        String ctag = "";
        String cjson = tag + "Json";
        if (method.equals(GET)) {
            ctag = "retrive" + tag;

        } else if (method.equals(POST)) {
            ctag = "update" + tag;
        } else {
            ctag = "create" + tag;
        }
        sb.append("void ").append(ctag).append("(").append(cjson).append(" json);");
        return sb.toString();
    }

    /**
     * Presenter 监听Listener代码生成
     *
     * @param method
     * @param tag
     * @return
     */

    public String getPresenterCode(String method, String tag) {
        String header = "/** \n*Presenter方法\n*/\n";

        String listenerHeader = "/** \n*PresenterImp 方法\n*/\n";
        StringBuilder sb = new StringBuilder(header);
        char c = tag.charAt(0);
        tag = Character.toUpperCase(c) + tag.substring(1);
        String ctag = "";
        String cjson = tag + "Json";
        if (method.equals(GET)) {
            ctag = "retrive" + tag;

        } else if (method.equals(POST)) {
            ctag = "update" + tag;
        } else {
            ctag = "create" + tag;
        }
        sb.append("public abstract void ").append(ctag).append("(").append(" );");

        sb.append("\n\n").append(listenerHeader);
        String cj = cjson + "," + cjson + "," + cjson;
        String listener = "private CURLinstener<" + cj + "> mListener = new CURLinstener<" + cj + ">() {\n" +
                "        @Override\n" +
                "        public void onRetrieveSuccess(" + cjson + " json) {\n" +
                "            mView.hideProgress();\n" +
                "            Logger.d(\"getClass().getSimpleName() -> "+ ctag + " : %s\",json.toString());"+
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void onUpdateSuccess(" + cjson + " json) {\n" +
                "            mView.hideProgress();\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void onCreateSuccess(" + cjson + " json) {\n" +
                "            mView.hideProgress();\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void onError(Throwable e) {\n" +
                "            mView.hideProgress();\n" +
                "            Logger.e(\"getClass().getSimpleName() -> "+ ctag + " : %s\",e.toString());"+
                "        }\n" +
                "    };";

        sb.append(listener).append("\n");
        return sb.toString();
    }

    public String getCode(String method, String tag, String url) {
        StringBuilder builder = new StringBuilder("\n");
        String apiService = getApiService(method, tag, url);
        builder.append(apiService);

        builder.append("\n\n");
        String apiManger = getApiManager(method, tag);
        builder.append(apiManger);

        builder.append("\n\n");
        String model = getModel(method, tag);
        builder.append(model);

        builder.append("\n\n");
        String view = getViewCode(method, tag);
        builder.append(view);

        builder.append("\n\n");
        String presenter = getPresenterCode(method, tag);
        builder.append(presenter);


        return builder.toString();
    }
}

package me.kingtux.tuxmvc.simple.impl;

import io.javalin.Context;
import me.kingtux.tmvc.core.request.HTTPCode;
import me.kingtux.tmvc.core.request.Request;
import me.kingtux.tmvc.core.request.RequestType;
import me.kingtux.tmvc.core.request.UploadedFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleRequest implements Request {
    private Context context;
    private RequestType type;
    private boolean responded = false;
    private String mimeType = "text/html";

    public SimpleRequest(Context context) {
        this.context = context;
    }

    public SimpleRequest(Context context, RequestType requestType) {
        this(context);
        type = requestType;

    }

    @Override
    public String responseType() {
        return mimeType;
    }

    @Override
    public void responseType(String s) {
        this.mimeType = s;
    }

    @Override
    public Map<String, String> queryParam() {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, List<String>> st : context.queryParamMap().entrySet()) {
            map.put(st.getKey(), st.getValue().get(0));
        }
        return map;
    }

    @Override
    public String queryParam(String key, String def) {
        return context.queryParam(key, def);
    }

    @Override
    public Map<String, String> formParam() {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, List<String>> st : context.formParamMap().entrySet()) {
            map.put(st.getKey(), st.getValue().get(0));
        }

        return map;
    }

    @Override
    public String cookie(String s) {
        return context.cookie(s);
    }

    @Override
    public void cookie(String s, String s1) {
        context.cookie(s, s1);
    }

    @Override
    public void cookie(String s, String s1, int i) {
        context.cookie(s, s1, i);
    }

    @Override
    public void removeCookie(String s) {
        context.removeCookie(s);
    }

    @Override
    public void clearCookie() {
        context.clearCookieStore();
    }

    @Override
    public RequestType getRequestType() {
        return type;
    }

    @Override
    public int status() {
        return context.status();
    }

    @Override
    public void status(int i) {
        context.status(i);
    }

    @Override
    public void status(HTTPCode httpCode) {
        status(httpCode.getCode());
    }

    @Override
    public Map<String, String> header() {
        return context.headerMap();
    }

    @Override
    public void redirect(String s, int i) {
        if (responded) return;
        responded = true;
        context.redirect(s, i);
    }

    @Override
    public String pathParam(String s) {
        return context.pathParam(s);
    }

    @Override
    public String path() {
        return context.path();
    }

    @Override
    public String[] pathSplit() {
        return context.path().split("/");
    }

    @Override
    public int port() {
        return context.port();
    }

    @Override
    public String ip() {
        return context.ip();
    }

    @Override
    public String url() {
        return context.url();
    }

    @Override
    public void respond(String s) {
        context.contentType(mimeType).result(s);
    }


    @Override
    public boolean hasResponded() {
        return responded;
    }

    @Override
    public List<UploadedFile> getUploadedFiles(String s) {
            return context.uploadedFiles(s).stream().map(SimpleUploadedFile::new).collect(Collectors.toList());
    }
}

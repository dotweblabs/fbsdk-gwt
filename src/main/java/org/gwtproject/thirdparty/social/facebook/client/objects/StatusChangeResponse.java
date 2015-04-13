package org.gwtproject.thirdparty.social.facebook.client.objects;

import com.google.gwt.core.client.JavaScriptObject;

public class StatusChangeResponse extends JavaScriptObject {

    public final static String NOT_AUTHORIZED = "not_authorized";

    protected StatusChangeResponse(){}

    public final native String getAccessToken()/*-{
        return authResponse.accessToken;
    }-*/;

    public final native String getSignedRequest()/*-{
        return authResponse.signedRequest;
    }-*/;
    public final native String getStatus()/*-{
        return status;
    }-*/;
}

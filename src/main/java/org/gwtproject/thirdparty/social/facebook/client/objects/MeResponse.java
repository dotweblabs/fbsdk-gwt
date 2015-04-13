package org.gwtproject.thirdparty.social.facebook.client.objects;

import com.google.gwt.core.client.JavaScriptObject;

public class MeResponse extends JavaScriptObject {

    protected MeResponse(){}

    public native String getId()/*-{
        return id;
    }-*/;
}

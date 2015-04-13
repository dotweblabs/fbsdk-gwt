package org.gwtproject.thirdparty.social.facebook.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FBEvent {

    public static final String AUTH_STATUS_CHANGE = "auth.statusChange";
    public static final String NOT_AUTHORIZED = "not_authorized";

    public native void unsubscribe(String event, AsyncCallback<JavaScriptObject> callback)/*-{
        $wnd.FB.Event.unsubscribe(event, handleResponse);
        var handleResponse = function(response) {
                if(response){
                    @org.gwtproject.social.facebook.client.FBEvent::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                } else {
                    @org.gwtproject.social.facebook.client.FBEvent::callbackFailure(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                }
        };
    }-*/;

    public native void subscribe(String event, AsyncCallback<JavaScriptObject> callback)/*-{
            $wnd.FB.Event.subscribe(event, function(response) {
                if(response){
                    @org.gwtproject.social.facebook.client.FBEvent::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                } else {
                    @org.gwtproject.social.facebook.client.FBEvent::callbackFailure(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                }
            });
    }-*/;

    @SuppressWarnings("unused")
    protected void callbackSuccess(JavaScriptObject response, AsyncCallback<JavaScriptObject> callback){
        callback.onSuccess(response);
    }

    @SuppressWarnings("unused")
    protected void callbackFailure(JavaScriptObject response, AsyncCallback<JavaScriptObject> callback){
        String error = String.valueOf(response);
        callback.onFailure(new RuntimeException(error));
    }
}

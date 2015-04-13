package org.gwtproject.thirdparty.social.facebook.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gwtproject.thirdparty.social.facebook.client.callback.LoginCallback;

@SuppressWarnings("unused")
public class FB {

    /**
     * Constants
     */
    public static final String AUTH_STATUS_CHANGE = "auth.statusChange";
    public static final String NOT_AUTHORIZED = "not_authorized";
    private static final String FACEBOOK_DOMAIN = "facebook";

    private static String _appId;
    private static boolean initialized = false;
    private static String accessToken;
    private static String signedRequest;
    private static String userId;
    private static LoginCallback loginCallback;

    public static void init(final String appId){
        _appId = appId;
        _init(appId);
        unsubscribeAuthStatusChange();
        subscribeAuthStatusChange();
    }

    public static void login(LoginCallback callback){
        loginCallback = callback;
        _login();
    }

    private static void onLogout(){
        loginCallback.onLogout();
        initialized = false;
    }

    private static void onConnected(String accessToken){
        FB.accessToken = accessToken;
        loginCallback.onLogin(accessToken);
    }

    private static void onAuthorizationFailed(){
        loginCallback.onAuthorizationFailed();
    }

    private static void onNotLoggedIn(){
        loginCallback.onNotLoggedIn();
    }

    private static native void subscribeAuthStatusChange()/*-{
            // listen for and handle auth.statusChange events
            $wnd.FB.Event.subscribe('auth.statusChange', function(response) {
              if (response.authResponse) {
                $wnd.FB.api('/me', function(me){
                    @org.gwtproject.thirdparty.social.facebook.client.FB::userId = me.id;
                    @org.gwtproject.thirdparty.social.facebook.client.FB::signedRequest = response.authResponse.signedRequest;
                    @org.gwtproject.thirdparty.social.facebook.client.FB::onConnected(Ljava/lang/String;)(response.authResponse.accessToken);
                })
              } else if (response.status === 'not_authorized') {
                    @org.gwtproject.thirdparty.social.facebook.client.FB::onAuthorizationFailed()();
              } else {
                    @org.gwtproject.thirdparty.social.facebook.client.FB::onNotLoggedIn()();
              }
            });
    }-*/;

    private static native void unsubscribeAuthStatusChange()/*-{
        $wnd.FB.Event.unsubscribe('auth.statusChange', handleResponse);
        var handleResponse = function(response) {
            // do something
        };
    }-*/;

    private static native void _login()/*-{
                    $wnd.FB.login(function(response) {
                        if (response.authResponse) {
                               // do nothing to prevent multiple events being fired
                        } else {
                             @org.gwtproject.thirdparty.social.facebook.client.FB::onAuthorizationFailed()();
                        }
                    });
    }-*/;

    public static native void logout()/*-{
        $wnd.FB.getLoginStatus(function(response) {
            if (response.status === 'connected') {
                $wnd.FB.logout(function(response) {
                     @org.gwtproject.thirdparty.social.facebook.client.FB::onLogout()();
                });
            }
        })
    }-*/;

    private static native void _init(String _appId)/*-{
        $wnd.FB.init({
            appId      : _appId,
            cookie     : true,  // enable cookies to allow the server to access
                                // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.2' // use version 2.2
        });
    }-*/;




    public native void unsubscribe(String event, AsyncCallback<JavaScriptObject> callback)/*-{
        $wnd.FB.Event.unsubscribe(event, handleResponse);
        var handleResponse = function(response) {
                if(response){
                    @org.gwtproject.thirdparty.social.facebook.client.FB::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                } else {
                    @org.gwtproject.thirdparty.social.facebook.client.FB::callbackFailure(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                }
        };
    }-*/;

    public native void subscribe(String event, AsyncCallback<JavaScriptObject> callback)/*-{
            $wnd.FB.Event.subscribe(event, function(response) {
                if(response){
                    @org.gwtproject.thirdparty.social.facebook.client.FB::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                } else {
                    @org.gwtproject.thirdparty.social.facebook.client.FB::callbackFailure(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                }
            });
    }-*/;


    /**
     * Common success callback method
     *
     * @param callback
     * @param response
     */
    @SuppressWarnings("unused")
    protected static void callbackSuccess(AsyncCallback<JavaScriptObject> callback, JavaScriptObject response){
        callback.onSuccess(response);
        log("Callback success finish");
    }

    /**
     * Common failure callback method
     *
     * @param callback
     * @param response
     */
    @SuppressWarnings("unused")
    protected static void callbackFailure(AsyncCallback<JavaScriptObject> callback, JavaScriptObject response){
        String error = String.valueOf(response);
        callback.onFailure(new RuntimeException(error));
        log("Callback failure finish");
    }

    private static native void log(String message)/*-{
        $wnd.console.log(message);
    }-*/;


    public static String getAccessToken() {
        return accessToken;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getSignedRequest() {
        return signedRequest;
    }
}

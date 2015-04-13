package org.gwtproject.thirdparty.social.facebook.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gwtproject.thirdparty.social.facebook.client.callback.LoginCallback;

@SuppressWarnings("unused")
public class FBCore {

    private static final String FACEBOOK_DOMAIN = "facebook";
    private static String _appId;
    private static boolean initialized = false;
    private static String accessToken;
    private static String signedRequest;
    private static String userId;

    private static LoginCallback loginCallback;

    public boolean isInitialized(){
        return initialized;
    }

    public void init(final String appId){
        _appId = appId;
        _init(appId);
        initialized = true;
    }

    public void login(LoginCallback callback){
        loginCallback = callback;
        _login();
    }

    private void onLogout(){
        loginCallback.onLogout();
        initialized = false;
    }

    private void onConnected(String accessToken){
        FBCore.accessToken = accessToken;
        loginCallback.onLogin(accessToken);
    }

    private void onAuthorizationFailed(){
        loginCallback.onAuthorizationFailed();
    }

    private void onNotLoggedIn(){
        loginCallback.onNotLoggedIn();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getSignedRequest() {
        return signedRequest;
    }

    public static native void api(String path, AsyncCallback callback)/*-{
            $wnd.FB.api(path, function(response){
                  if(response) {
                    @org.gwtproject.social.facebook.client.FBCore::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                  } else {
                    @org.gwtproject.social.facebook.client.FBCore::callbackFailure(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, response);
                  }
            });
    }-*/;

    @Deprecated
    private static native void listenFBEvent()/*-{
            $wnd.FB.Event.subscribe('auth.statusChange', function(response) {
              if (response.authResponse) {
                // user has auth'd your app and is logged into Facebook
                $wnd.FB.api('/me', function(me){
                  @org.gwtproject.social.facebook.client.FBCore::userId = me.id;
                  @org.gwtproject.social.facebook.client.FBCore::signedRequest = response.authResponse.signedRequest;
                  @org.gwtproject.social.facebook.client.FBCore::onConnected(Ljava/lang/String;)(response.authResponse.accessToken);
                })
              } else if (response.status === 'not_authorized') {
                @org.gwtproject.social.facebook.client.FBCore::onAuthorizationFailed()();
              } else {
                // user has not auth'd your app, or is not logged into Facebook
                @org.gwtproject.social.facebook.client.FBCore::onNotLoggedIn()();
              }
            });
    }-*/;

    private static native void _login()/*-{
                    $wnd.FB.login(function(response) {
                        if (response.authResponse) {
                                $wnd.FB.api('/me', function(response) {
                                    @org.gwtproject.social.facebook.client.FBCore::userId = response.id;
                                });
                        } else {
                            //console.log('User cancelled login or did not fully authorize.');
                            @org.gwtproject.social.facebook.client.FBCore::onAuthorizationFailed()();
                        }
                    });
    }-*/;

    public static native void logout()/*-{
        $wnd.FB.getLoginStatus(function(response) {
            if (response.status === 'connected') {
                $wnd.FB.logout(function(response) {
                    @org.gwtproject.social.facebook.client.FBCore::onLogout()();
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

    private static native void _init(String _appId, boolean _cookie, boolean _xfbml, String _version)/*-{
        $wnd.FB.init({
            appId      : _appId,
            cookie     : _cookie,   // enable cookies to allow the server to access
                                    // the session
            xfbml      : _xfbml,    // parse social plugins on this page
            version    : _version   // use version 2.2
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

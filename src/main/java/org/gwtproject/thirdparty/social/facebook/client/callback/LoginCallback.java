package org.gwtproject.thirdparty.social.facebook.client.callback;

public interface LoginCallback {
    public void onLogin(String token);
    public void onLogout();
    public void onAuthorizationFailed();
    public void onNotLoggedIn();
    public void onFailure(String error);
}

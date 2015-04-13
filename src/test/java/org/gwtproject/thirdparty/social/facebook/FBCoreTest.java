package org.gwtproject.thirdparty.social.facebook;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gwtproject.thirdparty.social.facebook.client.FBCore;
import org.gwtproject.thirdparty.social.facebook.client.FBEvent;
import org.gwtproject.thirdparty.social.facebook.client.callback.LoginCallback;
import org.gwtproject.thirdparty.social.facebook.client.objects.MeResponse;
import org.gwtproject.thirdparty.social.facebook.client.objects.StatusChangeResponse;

/**
 *
 * Unit tests of {@link FBCoreTest}
 */
public class FBCoreTest extends GWTTestCase {

    private static final String TEST_APP_ID = "123";

    @Override
    public String getModuleName() {
        return "org.gwtproject.social.facebook.Facebook";
    }

    public void testFBCoreInit(){
        FBCore fbCore = new FBCore();
        fbCore.init(TEST_APP_ID);
    }

    public void testLogin(){
        FBCore fbCore = new FBCore();
        fbCore.init(TEST_APP_ID);

        fbCore.login(new LoginCallback() {
            @Override
            public void onLogin(String token) {

            }

            @Override
            public void onLogout() {

            }

            @Override
            public void onAuthorizationFailed() {

            }

            @Override
            public void onNotLoggedIn() {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    public void testLogout(){
        FBCore fbCore = new FBCore();
        fbCore.init(TEST_APP_ID);
        fbCore.logout();
    }

    public void testListenAuthStatusChange(){
        FBEvent event = new FBEvent();
        event.subscribe(FBEvent.AUTH_STATUS_CHANGE, new AsyncCallback<JavaScriptObject>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(JavaScriptObject response) {
                if(response != null){
                    StatusChangeResponse statusChangeResponse = (StatusChangeResponse) response;
                    if(statusChangeResponse != null){
                        String signedRequest = statusChangeResponse.getSignedRequest();
                    } else if(statusChangeResponse.getStatus().equals(StatusChangeResponse.NOT_AUTHORIZED)){

                    } else {

                    }
                }
            }
        });
    }

    public void testMeApi(){
        FBCore fbCore = new FBCore();
        fbCore.init(TEST_APP_ID);

        fbCore.api("/me", new AsyncCallback<MeResponse>(){
            @Override
            public void onFailure(Throwable throwable) {

            }
            @Override
            public void onSuccess(MeResponse response) {
                response.getId();
            }
        });
    }

}

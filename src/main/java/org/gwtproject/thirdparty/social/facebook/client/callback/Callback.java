package org.gwtproject.thirdparty.social.facebook.client.callback;

import com.google.gwt.core.client.GWT;

public interface Callback<T> {

    public void onFailure(Throwable caught);

    public void onSuccess(T result);

}
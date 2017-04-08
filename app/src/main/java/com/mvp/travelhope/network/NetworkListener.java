package com.mvp.travelhope.network;


/**
 * Interface which is used to update UI after Network operation has finished
 */
public interface NetworkListener<T> {
    /**
     * Callback method called after Network Operation finish
     */
    void onSuccess(T response, int reqType);

    void onError(String response, int reqType);


}

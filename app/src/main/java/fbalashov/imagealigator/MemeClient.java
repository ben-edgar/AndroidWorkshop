package fbalashov.imagealigator;

import android.app.Application;

import okhttp3.OkHttpClient;


public class MemeClient {


    private static final String clientId = "99a8cba633b8ee3";
    private static final String imgurUrl = "https://api.imgur.com/3/memegen/defaults";

    public MemeClient(OkHttpClient client, Application context, MemeHandler handler) {

    }

    public void getMeme(final String imageId) {

    }

    public interface MemeHandler {
        void returnMeme(String link);
    }
}

package fbalashov.imagealigator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MemeClient {

    private static final String clientId = "99a8cba633b8ee3";
    private static final String imgurUrl = "https://api.imgur.com/3/memegen/defaults";

    private OkHttpClient client;
    private MemeHandler handler;

    public MemeClient(OkHttpClient client, MemeHandler handler) {
        this.client = client;
        this.handler = handler;

    }

    public void getMeme(final String imageId) {
        Request getRequest = new Request.Builder()
                .url(imgurUrl)
                .addHeader("Authorization", "Client-ID " + clientId)
                .build();

        client.newCall(getRequest).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    JSONArray array = object.getJSONArray("data");
                    final JSONObject memeData = array.getJSONObject(Integer.parseInt(imageId));
                    String link = memeData.getString("link");
                    handler.returnMeme(link);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface MemeHandler {
        void returnMeme(String link);
    }
}

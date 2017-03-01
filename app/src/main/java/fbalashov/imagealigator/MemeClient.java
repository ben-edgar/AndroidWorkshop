package fbalashov.imagealigator;

import android.app.Activity;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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


  private static final String CLIENT_ID = "99a8cba633b8ee3";
  private static final String IMGUR_URL = "https://api.imgur.com/3/memegen/defaults";

  private final OkHttpClient client;

  public MemeClient() {
    this.client = new OkHttpClient();
  }

  /**
   * Fetches a meme from imgur and displays it within the supplied image view.
   *
   * @param imageId   the number of the meme
   * @param activity  the activity that will display the meme
   * @param imageView the image view that will hold the meme
   */
  public void displayMeme(final String imageId, final Activity activity, final ImageView imageView) {
    Request getRequest = new Request.Builder()
        .url(IMGUR_URL)
        .addHeader("Authorization", "Client-ID " + CLIENT_ID)
        .build();

    client.newCall(getRequest).enqueue(new Callback() {
      Handler handler = new Handler(activity.getMainLooper());

      @Override
      public void onFailure(Call call, IOException e) {
        e.printStackTrace();

        handler.post(new Runnable() {
          @Override
          public void run() {
            Toast.makeText(activity, "Could not get meme", Toast.LENGTH_SHORT).show();
          }
        });
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        if (!response.isSuccessful()) {
          throw new IOException("Unexpected response code: " + response);
        }

        try {
          JSONObject object = new JSONObject(response.body().string());
          JSONArray array = object.getJSONArray("data");
          final JSONObject memeData = array.getJSONObject(Integer.parseInt(imageId));
          final String link = memeData.getString("link");
          handler.post(new Runnable() {
            @Override
            public void run() {
              Toast.makeText(activity, "Response was successful", Toast.LENGTH_SHORT).show();
              Picasso.with(activity).load(link).into(imageView);
            }
          });
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }

}

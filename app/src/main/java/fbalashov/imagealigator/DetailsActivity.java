package fbalashov.imagealigator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import okhttp3.OkHttpClient;

public class DetailsActivity extends AppCompatActivity implements MemeClient.MemeHandler{
  public static final String IMAGE_ID_EXTRA = "IMAGE_ID";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    String imageId = getIntent().getExtras().get(IMAGE_ID_EXTRA).toString();
    TextView textView = (TextView)findViewById(R.id.post_id);
    textView.setText(imageId);

    MemeClient mMemeClient = new MemeClient(new OkHttpClient(), this.getApplication(), this);
    mMemeClient.getMeme(imageId);
  }

  @Override
  public void returnMeme(final String link) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast toast = Toast.makeText(DetailsActivity.this, link, Toast.LENGTH_SHORT);
        toast.show();
      }
    });
  }
}

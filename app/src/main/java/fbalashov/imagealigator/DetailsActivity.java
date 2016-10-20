package fbalashov.imagealigator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import okhttp3.OkHttpClient;

public class DetailsActivity extends AppCompatActivity implements MemeClient.MemeHandler{
  public static final String IMAGE_ID_EXTRA = "IMAGE_ID";

  private ImageView mImageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    String imageId = getIntent().getExtras().get(IMAGE_ID_EXTRA).toString();
    TextView textView = (TextView)findViewById(R.id.post_id);
    mImageView = (ImageView)findViewById(R.id.imageView);
    textView.setText(imageId);

    MemeClient mMemeClient = new MemeClient(new OkHttpClient(), this.getApplication(), this);
    mMemeClient.getMeme(imageId);
  }

  @Override
  public void returnMeme(final String link) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Picasso.with(DetailsActivity.this).load(link).into(mImageView);
      }
    });
  }
}

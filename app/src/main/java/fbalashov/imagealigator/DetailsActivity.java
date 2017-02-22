package fbalashov.imagealigator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
  public static final String IMAGE_ID_EXTRA = "IMAGE_ID";

  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    String imageId = getIntent().getExtras().get(IMAGE_ID_EXTRA).toString();
    TextView textView = (TextView) findViewById(R.id.post_id);
    imageView = (ImageView) findViewById(R.id.imageView);
    textView.setText(imageId);

    MemeClient mMemeClient = new MemeClient();
    mMemeClient.displayMeme(imageId, this, imageView);
  }
}
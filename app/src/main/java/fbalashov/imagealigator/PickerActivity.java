package fbalashov.imagealigator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PickerActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_picker);
    Button button = (Button)findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText editText = (EditText)findViewById(R.id.img_id_view);
        Intent newIntent = new Intent(PickerActivity.this, DetailsActivity.class);
        newIntent.putExtra(DetailsActivity.IMAGE_ID_EXTRA, editText.getText());
        startActivity(newIntent);
        Toast.makeText(PickerActivity.this, "It's a trap!", Toast.LENGTH_SHORT).show();
      }
    });
  }
}

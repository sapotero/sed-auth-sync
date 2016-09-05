package sapotero.sed.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import sapotero.sed.Fragment.SyncSettings;
import sapotero.sed.R;

public class SyncSettingsActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.single_frame);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    if (savedInstanceState == null) {
      getFragmentManager()
          .beginTransaction()
          .add(R.id.root_frame, new SyncSettings())
          .commit();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

}

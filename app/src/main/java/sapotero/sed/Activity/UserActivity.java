package sapotero.sed.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;

import sapotero.sed.R;

public class UserActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
    new DrawerBuilder()
        .withActivity(this)
        .withToolbar(toolbar)
        .withActionBarDrawerToggle(true)
        .withHeader(R.layout.drawer_header)
        .addDrawerItems(
            new PrimaryDrawerItem().withName(  R.string.drawer_item_home).withIcon(GoogleMaterial.Icon.gmd_format_italic).withBadge("99").withIdentifier(1),
            new PrimaryDrawerItem().withName(  R.string.drawer_item_free_play).withIcon(GoogleMaterial.Icon.gmd_format_italic),
            new PrimaryDrawerItem().withName(  R.string.drawer_item_custom).withIcon(GoogleMaterial.Icon.gmd_format_italic).withBadge("6").withIdentifier(2),
            new SectionDrawerItem().withName(  R.string.drawer_item_settings),
            new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(GoogleMaterial.Icon.gmd_format_italic),
            new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(GoogleMaterial.Icon.gmd_format_italic),
            new DividerDrawerItem(),
            new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(GoogleMaterial.Icon.gmd_format_italic).withBadge("12+").withIdentifier(1)
        )
        .build();
  }
}

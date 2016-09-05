package sapotero.sed.Sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncService extends Service {

  private static SyncAdapter sSyncAdapter;

  @Override
  public void onCreate() {
    super.onCreate();
    if (sSyncAdapter == null) {
      synchronized (SyncAdapter.class) {
        sSyncAdapter = new SyncAdapter(getApplicationContext());
      }
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return sSyncAdapter.getSyncAdapterBinder();
  }

}
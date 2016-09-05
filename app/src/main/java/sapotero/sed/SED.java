package sapotero.sed;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.ContentResolver;
import android.os.Bundle;

public class SED extends Application {

  public static final String ACCOUNT_TYPE = "sapotero.sed";

  public static final String AUTHORITY = "sapotero.sed.documents";

  public static Account sAccount;

  @Override
  public void onCreate() {
    super.onCreate();
    final AccountManager am = AccountManager.get(this);
    if (sAccount == null) {
      sAccount = new Account(getString(R.string.documents), ACCOUNT_TYPE);
    }
    if (am.addAccountExplicitly(sAccount, getPackageName(), new Bundle())) {
      ContentResolver.setSyncAutomatically(sAccount, AUTHORITY, true);
    }
  }

}
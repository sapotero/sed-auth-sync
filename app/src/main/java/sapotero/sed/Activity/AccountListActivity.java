package sapotero.sed.Activity;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import sapotero.sed.Account.SedAccount;
import sapotero.sed.Fragment.AccountList;
import sapotero.sed.R;

public class AccountListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.single_frame);
    final AccountManager am = AccountManager.get(this);
    if (am.getAccountsByType(SedAccount.TYPE).length == 0) {
      addNewAccount(am);
    }
    if (savedInstanceState == null) {
      getFragmentManager()
          .beginTransaction()
          .add(R.id.root_frame, new AccountList())
          .commit();
    }
  }

  private void addNewAccount(AccountManager am) {
    am.addAccount(SedAccount.TYPE, SedAccount.TOKEN_FULL_ACCESS, null, null, this,
        new AccountManagerCallback<Bundle>() {
          @Override
          public void run(AccountManagerFuture<Bundle> future) {
            try {
              future.getResult();
            } catch (OperationCanceledException | IOException | AuthenticatorException e) {
              AccountListActivity.this.finish();
            }
          }
        }, null
    );
  }

}

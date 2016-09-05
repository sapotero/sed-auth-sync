package sapotero.sed.Activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.UUID;

import sapotero.sed.Fragment.LoginForm;
import sapotero.sed.R;
import sapotero.sed.Struct.Login.AuthToken;


public class LoginActivity  extends AccountAuthenticatorActivity {

  public static final String EXTRA_TOKEN_TYPE = "sapotero.sed.EXTRA_TOKEN_TYPE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.single_frame);
    if (savedInstanceState == null) {
      getFragmentManager()
          .beginTransaction()
          .replace(R.id.root_frame, new LoginForm())
          .commit();
    }
  }

  public void onTokenReceived(Account account, String password, String token) {
    final AccountManager am = AccountManager.get(this);
    final Bundle result = new Bundle();
    if (am.addAccountExplicitly(account, password, new Bundle())) {
      result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
      result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
      result.putString(AccountManager.KEY_AUTHTOKEN, token);
      am.setAuthToken(account, account.type, token);
    } else {
      result.putString(AccountManager.KEY_ERROR_MESSAGE, getString(R.string.account_already_exists));
    }
    setAccountAuthenticatorResult(result);
    setResult(RESULT_OK);
    finish();
  }

//  public void tryToLogin(View view){
//
//    final Intent intent = new Intent(this, UserActivity.class);
//    final Context context= this;
//
//    progressDialog = new ProgressDialog(LoginActivity.this);
//    progressDialog.setIndeterminate(true);
//    progressDialog.setMessage("Authenticating...");
//    progressDialog.show();
//
//    EditText usernameField = (EditText) findViewById(R.id.username);
//    final String username  = usernameField.getText().toString();
//
//    EditText passwordField = (EditText) findViewById(R.id.password);
//    final String password  = passwordField.getText().toString();
//
//
//    new android.os.Handler().postDelayed(
//        new Runnable() {
//          public void run() {
//            sendLoginRequest( username, password );
//          }
//        }, 1000);
//
//
//  }
//
//  public void sendLoginRequest( final String username, String password){
//    final Intent intent = new Intent(this, UserActivity.class);
//    final Context context= this;
//
//    RequestQueue queue = Volley.newRequestQueue(this);
//
//    Uri.Builder builder = new Uri.Builder();
//    builder.scheme("http")
//        .authority( "mobile.esd.n-core.ru" )
//        .appendPath("token")
//        .appendPath( String.format("%s.json", username) )
//        .appendQueryParameter("password", password);
//
//    String url = builder.build().toString();
//
//    StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
//        new Response.Listener<String>() {
//
//          @Override
//          public void onResponse(String response) {
//
////            intent.putExtra( EXTRA_MESSAGE, token.getToken() );
////            intent.putExtra( USER_MESSAGE, username);
//            AuthToken token = new Gson().fromJson(response, AuthToken.class);
//            progressDialog.dismiss();
//            startActivity(intent);
//            onLoginSuccess();
//          }
//        }, new Response.ErrorListener() {
//
//      @Override
//      public void onErrorResponse(VolleyError error) {
//        progressDialog.dismiss();
//        Toast.makeText( context, "That didn't work!", Toast.LENGTH_SHORT).show();
//      }
//    });
//    queue.add(stringRequest);
//  }
//
//  public void onLoginSuccess() {
//    finish();
//  }
//
//  public static String getUniqueID() {
//    String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
//
//    String serial = null;
//    try {
//      serial = android.os.Build.class.getField("SERIAL").get(null).toString();
//      return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
//    } catch (Exception exception) {
//      serial = "serial";
//    }
//
//    return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
//  }


}

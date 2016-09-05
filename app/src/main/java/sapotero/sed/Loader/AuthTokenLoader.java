package sapotero.sed.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import sapotero.sed.R;

public class AuthTokenLoader  extends AsyncTaskLoader<String> {

  private final String mObtainTokenUrl;

  private final String mLogin;

  private final String mPassword;

  private String mAuthToken;

  public AuthTokenLoader(Context context, String login, String password) {
    super(context);
    mObtainTokenUrl = context.getString(R.string.token_url, login, password);
    mLogin = login;
    mPassword = password;
  }

  public static String signIn(Context context, String login, String password) {
    try {
      return new AuthTokenLoader(context, login, password).signIn();
    } catch (IOException e) {
      Log.e(AuthTokenLoader.class.getSimpleName(), e.getMessage(), e);
    }
    return null;
  }

  @Override
  protected void onStartLoading() {
    if (TextUtils.isEmpty(mAuthToken)) {
      forceLoad();
    } else {
      deliverResult(mAuthToken);
    }
  }

  @Override
  public void deliverResult(String data) {
    mAuthToken = data;
    super.deliverResult(data);
  }

  @Override
  public String loadInBackground() {
    try {
      return signIn();
    } catch (IOException e) {
      Log.e(AuthTokenLoader.class.getSimpleName(), e.getMessage(), e);
    }
    return null;
  }

  private String signIn() throws IOException {
    final HttpURLConnection cn = (HttpURLConnection) new URL(mObtainTokenUrl).openConnection();
    cn.setRequestMethod("PUT");
    cn.addRequestProperty("Accept", "application/json");
    sendBody(cn);
    return readToken(cn);
  }

  private void sendBody(HttpURLConnection cn) throws IOException {
    final JSONObject body = new JSONObject();
    try {
      body.put("client_secret", "SedApp.CLIENT_SECRET");
      body.put("scopes", new JSONArray(Arrays.asList("repo")));
      final byte[] data = body.toString().getBytes();
      cn.setDoOutput(true);
      cn.setFixedLengthStreamingMode(data.length);
      cn.setRequestProperty("Content-Type", "application/json");
      final OutputStream out = new BufferedOutputStream(cn.getOutputStream());
      try {
        out.write(data);
      } finally {
        IOUtils.closeQuietly(out);
      }
    } catch (JSONException e) {
      Log.e(AuthTokenLoader.class.getSimpleName(), e.getMessage(), e);
    }
  }

  private String readToken(HttpURLConnection cn) throws IOException {
    final InputStream in = new BufferedInputStream(cn.getInputStream());
    try {
      final JSONObject json = new JSONObject(IOUtils.toString(in));


      if (json.has("auth_token")) {
        Log.v( "JSONObject", json.getString("auth_token") );
        return json.getString("auth_token");
      }
    } catch (JSONException e) {
      Log.e(AuthTokenLoader.class.getSimpleName(), e.getMessage(), e);
    } finally {
      IOUtils.closeQuietly(in);
    }
    return null;
  }

}

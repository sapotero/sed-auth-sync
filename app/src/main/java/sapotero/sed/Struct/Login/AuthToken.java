package sapotero.sed.Struct.Login;

import com.google.gson.annotations.SerializedName;

public class AuthToken {
  @SerializedName("auth_token")
  private String token;

  public String getToken() {
    return token;

  }
}

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class APITest {

    final String BASE_URL = "https://postman-echo.com";
    final String BASIC_AUTH = "/basic-auth";
    final String DIGEST_AUTH = "/digest-auth";

    @Test
    public void testBasicAuthPositive() {

        final String authKey = "Basic cG9zdG1hbjpwYXNzd29yZA==";
        
        Response response = null;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + BASIC_AUTH)
                .method("GET", null)
                .addHeader("Authorization", authKey)
                .build();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(200, response.code());
    }

    @Test
    public void testBasicAuthNegative() {

        final String authKey = "wrong key";

        Response response = null;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + BASIC_AUTH)
                .method("GET", null)
                .addHeader("Authorization", authKey)
                .build();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotEquals(200, response.code());

    }

    @Test
    public void testDigestAuthPositive() {

        Response response = null;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + DIGEST_AUTH)
                .method("GET", null)
                .addHeader("Authorization", "Digest username=\"postman\", " +
                        "realm=\"Users\", nonce=\"ni1LiL0O37PRRhofWdCLmwFsnEtH1lew\", " +
                        "uri=\"/digest-auth\", " +
                        "response=\"254679099562cf07df9b6f5d8d15db44\"")
                .build();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(200, response.code());

    }

    @Test
    public void testDigestAuthNegative() {

        Response response = null;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + DIGEST_AUTH)
                .method("GET", null)
                .addHeader("Authorization", "Digest username=\"postman\", " +
                        "realm=\"Users\", nonce=\"bad_string\", " +
                        "uri=\"/digest-auth\", " +
                        "response=\"254679099562cf07df9b6f5d8d15db44\"")
                .build();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotEquals(200, response.code());

    }




}

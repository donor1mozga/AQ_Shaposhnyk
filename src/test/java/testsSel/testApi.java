package testsSel;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class testApi {
    public static void main(String[] args) throws IOException {
//        final String payload = "ла ла ла";
        final String JiraKey = "AQ-38";
        final String creds = "donor1mozga:Wx5u4wWNQqP8Lkp";


        final String urlStr = "https://jira.ithillel.com/rest/api/2/issue/" + JiraKey;
        final URL urlObj = new URL(urlStr);
        final HttpsURLConnection httpCon = (HttpsURLConnection) urlObj.openConnection();
//        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("GET");
        httpCon.setRequestProperty("Authorization", "Basic " + new Base64().encodeToString(creds.getBytes()));
//        httpCon.setRequestProperty("");

//        final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
//        out.write(payload);
//        out.close();

        final StringBuilder result = new StringBuilder();

        BufferedReader rd;

        try {
            rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        } catch (Exception e) {
            rd = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
        }

        String line;

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }


        final JSONObject response = new JSONObject(result.toString());

        final int issueId = response.getInt("id");
        final String issueTypeName = response.getJSONObject("fields").getJSONObject("issuetype").getString("name");
        System.out.println(issueId);
        System.out.println(issueTypeName);

    }
}

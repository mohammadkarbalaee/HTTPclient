import java.lang.System;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;

class MyRequest {

    public static void main(String[] args){
        try{
            URL url = new URL("http://localhost:8080/raw");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            byte[] out = "{\"document_url\": \"http://bayanbox.ir/view/5067853395275628881/boute.pdf\",\"fix_orientation\": true,\"word_positions\": false,\"type\": \"general\",\"wait\": true}"
                .getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "multipart/form-data");
            http.setRequestProperty("Authorization", "Token 7c990ee0c9aa0ddd7307d9bdaa76ac8d682be233");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        }

        catch(Exception e){
            System.err.println(e);
        }
    }
}
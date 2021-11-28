import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class MultiPart2 {
  public static void main(String[] args) throws IOException {

    String url = "https://alefba.roshan-ai.ir/api/read_document/";
    String charset = "UTF-8";
    File textFile = new File("YOUR FILE PATH");
    String boundary = Long.toHexString(System.currentTimeMillis());
    String CRLF = "\r\n";

    URLConnection connection = new URL(url).openConnection();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    connection.setRequestProperty("Authorization", "Token TOKEN_KEY");
    connection.setRequestProperty("Connection", "close");
    try (
        OutputStream output = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
    ) {
      writer.append("--").append(boundary).append(CRLF);
      writer.append("Content-Disposition: form-data; name=\"document\"; filename=\"").append(textFile.getName()).append("\"").append(CRLF);
      writer.append("Content-Type: application/pdf").append(CRLF);
      writer.append(CRLF).flush();
      Files.copy(textFile.toPath(), output);
      output.flush();
      writer.append(CRLF).flush();
      writer.append("--").append(boundary).append("--").append(CRLF).flush();
    }

    BufferedReader inputStream = new BufferedReader(new InputStreamReader((InputStream) connection.getContent()));
    String inputLine;
    while ((inputLine = inputStream.readLine()) != null){
      System.out.println(inputLine);
    }
    inputStream.close();
  }
}

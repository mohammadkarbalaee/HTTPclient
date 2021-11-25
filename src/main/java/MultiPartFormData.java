import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.lang.System;

public class MultiPartFormData {
  public static void main(String[] args) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadFile = new HttpPost("https://alefba.roshan-ai.ir/api/read_document/");
    uploadFile.setHeader("Content-Type","multipart/form-data; boundary=---something");
    uploadFile.setHeader("Authorization", "Token 7c990ee0c9aa0ddd7307d9bdaa76ac8d682be233");
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    File pdfFile = new File("C:\\Users\\Muhammad\\Desktop\\projects\\docs\\apiCodes\\alefba\\boute.pdf");
    builder.addBinaryBody("document", new FileInputStream(pdfFile), ContentType.APPLICATION_OCTET_STREAM, pdfFile.getName());
    HttpEntity multipart = builder.build();
    uploadFile.setEntity(multipart);
    CloseableHttpResponse response = httpClient.execute(uploadFile);
    HttpEntity responseEntity = response.getEntity();
    BufferedReader inputStream = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
    String inputLine;
    while ((inputLine = inputStream.readLine()) != null){
      System.out.println(inputLine);
    }
    inputStream.close();
  }
}
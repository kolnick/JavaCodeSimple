import com.alibaba.fastjson2.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: caochaojie
 * @Date: 2023-12-10 11:57
 */
public class ChatGPTSdkTest {
    private static final String OPENAI_API_KEY = "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";

    @Test
    public void testAudio() throws URISyntaxException, IOException {

        // 创建 HttpClient 对象
        HttpClient httpClient = HttpClientBuilder.create().setProxy(new HttpHost("127.0.0.1", 21882)).build();

        // 构建请求 URL
        URI url = new URI("https://api.openai.com/v1/audio/speech");

        // 创建 HttpPost 请求
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeader("Authorization", "Bearer " + OPENAI_API_KEY);
        // 设置请求头
        postRequest.setHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
        postRequest.setHeader("Content-Type", "application/json");

        // 构建请求体
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("model", "tts-1");
        bodyMap.put("input", "给我介绍一下杭州.");
        String voice = "juniper";
        bodyMap.put("voice", voice);

        String jsonBody = JSON.toJSONString(bodyMap);
        // 设置请求体
        StringEntity stringEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
        postRequest.setEntity(stringEntity);

        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(postRequest);

        // 检查响应状态
        if (response.getStatusLine().getStatusCode() == 200) {
            // 获取响应体
            HttpEntity entity = response.getEntity();
            //String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            // 获取响应流到mp3文件
            InputStream content = entity.getContent();
            // 处理响应体
            File file = new File(voice + ".mp3");
            FileOutputStream fileInputStream = new FileOutputStream(file);
            fileInputStream.write(content.readAllBytes());
        } else {
            // 处理错误
            System.err.println("Error: " + response.getStatusLine().getStatusCode());
        }

    }


}

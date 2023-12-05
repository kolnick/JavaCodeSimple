import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.alibaba.dashscope.utils.JsonUtils;

/**
 * @Author: caochaojie
 * @Date: 2023-12-04 10:26
 */
public class BaiChuanSDKTest {


    public static void usage()
            throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        GenerationParam param = GenerationParam
                .builder()
                .model("baichuan2-7b-chat-v1")
                .prompt("给我介绍一下AIGC有哪些应用场景")
                .build();
        GenerationResult result = gen.call(param);
        String json = JsonUtils.toJson(result);
        System.out.println(json);
    }

    public static void main(String[] args) {
        try {
            Constants.apiKey="sk-3d1514bc4d0e41009ce84892b0396af6";

            usage();
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }

}

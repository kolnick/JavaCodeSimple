import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;

/**
 * @author caochaojie
 * 2022/12/8
 * @description
 */
@Slf4j
public class TestClassName {

    @Test
    public void test() {
        String typeName = StringDeserializer.class.getTypeName();
        log.info(typeName);
    }
}

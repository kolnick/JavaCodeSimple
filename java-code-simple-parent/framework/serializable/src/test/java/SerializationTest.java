import com.caochaojie.serializable.Serialization;
import com.caochaojie.serializable.bean.Person;
import com.caochaojie.serializable.impl.FastJson;
import com.caochaojie.serializable.impl.Fst;
import com.caochaojie.serializable.impl.JdkSerialization;
import com.caochaojie.serializable.impl.KryoSerialization;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
@Slf4j
public class SerializationTest {

    /**
     * 需要JDK14
     */
    @Test
    public void testFst() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new Fst();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

    /**
     * 支持JDK17
     */
    @Test
    public void testKryo() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new KryoSerialization();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

    @Test
    public void testFastJson() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new FastJson();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

    @Test
    public void testJdk() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new JdkSerialization();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

    @Test
    public void test() {
        String join = StringUtils.join(new Object[]{"属性","", "1"}, '|');
        System.out.println(join);
    }
}

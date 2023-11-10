import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.beust.jcommander.internal.Maps;
import com.caochaojie.serializable.Serialization;
import com.caochaojie.serializable.bean.Person;
import com.caochaojie.serializable.impl.FastJson;
import com.caochaojie.serializable.impl.Fst;
import com.caochaojie.serializable.impl.JdkSerialization;
import com.caochaojie.serializable.impl.KryoSerialization;
import com.google.common.collect.Lists;
import jprotobuf.Cat;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

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
    public void jproto() {
        Codec<Cat> simpleTypeCodec = ProtobufProxy
                .create(Cat.class);
        Cat cat = new Cat();
        cat.setCatName("dd");
        cat.setId(1);
        cat.setFoodMap(Maps.newHashMap(1, 2, 3, 4));
        cat.setPropIdList(Lists.newArrayList(1l, 2l, 3l, 4l));
        try {
            // 序列化
            byte[] bb = simpleTypeCodec.encode(cat);
            // 反序列化
            Cat uncat = simpleTypeCodec.decode(bb);
            System.out.println(uncat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJdk() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new JdkSerialization();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

}

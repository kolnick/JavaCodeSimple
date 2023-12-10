import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caochaojie.serializable.Serialization;
import com.caochaojie.serializable.bean.Person;
import com.caochaojie.serializable.impl.FastJson;
import com.caochaojie.serializable.impl.Fst;
import com.caochaojie.serializable.impl.JdkSerialization;
import com.caochaojie.serializable.impl.KryoSerialization;
import io.fury.Fury;
import io.fury.config.Language;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

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
    public void fst() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new Fst();
        long l = System.currentTimeMillis();
        byte[] serializer = serialization.serializer(person);
        long x = System.currentTimeMillis();
        System.out.println(x-l);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        System.out.println(System.currentTimeMillis() - x);

        log.info(deserialize.toString());
    }

    /**
     * 支持JDK17
     */
    @Test
    public void kryo() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new KryoSerialization();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

    @Test
    public void fastJson() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new FastJson();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

    @Test
    public void jproto() {
        Codec<Person> simpleTypeCodec = ProtobufProxy
                .create(Person.class);
        Person person = new Person(19, "ccj");


        try {
            // 序列化
            byte[] bb = simpleTypeCodec.encode(person);
            // 反序列化
            Person uncat = simpleTypeCodec.decode(bb);
            System.out.println(uncat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void jdk() {
        Person person = new Person(19, "ccj");
        Serialization serialization = new JdkSerialization();
        byte[] serializer = serialization.serializer(person);
        Person deserialize = serialization.deserialize(Person.class, serializer);
        log.info(deserialize.toString());
    }

    @Test
    public void fury() {
        Person person = new Person(19, "ccj");
        Fury fury = Fury.builder().withLanguage(Language.JAVA)
                .requireClassRegistration(false)
                .build();
        fury.register(Person.class);
        byte[] bytes = fury.serialize(person);
        long l = System.currentTimeMillis();
        byte[] bytes2 = fury.serialize(person);
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(fury.deserialize(bytes));
    }

}

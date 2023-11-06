package client;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {

    public static void main(String[] args) throws TTransportException {

        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUserName("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("-----------");

            Person person2 = new Person();
            person2.setUsername("李四");
            person2.setAge(20);
            person2.setMarried(true);

            client.savePerson(person2);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            transport.close();
        }
    }
}

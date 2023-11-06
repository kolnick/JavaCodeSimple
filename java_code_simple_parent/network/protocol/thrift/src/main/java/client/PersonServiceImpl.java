package client;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUserName(String userName) throws DataException, TException {
        System.out.println("get client Param");
        Person person = new Person();
        person.setAge(19);
        person.setMarried(false);
        person.setUsername("kolnick");
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("get client param");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}

package kafka.example;

import kafka.bean.Bar;
import kafka.bean.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caochaojie
 * @Date 2022/8/7
 */
@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @PostMapping(path = "/send/foo/{what}")
    public void sendFoo(@PathVariable String what) {
        this.template.send("foos", new Foo(what));
    }

    @PostMapping(path = "/send/bar/{what}")
    public void sendBar(@PathVariable String what) {
        this.template.send("bars", new Bar(what));
    }

    @PostMapping(path = "/send/unknown/{what}")
    public void sendUnknown(@PathVariable String what) {
        this.template.send("bars", what);
    }

}

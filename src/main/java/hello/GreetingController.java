package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

/*
    @RestController is a Spring annotation which marks the class as a controller where every method returns a domain object
    instead of a view.  Its shorthad for @Controller and @ResponseBody rolled together.
*/
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    /*
        Request Mapping ensures that HTTP Requests to '/greeting' are mapped to the 'greeting()' method.
        This does not specify GET vs PUT, POST, and so forth because RequestMappping maps all HTTP operations by default.
        Use '@RequestMapping(method=GET)' to narrow this mapping.

        The Greeting object must be converted to JSON.  Spring has HTTP message converter support; you don't need to do this
        conversion manually.  Because Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter is
        automatically chosen to convert the Greeting instance to JSON.

     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World")String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}

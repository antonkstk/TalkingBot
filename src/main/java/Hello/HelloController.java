package Hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anton on 8/12/16.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/webhook")
    public String bootResponse(@RequestParam(required = false) String req) {
        return "Greetings from Spring Boot!";
    }
}
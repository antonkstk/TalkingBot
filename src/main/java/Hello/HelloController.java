package Hello;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by anton on 8/12/16.
 */
@RestController
public class HelloController {

    private final String pageToken = "EAAE0KtuIWZCEBAHvCrK37GshyZAD7Vti3nTIhglj74s4HwkYZAMMZBs3fRCR2hyhqtvEHpyE785qLfQL7RN8Gdn8Q3ZBjwyznyKgWYbZC9VU2Nj8XWLfAASaOujcnpM3ZArYgANuy5EocgK5K2wL66MK6LJcdLBLwUNdTZC54BFZAMQZDZD";
    private final String verifyToken = "SAY_HELLO_BOT";

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.GET)
    public String webHookResponse(@RequestParam("hub.challenge") String challenge, @RequestParam("hub.verify_token") String verify_token) {
        if(verify_token.equals(verifyToken)) {
            return challenge;
        }
        else {
            return "ERROR!";
        }
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST)
    public String webHookResponse(@RequestBody String body) {
        //List<String> messagings = body.entry.get(0).messaging;
        return body;
    }
}
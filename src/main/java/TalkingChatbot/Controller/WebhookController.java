package TalkingChatbot.Controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by anton on 8/12/16.
 */
@RestController
public class WebhookController {

    private final String pageToken = "EAAE0KtuIWZCEBAHvCrK37GshyZAD7Vti3nTIhglj74s4HwkYZAMMZBs3fRCR2hyhqtvEHpyE785qLfQL7RN8Gdn8Q3ZBjwyznyKgWYbZC9VU2Nj8XWLfAASaOujcnpM3ZArYgANuy5EocgK5K2wL66MK6LJcdLBLwUNdTZC54BFZAMQZDZD";
    private final String verifyToken = "SAY_HELLO_BOT";

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.GET)
    public String getWebhook(@RequestParam("challenge") String challenge, @RequestParam("verify_token") String verify_token,
             @RequestParam("mode") String mode) {
        if(verify_token.equals(verifyToken)) {
            return challenge;
        }
        else {
            return "ERROR!";
        }
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST)
    public String postWebhook(@RequestBody String body) {
        //List<String> messagings = body.entry.get(0).messaging;
        //throw new Exception(body);
        return body;
    }
}
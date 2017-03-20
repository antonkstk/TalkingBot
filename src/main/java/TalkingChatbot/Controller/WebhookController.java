package TalkingChatbot.Controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by anton on 8/12/16.
 */
@RestController
public class WebhookController {

    private final String verifyToken = "EAAU3dkADtksBALsYVifZA9HEAuBAb4MRAaLZCoZCCv63HYsPwhl1fXkgmkaPMQaTtKxMmmGm4yydplimqbEwsBVqiPiLkq8ntag9CSghzCQksvOfLPjAPrTszVEsHQMEaod1sQrlBC9YvbJJ8ZAOWnjHnxISHjCOhWvMsAV41QZDZD";

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.GET)
    public Integer getWebhook(@RequestParam("hub.challenge") String challenge, @RequestParam("hub.verify_token") String verify_token,
             @RequestParam("hub.mode") String mode) {
        if(verify_token.equals(verifyToken)) {
            return Integer.parseInt(challenge);
        }
        else {
            return 0;
        }
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST)
    public String postWebhook(@RequestBody String body) {
        //List<String> messagings = body.entry.get(0).messaging;
        //throw new Exception(body);
        return body;
    }
}
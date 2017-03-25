package TalkingChatbot.Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Integer> getWebhook(@RequestParam("hub.challenge") String challenge, @RequestParam("hub.verify_token") String verify_token,
             @RequestParam("hub.mode") String mode) {
        if(verify_token.equals(verifyToken) && mode.equals("subscribe")) {
            return new ResponseEntity(Integer.parseInt(challenge), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(0, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST)
    public String postWebhook(@RequestBody String jsonString) {
        //System.out.println("Message received: " + jsonString);

        try {
            JSONObject rootJSON = (JSONObject) new JSONParser().parse(jsonString);
            JSONArray entry = (JSONArray) rootJSON.get("entry");
            for(Object el: entry.toArray()){

                JSONObject messaging = (JSONObject)el;
                System.out.println("Messaging: " + messaging);
                JSONArray messageList = (JSONArray) messaging.get("messaging");
                System.out.println("Messaging list: " + messageList);
                /*for(Object messageObj: messageList.toArray()){
                    JSONObject issue = (JSONObject) messageObj;
                    //do something with the issue
                }*/
            }
        }
        catch (ParseException e) {
            System.out.println(e);
        }


        return jsonString;
    }
}
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

            System.out.println("Entry: " + entry);
            for(Object rootEl: entry.toArray()){
                JSONObject requestBody = (JSONObject)rootEl;
                JSONArray messaging = (JSONArray) requestBody.get("messaging");

                System.out.println("Messaging: " + messaging);
                for(Object messagingEl: messaging.toArray()){
                    JSONObject messageData = (JSONObject) messagingEl;

                    //JSONArray messageBody = (JSONArray) messageData.get("message");

                    System.out.println("Message: " + messageData.get("message"));
                    /*for(Object messageEl: messageBody.toArray()){
                        JSONObject message = (JSONObject) messageEl;
                        JSONArray messageText = (JSONArray) message.get("text");
                        System.out.println("Message text: " + messageText);
                    }*/
                }
            }
        }
        catch (ParseException e) {
            System.out.println(e);
        }


        return jsonString;
    }
}
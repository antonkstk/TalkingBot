package TalkingChatbot.Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by anton on 8/12/16.
 */
@RestController
public class WebhookController {

    private final String url = "https://graph.facebook.com/v2.6/me/messages";
    private final String verifyToken = "EAAU3dkADtksBALsYVifZA9HEAuBAb4MRAaLZCoZCCv63HYsPwhl1fXkgmkaPMQaTtKxMmmGm4yydplimqbEwsBVqiPiLkq8ntag9CSghzCQksvOfLPjAPrTszVEsHQMEaod1sQrlBC9YvbJJ8ZAOWnjHnxISHjCOhWvMsAV41QZDZD";
    private String messageId = "";
    private String messageText = "";
    private String recipientId = "";

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
    public void postWebhook(@RequestBody String jsonString) {
        /*try {
            JSONObject rootJSON = (JSONObject) new JSONParser().parse(jsonString);

            if(rootJSON.get("entry") != null) {
                JSONArray entry = (JSONArray) rootJSON.get("entry");
                System.out.println("Entry: " + entry);//remove <-
                JSONObject requestBody = (JSONObject) entry.toArray()[0];
                //for (Object rootEl : entry.toArray()) {
                    //JSONObject requestBody = (JSONObject) rootEl;

                    if (requestBody.get("messaging") != null) {
                        JSONArray messaging = (JSONArray) requestBody.get("messaging");
                        messageId = requestBody.get("id").toString();
                        JSONObject messageData = (JSONObject) messaging.toArray()[0];
                        //for (Object messagingEl : messaging.toArray()) {
                        //    JSONObject messageData = (JSONObject) messagingEl;

                            if (messageData.get("message") != null && messageData.get("sender") != null) {
                                JSONObject message = (JSONObject) messageData.get("message");
                                JSONObject sender = (JSONObject) messageData.get("sender");
                                recipientId = sender.get("id").toString();
                                messageText = message.get("text").toString();
                            }
                        //}
                    }
                //}
            }
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }*/

        sendMessageBack(recipientId, messageText);

    }

    /**
     * add all the needed checking conditions!!!
     * @param recipientId
     * @param messageText
     */
    private void sendMessageBack(String recipientId, String messageText) {
        JSONObject messageData = new JSONObject();
        JSONObject recipient = new JSONObject();
        JSONObject message = new JSONObject();
        JSONObject queryString = new JSONObject();
        queryString.put("access_token", verifyToken);
        recipient.put("id", "1110000515732631");
        message.put("text", "hello");
        messageData.put("recipient", recipient);
        messageData.put("message", message);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(messageData.toJSONString(), headers);

        restTemplate.exchange(url + "?access_token=" + verifyToken, HttpMethod.POST, entity, String.class);

    }
}
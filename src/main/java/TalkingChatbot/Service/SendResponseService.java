package TalkingChatbot.Service;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by anton on 4/2/17.
 */
@Service
public class SendResponseService {
   /**
    * this method sends the input message back to user
    * @param recipientId - the id of the message recipient
    * @param messageText - the massage body
    */
   public static void sendMessageBack(String recipientId, String messageText, String verifyToken, String url) {
      JSONObject messageData = new JSONObject();
      JSONObject recipient = new JSONObject();
      JSONObject message = new JSONObject();
      JSONObject queryString = new JSONObject();
      queryString.put("access_token", verifyToken);
      recipient.put("id", recipientId);
      message.put("text", messageText);
      messageData.put("recipient", recipient);
      messageData.put("message", message);

      RestTemplate restTemplate = new RestTemplate();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<String> entity = new HttpEntity(messageData.toJSONString(), headers);

      restTemplate.exchange(url + "?access_token=" + verifyToken, HttpMethod.POST, entity, String.class);
   }
}

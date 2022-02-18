package emlakburada.service;

import emlakburada.dto.Messagerequest;
import emlakburada.dto.response.MessageResponse;
import emlakburada.model.Message;
import emlakburada.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MesajService {
    @Autowired
    private MessageRepository messageRepository;

    private static int messageNo = 1;

    public List<MessageResponse> getAllMessages() {
        List<MessageResponse> advertList = new ArrayList<>();
        for (Message message : messageRepository.fetchAllMessages()) {
            advertList.add(convertToMessageResponse(message));
        }
        return advertList;
    }

    public MessageResponse saveMessage(Messagerequest request) {
        Message savedAdvert = messageRepository.saveMessage(convertToMessage(request));
//        EmailMessage emailMessage = new EmailMessage("cemdrman@gmail.com");
//        queueService.sendMessage(emailMessage);
//        bannerClient.saveBanner();
        return convertToMessageResponse(savedAdvert);
    }

    private MessageResponse convertToMessageResponse(Message savedMessage) {
        MessageResponse response = new MessageResponse();
        response.setBaslik(savedMessage.getBaslik());
        response.setGonderici(savedMessage.getGonderici());
        response.setMessageNo(savedMessage.getMessageNo());
        return response;
    }

    private Message convertToMessage(Messagerequest request) {
        Message message = new Message();
        messageNo++;

        message.setMessageNo(messageNo);
        message.setBaslik(request.getBaslik());
        message.setIcerigi(request.getIcerigi());
        return message;
    }

    public MessageResponse getMessageById(int messageId) {
        Message message = messageRepository.findMessageById(messageId);
        return convertToMessageResponse(message);
    }


}

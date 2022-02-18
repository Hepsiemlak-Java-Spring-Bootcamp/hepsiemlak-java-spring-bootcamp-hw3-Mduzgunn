package emlakburada.repository;

import emlakburada.model.Advert;
import emlakburada.model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {
    private static List<Message> mesajListesi = new ArrayList<>();

    static {
        mesajListesi.add(prepareMessage(1,"icerik1", "baslik1"));
        mesajListesi.add(prepareMessage(2,"icerik2", "baslik2"));
        mesajListesi.add(prepareMessage(3,"icerik3", "baslik3"));
        mesajListesi.add(prepareMessage(4,"icerik4", "baslik4"));
    }

    public List<Message> fetchAllMessages() {
        return mesajListesi;
    }

    private static Message prepareMessage(int messageNo,String icerik, String baslik) {
        Message message = new Message();
        message.setMessageNo(messageNo);
        message.setIcerigi(icerik);
        message.setBaslik(baslik);
        return message;
    }

    public Message saveMessage(Message message) {
        mesajListesi.add(message);
        System.out.println(message.toString());
        return message;
    }


    public Message findMessageById(int messageNo) {
        return mesajListesi.stream().filter(advert -> advert.getMessageNo() == messageNo).findAny().orElse(new Message());
    }


}

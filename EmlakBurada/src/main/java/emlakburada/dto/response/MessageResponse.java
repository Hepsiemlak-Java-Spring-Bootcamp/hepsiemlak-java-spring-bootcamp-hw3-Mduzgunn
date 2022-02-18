package emlakburada.dto.response;

import emlakburada.model.User;
import lombok.Data;

import java.util.Date;
@Data
public class MessageResponse {
    private int messageNo;
    private String baslik;
    private String icerigi;
    private Date gonderilenTarih;
    private Date okunduguTarihi;
    private boolean gorulduMu;
    private User gonderici;
    private User alici;
}

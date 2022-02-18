package emlakburada.dto;

import emlakburada.model.User;
import lombok.Data;

@Data
public class Messagerequest {
    private String baslik;
    private String icerigi;
    private boolean gorulduMu=false;
    private User gonderici;
    private User alici;
}

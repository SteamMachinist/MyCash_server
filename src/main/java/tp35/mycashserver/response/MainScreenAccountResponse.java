package tp35.mycashserver.response;

import lombok.Data;
import tp35.mycashserver.dto.OperationDTO;

import java.util.List;

@Data
public class MainScreenAccountResponse {
    private final String accountName;
    private final Double balance;
    private final List<OperationDTO> operations;
}

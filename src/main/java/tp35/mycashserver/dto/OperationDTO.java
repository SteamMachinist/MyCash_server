package tp35.mycashserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private AccountDTO account;
    private CategoryDTO category;
    private Double value;
    private LocalDateTime created;
}

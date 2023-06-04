package tp35.mycashserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private Long id;
    private String accountName;
    private CategoryDTO category;
    private Double value;
    private String dateTime;
    private String comment;
}

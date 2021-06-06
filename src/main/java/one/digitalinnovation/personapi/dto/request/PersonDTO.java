package one.digitalinnovation.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entity.Phone;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {


    private Long id;

    @NotEmpty(message = "Atenção! O campo firstName é obrigatório!")
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty(message = "Atenção! O campo lastName é obrigatório!")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty(message = "Atenção! O campo cpf é obrigatório!")
    @CPF
    private String cpf;

    private String birthDate;

    @NotEmpty(message = "Atenção! O campo phones é obrigatório")
    @Valid
    private List<Phone> phones;
}

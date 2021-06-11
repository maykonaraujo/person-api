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

    @NotEmpty
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty(message = "O campo Last Name é obrigatório!")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty(message = "O campo CPF é obrigatório!")
    @CPF
    private String cpf;

    @NotEmpty(message = "O campo Birth Date é obrigatório!")
    private String birthDate;

    @NotEmpty(message = "O campo Phone é obrigatório!")
    @Valid
    private List<Phone> phones;
}

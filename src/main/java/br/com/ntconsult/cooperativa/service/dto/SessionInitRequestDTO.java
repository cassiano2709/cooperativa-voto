package br.com.ntconsult.cooperativa.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionInitRequestDTO {

    private LocalDateTime endDateTime;
    @NotNull(message = "The Subject id cannot be null!")
    private Long subjectId;
}

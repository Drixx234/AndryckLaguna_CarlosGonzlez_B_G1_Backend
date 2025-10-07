package finalBoss.pruebaFinal.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PeliculasDTO {

    @NotBlank
    private Long Id_Pelicula;

    @NotBlank
    private String Titulo;

    @NotBlank
    private String Director;

    @NotBlank
    private String Genero;

    @NotBlank
    private int Ano_Estreno;

    @NotBlank
    private int Duracion_Min;

    @NotBlank
    private String Fecha_Creacion;
}

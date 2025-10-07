package finalBoss.pruebaFinal.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "PELICULAS")
public class peliculasEntity {

    @Id
    @Column(name = "ID_PELICULA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PELICULAS")
    @SequenceGenerator(sequenceName = "SEQ_PELICULAS", name = "SEQ_PELICULAS", allocationSize = 1)
    private Long Id_Peliculas;

    @Column(name = "TITULO")
    private String Titulo;

    @Column(name = "DIRECTOR")
    private String Director;

    @Column(name = "GENERO")
    private String Genero;

    @Column(name = "ANO_ESTRENO")
    private int Ano_Estreno;

    @Column(name = "DURACION_MIN")
    private int Duracion_Min;

    @Column(name = "FECHA_CREACION")
    private String Fecha_Creacion;

}

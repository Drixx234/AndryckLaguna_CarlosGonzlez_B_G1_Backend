package finalBoss.pruebaFinal.Services;

import finalBoss.pruebaFinal.Entities.peliculasEntity;
import finalBoss.pruebaFinal.Exceptions.ExceptionPeliculaNotFound;
import finalBoss.pruebaFinal.Models.DTO.PeliculasDTO;
import finalBoss.pruebaFinal.Repositories.peliculasRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class peliculasServices {

    @Autowired
    private peliculasRepository repo;

        public List<PeliculasDTO> getAllPelicula(){
            List<peliculasEntity> pageEntity = repo.findAll();
            return pageEntity.stream()
                    .map(this::ConvertirADTO)
                    .collect(Collectors.toList());
        }

    public PeliculasDTO insert(@Valid PeliculasDTO jsonData){
        if(jsonData == null){
            throw new IllegalArgumentException("Titulo no puede ser nulo");
        }
        try {
            peliculasEntity peliculasEntity = ConvertirAEntity(jsonData);
            peliculasEntity peliculasSave = repo.save(peliculasEntity);
            return ConvertirADTO(peliculasSave);
        }catch (Exception e){
            log.error("error al ingresar pelicula" + e.getMessage());
            throw new ExceptionPeliculaNotFound("Error al ingresar pelicula");
        }
    }

     public PeliculasDTO update(Long id, @Valid PeliculasDTO jsonDto ){
        if (jsonDto == null){
            throw new IllegalArgumentException("La pelicula no puede ser nula");
        }
        peliculasEntity peliculasData = repo.findById(id).orElseThrow(()-> new ExceptionPeliculaNotFound("pelicula no encontrada"));
        peliculasData.setTitulo(jsonDto.getTitulo());
        peliculasData.setDirector(jsonDto.getDirector());

        peliculasEntity peliculasUpdate = repo.save(peliculasData);
        return ConvertirADTO(peliculasUpdate);
    }

    public boolean Delete(Long id){
        try{
            peliculasEntity objEntity = repo.findById(id).orElse(null);

            if (objEntity != null){
                repo.deleteById(id);
                return true;
            }
            return false;
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("no se encontro la pelicula con id"+id,1);
        }
    }

    public PeliculasDTO ConvertirADTO(peliculasEntity objEntity){
        PeliculasDTO dto = new PeliculasDTO();
        dto.setId_Pelicula(dto.getId_Pelicula());
        dto.setTitulo(dto.getTitulo());
        dto.setDirector(dto.getDirector());
        dto.setGenero(dto.getGenero());
        dto.setAno_Estreno(dto.getAno_Estreno());
        dto.setDuracion_Min(dto.getDuracion_Min());
        dto.setFecha_Creacion(dto.getFecha_Creacion());
        return dto;
    }

    private peliculasEntity ConvertirAEntity(@Valid PeliculasDTO json){
        peliculasEntity objEntity = new peliculasEntity();
        objEntity.setId_Peliculas(json.getId_Pelicula());
        objEntity.setTitulo(json.getTitulo());
        objEntity.setDirector(json.getDirector());
        objEntity.setGenero(json.getGenero());
        objEntity.setAno_Estreno(json.getAno_Estreno());
        objEntity.setDuracion_Min(json.getDuracion_Min());
        objEntity.setFecha_Creacion(json.getFecha_Creacion());
        return objEntity;
    }


}

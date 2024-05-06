package com.codigo.msgarcialuyo.infraestructure.adapters;

import com.codigo.msgarcialuyo.domain.aggregates.constants.Constant;
import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.codigo.msgarcialuyo.domain.aggregates.dto.ReniecDto;
import com.codigo.msgarcialuyo.domain.aggregates.dto.SunatDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.PersonaRequest;
import com.codigo.msgarcialuyo.domain.ports.out.PersonaServiceOut;
import com.codigo.msgarcialuyo.infraestructure.client.ClientReniec;
import com.codigo.msgarcialuyo.infraestructure.client.ClientSunat;
import com.codigo.msgarcialuyo.infraestructure.dao.PersonaRepository;
import com.codigo.msgarcialuyo.infraestructure.entity.EmpresaEntity;
import com.codigo.msgarcialuyo.infraestructure.entity.PersonaEntity;
import com.codigo.msgarcialuyo.infraestructure.mapper.EmpresaMapper;
import com.codigo.msgarcialuyo.infraestructure.mapper.PersonaMapper;
import com.codigo.msgarcialuyo.infraestructure.redis.RedisService;
import com.codigo.msgarcialuyo.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final ClientReniec clientReniec;
    private final RedisService redisService;

    @Value("${token.sunat}")
    private String tokenSunat;

    private PersonaEntity getEntity(PersonaRequest personaRequest, long id){
        ReniecDto reniecDto = getExcecuteSunat(personaRequest.getNumDoc());
        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setNombre(reniecDto.getNombres());
        personaEntity.setApellido(reniecDto.getApellidoPaterno());
        personaEntity.setTipoDocumento(reniecDto.getTipoDocumento());
        personaEntity.setNumeroDocumento(reniecDto.getNumeroDocumento());

        personaEntity.setEmail(personaRequest.getEmail());
        personaEntity.setTelefono(personaRequest.getTelefono());
        personaEntity.setDireccion(personaRequest.getDireccion());

        //Prueba
        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity.setIdEmpresa((long)1);
        personaEntity.setEmpresa(empresaEntity);

        personaEntity.setEstado(Constant.STATUS_ACTIVE);
        if(id == 0){
            personaEntity.setUsuaCrea(Constant.USU_ADMIN);
            personaEntity.setDateCreate(getTimestamp());
        }else {
            personaEntity.setIdPersona(id);
            personaEntity.setUsuaModif(Constant.USU_ADMIN);
            personaEntity.setDateModif(getTimestamp());
        }


        return personaEntity;
    }

    private Timestamp getTimestamp() {
        long currenTime = System.currentTimeMillis();
        return new Timestamp(currenTime);
    }

    private ReniecDto getExcecuteSunat(String numDoc){

        String authorization = "Bearer" + tokenSunat;

        return clientReniec.obtenerInfoReniec(numDoc,authorization);
    }

    @Override
    public PersonaDto crearOut(PersonaRequest personaRequest) {
        PersonaEntity personaEntity = getEntity(personaRequest,0);
        return  PersonaMapper.fromEntity(personaRepository.save(personaEntity));
    }

    @Override
    public Optional<PersonaDto> obtenerOut(Long id) {

        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPERSONA);
        if(redisInfo!= null){
            PersonaDto personaDto = Util.convertirDesdeString(redisInfo,PersonaDto.class);
            return Optional.of(personaDto);
        }else {
            PersonaDto personaDto = PersonaMapper.fromEntity(personaRepository.findById(id).get());
            String dataForReadis  = Util.convertirAString(personaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPERSONA+id,dataForReadis,10);
            return Optional.of(personaDto);
        }
    }

    @Override
    public List<PersonaDto> listarOut() {
        List<PersonaDto> dtoList = new ArrayList<>();

        List<PersonaEntity> entityList = personaRepository.findAll();
        for (PersonaEntity dato : entityList) {
            dtoList.add(PersonaMapper.fromEntity(dato));
        }
        return dtoList;
    }

    @Override
    public PersonaDto actualizarOut(Long id, PersonaRequest personaRequest) {

        Optional<PersonaEntity> personaEntity = personaRepository.findById(id);
        if(personaEntity.isPresent()){
            PersonaEntity personaEntity1 = getEntity(personaRequest, id);
            return  PersonaMapper.fromEntity(personaRepository.save(personaEntity1));
        }else {
            throw new RuntimeException();
        }


    }

    @Override
    public PersonaDto eliminarOut(Long id) {

        Optional<PersonaEntity> datoExtraido = personaRepository.findById(id);

        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return PersonaMapper.fromEntity(personaRepository.save(datoExtraido.get()));
        }else{
            throw new RuntimeException();
        }

    }
}

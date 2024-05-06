package com.codigo.msgarcialuyo.infraestructure.adapters;

import com.codigo.msgarcialuyo.domain.aggregates.constants.Constant;
import com.codigo.msgarcialuyo.domain.aggregates.dto.EmpresaDto;
import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.codigo.msgarcialuyo.domain.aggregates.dto.SunatDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.EmpresaRequest;
import com.codigo.msgarcialuyo.domain.ports.out.EmpresaServiceOut;
import com.codigo.msgarcialuyo.infraestructure.client.ClientSunat;
import com.codigo.msgarcialuyo.infraestructure.dao.EmpresaRepository;
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
public class EmpresaAdapter implements EmpresaServiceOut {

    private final EmpresaRepository empresaRepository;
    private final ClientSunat clientSunat;
    private final RedisService redisService;

    @Value("${token.sunat}")
    private String tokenSunat;

    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, long id){
        SunatDto sunatDto = getExcecuteSunat(empresaRequest.getNumDoc());
        EmpresaEntity empresaEntity = new EmpresaEntity();

        empresaEntity.setRazonSocial(sunatDto.getRazonSocial());
        empresaEntity.setTipoDocumento(sunatDto.getTipoDocumento());
        empresaEntity.setNumeroDocumento(sunatDto.getNumeroDocumento());
        empresaEntity.setCondicion(sunatDto.getCondicion());
        empresaEntity.setEsAgenteRetencion(sunatDto.getEsAgenteRetencion());

        empresaEntity.setEstado(Constant.STATUS_ACTIVE);
        if(id == 0){
            empresaEntity.setUsuaCrea(Constant.USU_ADMIN);
            empresaEntity.setDateCreate(getTimestamp());
        }else {
            empresaEntity.setIdEmpresa(id);
            empresaEntity.setUsuaModif(Constant.USU_ADMIN);
            empresaEntity.setDateModif(getTimestamp());
        }

        return empresaEntity;
    }

    private Timestamp getTimestamp() {
        long currenTime = System.currentTimeMillis();
        return new Timestamp(currenTime);
    }

    private SunatDto getExcecuteSunat(String numDoc){

        String authorization = "Bearer" + tokenSunat;

        return clientSunat.obtenerInfoSunat(numDoc,authorization);
    }


    @Override
    public EmpresaDto crearOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity = getEntity(empresaRequest,0);
        return  EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
    }

    @Override
    public Optional<EmpresaDto> obtenerOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPRESA);
        if(redisInfo!= null){
            EmpresaDto empresaDto = Util.convertirDesdeString(redisInfo,EmpresaDto.class);
            return Optional.of(empresaDto);
        }else {
            EmpresaDto empresaDto = EmpresaMapper.fromEntity(empresaRepository.findById(id).get());
            String dataForReadis  = Util.convertirAStringEmpresa(empresaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id,dataForReadis,10);
            return Optional.of(empresaDto);
        }
    }

    @Override
    public List<EmpresaDto> listarOut() {
        List<EmpresaDto> dtoList = new ArrayList<>();

        List<EmpresaEntity> entityList = empresaRepository.findAll();
        for (EmpresaEntity dato : entityList) {
            dtoList.add(EmpresaMapper.fromEntity(dato));
        }
        return dtoList;
    }

    @Override
    public EmpresaDto actualizarOut(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> empresaEntity = empresaRepository.findById(id);
        if(empresaEntity.isPresent()){
            EmpresaEntity empresaEntity1 = getEntity(empresaRequest, id);
            return  EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity1));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public EmpresaDto eliminarOut(Long id) {
        Optional<EmpresaEntity> datoExtraido = empresaRepository.findById(id);

        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return EmpresaMapper.fromEntity(empresaRepository.save(datoExtraido.get()));
        }else{
            throw new RuntimeException();
        }
    }
}

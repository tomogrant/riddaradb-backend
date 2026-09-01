package com.se.riddaradb.ms;

import com.se.riddaradb.motif.MotifSagaVersionDto;
import com.se.riddaradb.saga.*;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import com.se.riddaradb.sagaversion.SagaVersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class MsService {

    final MsRepository msRepository;
    final SagaRepository sagaRepository;
    final SagaMsRepository sagaMsRepository;
    final MsRepositoryRepository msRepositoryRepository;
    final MsMapper msMapper;

    public MsService(MsRepository msRepository, SagaRepository sagaRepository, SagaMsRepository sagaMsRepository, MsRepositoryRepository msRepositoryRepository, MsMapper msMapper) {
        this.msRepository = msRepository;
        this.sagaRepository = sagaRepository;
        this.sagaMsRepository = sagaMsRepository;
        this.msRepositoryRepository = msRepositoryRepository;
        this.msMapper = msMapper;
    }

    public Collection<MsDto> getMsEntries(){
        return msRepository.findAll()
                .stream()
                .map(msMapper::mapToDto)
                .toList();
    }

    public MsDto getMsEntryById(int id){
        if (msRepository.findById(id).isPresent()){
            return msMapper.mapToDto(msRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public MsDto updateMsEntry(MsDto msDto){

        MsEntity msEntity = msRepository.findById(msDto.getId()).orElseThrow();

        msEntity.setName(msDto.getName());
        msEntity.setShelfmark(msDto.getShelfmark());
        msEntity.setDescription(msDto.getDescription());

        updateSaga(msEntity, msDto);

        return msMapper.mapToDto(msEntity);
    }

    public MsDto saveMsEntry(MsDto msDto){
        MsEntity msEntity = new MsEntity(msDto.getId(), msDto.getName(), msDto.getShelfmark(), msDto.getDescription());

        for (MsSagaDto msSagaDto : msDto.getMsSagaDtos()){
            sagaRepository.findById(msSagaDto.getSagaId()).ifPresent(saga -> saga.addMs(msEntity, msSagaDto.getFolioNumber()));
        }

        if (msDto.getMsRepositoryId() != null){
            msRepositoryRepository.findById(msDto.getMsRepositoryId()).ifPresent(msRepoRepo -> msRepoRepo.addMs(msEntity));
        }

        return msMapper.mapToDto(msRepository.save(msEntity));
    }

    void updateSaga(MsEntity msEntity, MsDto msDto){

        //Map of saga-MS join entities already associated with this saga. Indexed by saga ID.
        Map<Integer, SagaMsEntity> currentSagaMss = msEntity.getSagaMsEntities()
                .stream()
                .collect(Collectors.toMap(sagaMsEntity -> sagaMsEntity.getSagaEntity().getId(), Function.identity()));

        //IDs of sagas to be joined to this MS
        Set<Integer> newMsSagaIds = msDto.getMsSagaDtos()
                .stream()
                .map(MsSagaDto::getSagaId)
                .collect(Collectors.toSet());

        //For each saga-MS entity attached to saga...
        for (MsSagaDto msSagaDto : msDto.getMsSagaDtos()){
            SagaMsEntity sagaMsCurrent = currentSagaMss.get(msSagaDto.getSagaId());

            //Saga-MS exists already. Update.
            if (sagaMsCurrent != null){
                sagaMsCurrent.setFolioNumber(msSagaDto.getFolioNumber());
            }

            //Saga-MS does not exist. Fetch saga and attach MS.
            else{
                sagaRepository.findById(msSagaDto.getSagaId()).ifPresent(saga -> {
                    saga.addMs(msEntity, msSagaDto.getFolioNumber());
                });
            }
        }

        //For each saga-MS join entity associated with this MS, remove join entity
        //from saga if it is not listed in the 'new MS-saga IDs' set.
        sagaMsRepository.findByMsEntityId(msDto.getId()).forEach(sagaMs -> {
            if (!newMsSagaIds.contains(sagaMs.getSagaEntity().getId())){
                sagaRepository.findById(sagaMs.getSagaEntity().getId()).ifPresent(saga ->
                        saga.removeMs(msRepository.findById(sagaMs.getMsEntity().getId()).orElseThrow()));
            }
        });
    }

    public void deleteMsEntryById(int id){

        msRepository.deleteById(id);
    }

    public void deleteAll(){
        msRepository.deleteAll();
    }
}

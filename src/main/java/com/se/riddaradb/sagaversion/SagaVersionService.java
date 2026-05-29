package com.se.riddaradb.sagaversion;

import com.se.riddaradb.bib.BibRepository;
import com.se.riddaradb.character.PersonEntity;
import com.se.riddaradb.character.PersonRepository;
import com.se.riddaradb.motif.MotifRepository;
import com.se.riddaradb.ms.MsEntity;
import com.se.riddaradb.ms.MsRepository;
import com.se.riddaradb.object.ObjectEntity;
import com.se.riddaradb.object.ObjectRepository;
import com.se.riddaradb.place.PlaceEntity;
import com.se.riddaradb.place.PlaceRepository;
import com.se.riddaradb.saga.SagaRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SagaVersionService {

    final SagaRepository sagaRepository;
    final SagaVersionRepository sagaVersionRepository;
    final BibRepository bibRepository;
    final MotifRepository motifRepository;
    final PersonRepository personRepository;
    final PlaceRepository placeRepository;
    final ObjectRepository objectRepository;
    final MsRepository msRepository;
    final SagaVersionMapper sagaVersionMapper;

    public SagaVersionService(SagaRepository sagaRepository,
                              SagaVersionRepository sagaVersionRepository,
                              BibRepository bibRepository,
                              MotifRepository motifRepository,
                              PersonRepository personRepository,
                              PlaceRepository placeRepository,
                              ObjectRepository objectRepository,
                              MsRepository msRepository,
                              SagaVersionMapper sagaVersionMapper) {

        this.sagaRepository = sagaRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.bibRepository = bibRepository;
        this.motifRepository = motifRepository;
        this.personRepository = personRepository;
        this.placeRepository = placeRepository;
        this.objectRepository = objectRepository;
        this.msRepository = msRepository;
        this.sagaVersionMapper = sagaVersionMapper;
    }

    public Set<SagaVersionResponseDto> getSagaVersions(){
        return sagaVersionRepository.findAll()
                .stream()
                .map(sagaVersionMapper::mapToDto)
                .collect(Collectors.toSet());
    }

    public SagaVersionResponseDto getSagaVersionById(int id){
        if (sagaVersionRepository.findById(id).isPresent()){
            return sagaVersionMapper.mapToDto(sagaVersionRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public SagaVersionResponseDto saveSagaVersion(SagaVersionRequestDto sagaVersionRequestDto){
        SagaVersionEntity sagaVersionEntity = sagaVersionMapper.mapFromDto(sagaVersionRequestDto);

        if (sagaRepository.findById(sagaVersionRequestDto.getSagaId()).isPresent()){
            sagaVersionEntity.setSagaEntity(sagaRepository.findById(sagaVersionRequestDto.getSagaId()).get());
        }

        sagaVersionEntity.setBibEntity(new HashSet<>(bibRepository.findAllById(sagaVersionRequestDto.getBibIds())));
//        sagaVersionEntity.setMotifEntity(new HashSet<>(motifRepository.findAllById(sagaVersionRequestDto.getMotifIds())));
        sagaVersionEntity.setPersonEntity(new HashSet<>(personRepository.findAllById(sagaVersionRequestDto.getPersonIds())));
        sagaVersionEntity.setPlaceEntity(new HashSet<>(placeRepository.findAllById(sagaVersionRequestDto.getPlaceIds())));
        sagaVersionEntity.setObjectEntity(new HashSet<>(objectRepository.findAllById(sagaVersionRequestDto.getObjectIds())));
        sagaVersionEntity.setMsEntity(new HashSet<>(msRepository.findAllById(sagaVersionRequestDto.getMsIds())));

        return sagaVersionMapper.mapToDto(sagaVersionRepository.save(sagaVersionEntity));
    }

    public void deleteSagaVersionById(int id) {

        if (sagaVersionRepository.existsById(id)){
            removeSagaVersionFromBibEntries(id);
//            removeSagaVersionFromMotifEntries(id);
            removeSagaVersionFromMsEntries(id);
            removeSagaVersionFromObjectEntries(id);
            removeSagaVersionFromPersonEntries(id);
            removeSagaVersionFromPlaceEntries(id);

            sagaVersionRepository.deleteById(id);
        }
        else {
            System.out.println("Record not found in database.");
        }
    }

    private void removeSagaVersionFromBibEntries(int id){
        for (SagaVersionEntity saga : sagaVersionRepository.findAll()) {
            saga.getBibEntity().removeIf(bib -> bib.getId() == id);
        }
    }

    private void removeSagaVersionFromMsEntries(int id){
        Set<MsEntity> msEntitiesSet = new HashSet<>(msRepository.findAll());
        for(MsEntity msEntity : msEntitiesSet){
            Set<SagaVersionEntity> msSagaEntitiesSet = new HashSet<>(msEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : msSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    msSagaEntitiesSet.remove(sagaVersionEntity);
                    msEntity.setSagaVersionEntity(msSagaEntitiesSet);
                    msRepository.save(msEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromObjectEntries(int id){
        Set<ObjectEntity> objectEntitiesSet = new HashSet<>(objectRepository.findAll());
        for(ObjectEntity objectEntity : objectEntitiesSet){
            Set<SagaVersionEntity> objectSagaEntitiesSet = new HashSet<>(objectEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : objectSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    objectSagaEntitiesSet.remove(sagaVersionEntity);
                    objectEntity.setSagaVersionEntity(objectSagaEntitiesSet);
                    objectRepository.save(objectEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromPersonEntries(int id){
        Set<PersonEntity> personEntitiesSet = new HashSet<>(personRepository.findAll());
        for(PersonEntity personEntity : personEntitiesSet){
            Set<SagaVersionEntity> personSagaEntitiesSet = new HashSet<>(personEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : personSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    personSagaEntitiesSet.remove(sagaVersionEntity);
                    personEntity.setSagaVersionEntity(personSagaEntitiesSet);
                    personRepository.save(personEntity);
                }
            }
        }
    }

    private void removeSagaVersionFromPlaceEntries(int id){
        Set<PlaceEntity> placeEntitiesSet = new HashSet<>(placeRepository.findAll());
        for(PlaceEntity placeEntity : placeEntitiesSet){
            Set<SagaVersionEntity> placeSagaEntitiesSet = new HashSet<>(placeEntity.getSagaVersionEntity());
            for(SagaVersionEntity sagaVersionEntity : placeSagaEntitiesSet){
                if (sagaVersionEntity.getId() == id) {
                    placeSagaEntitiesSet.remove(sagaVersionEntity);
                    placeEntity.setSagaVersionEntity(placeSagaEntitiesSet);
                    placeRepository.save(placeEntity);
                }
            }
        }
    }
}

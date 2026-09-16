package com.se.riddaradb.character;

import com.se.riddaradb.location.LocationRepository;
import com.se.riddaradb.sagaversion.SagaVersionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class CharacterService {

    final LocationRepository locationRepository;
    final SagaVersionRepository sagaVersionRepository;
    final CharacterRepository characterRepository;
    final CharacterMapper characterMapper;

    public CharacterService(LocationRepository locationRepository, SagaVersionRepository sagaVersionRepository, CharacterRepository characterRepository, CharacterMapper characterMapper) {
        this.locationRepository = locationRepository;
        this.sagaVersionRepository = sagaVersionRepository;
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    public Collection<CharacterDto> getCharacterEntries(){
        return characterRepository.findAll()
                .stream()
                .map(characterMapper::mapToDto)
                .toList();
    }

    public CharacterDto getCharacterEntryById(int id){
        if (characterRepository.findById(id).isPresent()){
            return characterMapper.mapToDto(characterRepository.findById(id).get());
        }
        else {
            return null;
        }
    }

    public CharacterDto saveCharacterEntry(CharacterDto characterDto){
        CharacterEntity characterEntity = characterMapper.mapFromDto(characterDto);
        characterEntity.setSagaVersionEntity(new HashSet<>(sagaVersionRepository.findAllById(characterDto.getSagaVersionIds())));
        characterEntity.setPlaceEntity(new HashSet<>(locationRepository.findAllById(characterDto.getPlaceIds())));
        return characterMapper.mapToDto(characterRepository.save(characterEntity));
    }

    public void deleteCharacterEntryById(int id) {

        locationRepository.deleteById(id);
    }
}



package com.se.riddaradb.motif;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.riddaradb.bib.BibDto;
import com.se.riddaradb.bib.BibService;
import com.se.riddaradb.saga.SagaRequestDto;
import com.se.riddaradb.saga.SagaResponseDto;
import com.se.riddaradb.saga.SagaService;
import com.se.riddaradb.sagaversion.SagaVersionEntity;
import com.se.riddaradb.sagaversion.SagaVersionMotifDto;
import com.se.riddaradb.sagaversion.SagaVersionRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MotifIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MotifService motifService;

    @Autowired
    private SagaService sagaService;

    @AfterEach
    void cleanDatabase(){
        sagaService.deleteAll();
        motifService.deleteAll();
    }

    @Test
    public void postMotif_shouldReturnMotifWithAllFieldsCorrect() throws Exception {

        MotifDto result = postMotif();

        assertThat(result.getId()).isNotNull();
        assertThat(result.getMotifCode()).isEqualTo(createMotifDto().getMotifCode());
        assertThat(result.getMotifName()).isEqualTo(createMotifDto().getMotifName());
        assertThat(result.getDescription()).isEqualTo(createMotifDto().getDescription());
        assertThat(result.getHasChildren()).isEqualTo(createMotifDto().getHasChildren());
        assertThat(result.getSagaMotifs())
                .singleElement()
                .satisfies(element -> assertThat(element.getPageChapterNumber()).isEqualTo(createMotifSagaVersionDto().getPageChapterNumber()));

    }

    @Test
    public void putMotif_shouldReturnMotifWithAllFieldsCorrect() throws Exception {

        MotifDto motifDto = postMotif();

        motifDto.setMotifCode("New motif code");
        motifDto.setMotifName("New motif name");
        motifDto.setDescription("New description");

        MotifSagaVersionDto motifSagaVersionDto = motifDto.getSagaMotifs().stream().findFirst().orElseThrow();
        motifSagaVersionDto.setPageChapterNumber("New page chapter number");

        motifDto.setSagaMotifs(Set.of(motifSagaVersionDto));

        MvcResult mvcResult = mockMvc.perform(put("/motifs/putmotif")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(motifDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MotifDto result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MotifDto.class);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getMotifCode()).isEqualTo(motifDto.getMotifCode());
        assertThat(result.getMotifName()).isEqualTo(motifDto.getMotifName());
        assertThat(result.getDescription()).isEqualTo(motifDto.getDescription());
        assertThat(result.getHasChildren()).isEqualTo(motifDto.getHasChildren());
        assertThat(result.getSagaMotifs())
                .singleElement()
                .satisfies(element -> assertThat(element.getPageChapterNumber()).isEqualTo(motifSagaVersionDto.getPageChapterNumber()));

    }

    @Test
    public void getMotifs_shouldReturnMotifs() throws Exception {

        postMotif();

        MvcResult mvcResult = mockMvc.perform(get("/motifs/getmotifs"))
                .andExpect(status().isOk())
                .andReturn();

        Set<MotifDto> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>(){});

        assertThat(result).isNotEmpty();
        assertThat(result)
            .singleElement()
            .satisfies(element -> assertThat(element.getId()).isNotNull());
    }

    @Test
    public void deleteMotif_motifIsRemoved() throws Exception{

        MotifDto motifDto = postMotif();

        mockMvc.perform(delete("/motifs/deletemotif/" + motifDto.getId()))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(get("/motifs/getmotifs"))
                .andExpect(status().isOk())
                .andReturn();

        Set<MotifDto> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>(){});

        assertThat(result).isEmpty();
    }



    private MotifDto postMotif() throws Exception{

        MotifDto motifDto = createMotifDto();

        //Set up saga with saga version
        SagaRequestDto sagaRequestDto = createSagaRequestDto();
        sagaRequestDto.setSagaVersions(Set.of(createSagaVersionRequestDto()));
        SagaResponseDto sagaResponseDto = sagaService.saveSaga(sagaRequestDto);

        MotifSagaVersionDto motifSagaVersionDto = createMotifSagaVersionDto();
        motifSagaVersionDto.setSagaVersionId(sagaResponseDto
                .getSagaVersions()
                .stream()
                .findFirst()
                .orElseThrow()
                .getId());

        motifDto.setSagaMotifs(Set.of(motifSagaVersionDto));

        MvcResult mvcResult = mockMvc.perform(post("/motifs/postmotif")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(motifDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MotifDto.class);
    }

    private MotifDto createMotifDto(){
        return new MotifDto(
                null,
                "Motif code",
                "Motif name",
                "Description",
                false
        );
    }

    private SagaRequestDto createSagaRequestDto(){
        return new SagaRequestDto(
                null,
                "Title",
                "Description",
                false);
    }

    private SagaVersionRequestDto createSagaVersionRequestDto() {
        return new SagaVersionRequestDto(
                null,
                "Title",
                "Description",
                SagaVersionEntity.SagaDate.UNDEFINED);
    }

    private MotifSagaVersionDto createMotifSagaVersionDto(){
        return new MotifSagaVersionDto(
                null,
                "Page/chapter number"
        );
    }


}

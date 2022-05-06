package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManuscriptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ManuscriptServiceTest {

//    parameters and external dependency:
//    private ManuscriptRepository manuscriptRepository;

    @Test
    @DisplayName("Test the manuscript is successfully found and no exceptions occur")
    public void getManuscriptByIdFound() {
        // create a mock repository
        ManuscriptRepository manuscriptRepository = mock(ManuscriptRepository.class);

        // create an instance of manuscript
        Manuscript m = new Manuscript();
        m.setId(1);
        given(manuscriptRepository.findById(m.getId())).willReturn(Optional.of(m));
        }

    @Test
    @DisplayName("Test the manuscript when exception occurs")
    public void getManuscriptById_IdNotFound(){

        ManuscriptRepository manuscriptRepository = mock(ManuscriptRepository.class);
        // create an instance of manuscript
        Manuscript m = new Manuscript();
        m.setId(1);
        given(manuscriptRepository.findById(m.getId())).willReturn(Optional.empty());
     }
}

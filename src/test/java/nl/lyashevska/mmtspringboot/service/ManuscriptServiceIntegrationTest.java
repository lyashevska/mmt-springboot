/**
 * https://www.baeldung.com/spring-boot-testing
 */


//        TODO
package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManuscriptRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ManuscriptServiceIntegrationTest {
    @MockBean
    private ManuscriptRepository manuscriptRepository;
    private  ManuscriptService manuscriptService;

    @Test
    void getManuscriptByIdTest() {
        Manuscript m = new Manuscript();
        m.setId(1);
        given(manuscriptRepository.findById(m.getId())).willReturn(Optional.of(m));
    }
}
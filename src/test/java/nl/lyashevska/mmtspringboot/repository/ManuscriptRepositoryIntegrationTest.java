package nl.lyashevska.mmtspringboot.repository;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ManuscriptRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ManuscriptRepository manuscriptRepository;

    // test cases here
    @Test
    void whenFindByAuthor_thenReturnAuthor() {

        // given
        Manuscript man = new Manuscript();
        man.setAuthor("Lyashevska");
        entityManager.persist("Lyashevska");
        entityManager.flush();

        // when
        Manuscript found = manuscriptRepository.findByAuthor("Lyashevska");

        // then
        assertThat(found.getAuthor())
                .isEqualTo(man.getAuthor());
    }

    @Test
    @Disabled("Not implemented yet")
    @DisplayName("Should check whether file upload worked as expected")
    void testUploadContent() throws IOException {
        String pathname = "src/test/resources/Lyashevska2020-class-imbalance.pdf";
        File file = new File(pathname);
        Manuscript man = new Manuscript();

        byte[] bytes = Files.readAllBytes(file.toPath());
        // set content
        man.setContent(bytes);
        // set size
        long fileSize = bytes.length;
        man.setSize(fileSize);

        Manuscript uploadedFile = manuscriptRepository.save(man);
//		Manuscript existFile = entityManager.getId(Manuscript.class, uploadedFile.getId());
        // check if size is equal
//		assertThat(existFile.getSize()).isEqualTo(fileSize);

    }

}
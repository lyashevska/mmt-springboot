/**
 * https://www.baeldung.com/spring-boot-testing
 */

package nl.lyashevska.mmtspringboot;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManuscriptRepository;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

//import static org.assertj.core.api.Assertions.assertThat;

// test JPA queries
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MmtSpringbootApplicationTests {

	@Autowired
	private ManuscriptRepository manuscriptRepository;

	// class provided by DataJpaTest
	@Autowired
	private TestEntityManager entityManager;

	@Test
	@Disabled("Not implemented yet")
	@DisplayName("Should check whethe file upload worked as expected")
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

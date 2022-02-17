package nl.lyashevska.mmtspringboot.repository;

import nl.lyashevska.mmtspringboot.entity.Manuscript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManRepo extends JpaRepository<Manuscript, Integer> {
}

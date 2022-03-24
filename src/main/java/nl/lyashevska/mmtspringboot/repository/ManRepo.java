package nl.lyashevska.mmtspringboot.repository;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManRepo extends JpaRepository<Manuscript, Integer> {
}

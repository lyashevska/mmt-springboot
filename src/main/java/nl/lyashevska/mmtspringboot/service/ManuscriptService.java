package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManuscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManuscriptService {

    @Autowired
    private ManuscriptRepository manuscriptRepository;

    public void add(Manuscript m){
        manuscriptRepository.save(m);
    }

    public List<Manuscript> getAllManuscript(){
        return manuscriptRepository.findAll();
    }

    public Manuscript getManuscriptById(int id){
        Optional<Manuscript> m = manuscriptRepository.findById(id);
        if(m.isPresent()){
            return m.get();
        }
        return null;
    }

    public void deleteManuscript(int id){
        manuscriptRepository.deleteById(id);
    }
}

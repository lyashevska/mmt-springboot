package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManuscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    // method for upload service
    public Manuscript store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Manuscript m = new Manuscript();
        m.setName(fileName);
        m.setType(file.getContentType());
        m.setData(file.getBytes());
        return manuscriptRepository.save(m);
    }

    public Stream<Manuscript> getAllFiles() {
        return manuscriptRepository.findAll().stream();
    }
    // maybe redundant?
    public Manuscript getFile(int id) {
        return manuscriptRepository.findById(id).get();
    }

}

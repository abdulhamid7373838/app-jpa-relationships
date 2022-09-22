package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName) {
            return "This subject already exist";
        }
        subjectRepository.save(subject);
        return "Subject added";
    }

    // READ
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    // UPDATE
    @PutMapping(value = "/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subjectList = optionalSubject.get();
            subjectList.setName(subject.getName());
            subjectRepository.save(subjectList);
            return "Subject edit";
        }
        return "Subject not found"
                ;
    }

    // DELETE
    @DeleteMapping(value = "/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
        return "Subject deleted";
    }
}

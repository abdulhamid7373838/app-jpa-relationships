package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Faculty;
import uz.pdp.appjparelationships.entity.Group;
import uz.pdp.appjparelationships.payload.FacultyDto;
import uz.pdp.appjparelationships.repository.FacultyRepository;
import uz.pdp.appjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {


    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    // VAZIRLIK UCHUN
    // READ
    @GetMapping
    public List<Group> getGroup() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    // UNIVERSITIT MAS'UL XODIMI UCHUN
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupByUniversityId(@PathVariable Integer universityId) {
        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
        List<Group> groupByUniversityId = groupRepository.getGroupByUniversityId(universityId);
        List<Group> groupByUniversityIdNative = groupRepository.getGroupByUniversityIdNative(universityId);
        return allByFaculty_university_id;
    }

    @PostMapping
    public String addGroup(@RequestBody FacultyDto facultyDto) {
        Group group = new Group();
        group.setName(facultyDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(facultyDto.getUniversityId());
        if (!optionalFaculty.isPresent())
            return "Such faculty not found";

        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group added";
    }
}

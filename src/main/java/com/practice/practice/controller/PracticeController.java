package com.practice.practice.controller;

import com.practice.practice.exception.ResourceNotFoundException;
import com.practice.practice.model.Practice;
import com.practice.practice.repository.PracticeRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class PracticeController {
    @Autowired
    private PracticeRepository practiceRepository;

    @GetMapping("/practice")
    public List<Practice> getAllPractices() {
        return practiceRepository.findAll();
    }

    @GetMapping("/practice/{id}")
    public ResponseEntity<Practice> getPracticeById(@PathVariable(value = "id") Long practiceId)
            throws ResourceNotFoundException {
        /*
         * Please try and wrap lines to make sure it fits in a screen for ease in readability
         */
        Practice practice = practiceRepository.findById(practiceId).orElseThrow(() -> new ResourceNotFoundException("Provider not found for this id - " + practiceId));
        return ResponseEntity.ok().body(practice);
    }

    @PostMapping("/practice/create-downtime")
    public Practice createPractice(@RequestBody Practice practice) {
        return practiceRepository.save(practice);
    }

    @PutMapping("/practice/update/{id}")
    public ResponseEntity<Practice> updatePractice(@PathVariable(value = "id") Long practiceId, @RequestBody Practice practiceDetails) throws ResourceNotFoundException {
        Practice practice = practiceRepository.findById(practiceId).orElseThrow(() -> new ResourceNotFoundException("Practice not found for this id - " + practiceId));

        practice.setProvider(practiceDetails.getProvider());
        practice.setFlowName(practiceDetails.getFlowName());
        practice.setDownFrom(practiceDetails.getDownFrom());
        practice.setDownTo(practiceDetails.getDownTo());

        final Practice updatedPractice = practiceRepository.save(practice);

        return ResponseEntity.ok(updatedPractice);
    }

    @DeleteMapping("/practice/delete/{id}")
    public Map<String, Boolean> deletePractice(@PathVariable(value="id") Long practiceId) throws ResourceNotFoundException{
        Practice practice = practiceRepository.findById(practiceId).orElseThrow(()-> new ResourceNotFoundException("Practice not found for this id - " + practiceId));

        practiceRepository.delete(practice);
        Map<String, Boolean> response= new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

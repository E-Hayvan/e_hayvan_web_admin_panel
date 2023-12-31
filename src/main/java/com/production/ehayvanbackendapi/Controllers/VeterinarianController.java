package com.production.ehayvanbackendapi.Controllers;

import com.production.ehayvanbackendapi.DTO.VeterinarianDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdateVeterinarianDTO;
import com.production.ehayvanbackendapi.Services.VeterinarianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veterinarians")
public class VeterinarianController {
    private final VeterinarianService veterinarianService;

    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianDTO> getVeterinarianById(@PathVariable Integer id) {
        VeterinarianDTO veterinarianDTO = veterinarianService.getVeterinarianById(id);

        if (veterinarianDTO != null) {
            return new ResponseEntity<>(veterinarianDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<VeterinarianDTO>> getVeterinariansByName(@PathVariable String name){
        List<VeterinarianDTO> veterinarianDTOS = veterinarianService.getVeterinariansByClinic(name);

        if(veterinarianDTOS != null){
            return new ResponseEntity<>(veterinarianDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VeterinarianDTO> saveVeterinarian(@RequestBody CreateOrUpdateVeterinarianDTO veterinarianDTO) {
        VeterinarianDTO savedVeterinarian = veterinarianService.postVeterinarian(veterinarianDTO);

        if(savedVeterinarian != null){
            return new ResponseEntity<>(savedVeterinarian, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VeterinarianDTO> updateVeterinarian(@PathVariable Integer id,
                                                              @RequestBody CreateOrUpdateVeterinarianDTO veterinarianDTO){
        VeterinarianDTO updatedVeterinarian = veterinarianService.updateVeterinarian(id, veterinarianDTO);

        if(updatedVeterinarian != null){
            return new ResponseEntity<>(updatedVeterinarian, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VeterinarianDTO> deleteVeterinarian(@PathVariable Integer id){
        VeterinarianDTO deletedVeterinarian = veterinarianService.deleteVeterinarian(id);

        if(deletedVeterinarian != null){
            return new ResponseEntity<>(deletedVeterinarian, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Other controller methods for updating, deleting veterinarians, etc.
}


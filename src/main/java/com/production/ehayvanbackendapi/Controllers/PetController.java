package com.production.ehayvanbackendapi.Controllers;

import com.production.ehayvanbackendapi.DTO.PetDTO;
import com.production.ehayvanbackendapi.Services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Integer id) {
        PetDTO petDTO = petService.getPetById(id);

        if (petDTO != null) {
            return new ResponseEntity<>(petDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping
//    public ResponseEntity<List<PetDTO>> getAllPets() {
//        List<PetDTO> petDTOList = petService.getAllPets();
//        return new ResponseEntity<>(petDTOList, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<PetDTO> savePet(@RequestBody PetDTO petDTO) {
//        PetDTO savedPet = petService.savePet(petDTO);
//        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
//    }

    // Other controller methods for updating, deleting pets, etc.
}

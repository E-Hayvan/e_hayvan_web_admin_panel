package com.production.ehayvanbackendapi.Controllers;

import com.production.ehayvanbackendapi.DTO.PetOwnerDTO;
import com.production.ehayvanbackendapi.DTO.request.CreateOrUpdatePetOwnerDTO;
import com.production.ehayvanbackendapi.Services.PetOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/petowners")
public class PetOwnerController {
    private final PetOwnerService petOwnerService;

    public PetOwnerController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetOwnerDTO> getPetOwnerById(@PathVariable Integer id) {
        PetOwnerDTO petOwnerDTO = petOwnerService.getPetOwnerById(id);

        if (petOwnerDTO != null) {
            return new ResponseEntity<>(petOwnerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/newowner")
    public ResponseEntity<PetOwnerDTO> savePetOwner(@RequestBody CreateOrUpdatePetOwnerDTO petOwnerDTO) {
        PetOwnerDTO savedPetOwner = petOwnerService.postPetOwner(petOwnerDTO);

        if (petOwnerDTO != null){
            return new ResponseEntity<>(savedPetOwner, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PetOwnerDTO> deletePetOwner(@PathVariable Integer id){
        PetOwnerDTO deletedPetOwner = petOwnerService.deletePetOwner(id);

        if(deletedPetOwner != null){
            return new ResponseEntity<>(deletedPetOwner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Other controller methods for updating, deleting pet owners, etc.
}


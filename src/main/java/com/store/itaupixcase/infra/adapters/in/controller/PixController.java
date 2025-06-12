package com.store.itaupixcase.infra.adapters.in.controller;

import com.store.itaupixcase.app.PixService;
import com.store.itaupixcase.infra.adapters.in.dto.consult.FiltersDto;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixRequestDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixResponseDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixRequestUpdateDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
public class PixController {

    public final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping
    public ResponseEntity<PixResponseDTO> registerPixKey(@RequestBody @Valid PixRequestDTO requestDTO){
        return ResponseEntity.status(200).body(pixService.registerPixKey(requestDTO));
    }

    @PutMapping
    public ResponseEntity<PixResponseUpdateDTO> updatePixKey(@RequestBody PixRequestUpdateDTO requestDTO){
        return ResponseEntity.status(200).body(pixService.updatePixKey(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<PixRequestConsultDTO>> getPixKey(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String keyType,
            @RequestParam(required = false) String keyValue,
            @RequestParam(required = false) String accountType,
            @RequestParam(required = false) Integer accountNumber,
            @RequestParam(required = false) Integer agencyNumber,
            @RequestParam(required = false) String accountHolderName,
            @RequestParam(required = false) String createdTime,
            @RequestParam(required = false) String inactiveTime
    ) {

        return ResponseEntity.status(200).body(pixService.getPixKey(new FiltersDto(
                id,
                keyType,
                keyValue,
                accountType,
                accountNumber,
                agencyNumber,
                accountHolderName,
                createdTime,
                inactiveTime
        )));
    }
}

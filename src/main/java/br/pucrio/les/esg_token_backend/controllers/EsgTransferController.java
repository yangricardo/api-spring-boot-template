package br.pucrio.les.esg_token_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pucrio.les.esg_token_backend.models.EsgTransfer;
import br.pucrio.les.esg_token_backend.services.esg_transfer.IEsgTransferService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/esg-transfers")
public class EsgTransferController extends BaseController {

    @Autowired
    private IEsgTransferService esgTransferService;

    public EsgTransferController(IEsgTransferService esgTransferService) {
        this.esgTransferService = esgTransferService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Find all Esg Transfers")
    ResponseEntity<?> index() {
        List<EsgTransfer> esgTransfers = this.esgTransferService.index();
        if (esgTransfers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(esgTransfers);
    }

    @PostMapping
    @ApiOperation(value = "Create Esg Transfer")
    ResponseEntity<?> createEsgTransfer(@RequestBody EsgTransfer esgTransfer) {
        EsgTransfer esgTransferCreated = this.esgTransferService.create(esgTransfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(esgTransferCreated);
    }

}

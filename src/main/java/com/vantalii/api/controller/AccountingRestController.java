package com.vantalii.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.model.transfer.TransferContract;
import com.vantalii.data.service.IAccountingService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/api/v1/")
public class AccountingRestController {
    @Autowired
    private IAccountingService accountingService;

    @RequestMapping(value = "/accounting/contracts/transfer", method = RequestMethod.GET)
    public ResponseEntity<List> getTransferContracts() {
	List<TransferContract> transferContracts = accountingService.getTransferContracts();
	ResponseEntity<List> response = new ResponseEntity<List>(transferContracts, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/accounting/contracts/transfer/save", method = RequestMethod.POST)
    public ResponseEntity<Integer> saveTransferContract(RequestEntity<TransferContract> requestEntity) {
	TransferContract transferContract = requestEntity.getBody();
	int transferContractId = accountingService.saveTransferContract(transferContract);
	ResponseEntity<Integer> response = new ResponseEntity<Integer>(transferContractId, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/accounting/contracts/transfer/{contractId}", method = RequestMethod.GET)
    public ResponseEntity<TransferContract> getTransferContractById(@PathVariable int contractId) {
	TransferContract transferContract = accountingService.getTransferContractById(contractId);
	ResponseEntity<TransferContract> response = new ResponseEntity<TransferContract>(transferContract, HttpStatus.OK);

	return response;
    }

}

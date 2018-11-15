package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.ClienteDto;
import com.example.enums.Tipo;
import com.example.model.Cliente;
import com.example.services.ClienteService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> listar(){
		List<ClienteDto> retorno = new ArrayList<>();
		
		List<Cliente> clientes = clienteService.listar();
		
		if(clientes.isEmpty()) {
			return ResponseEntity.badRequest().body(retorno);
		}
		
		clientes.forEach(c->retorno.add(new ClienteDto(c)));
		
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> salvar(@Valid @RequestBody ClienteDto clienteDto, BindingResult result){
		Cliente cliente = validar(clienteDto, result);
		
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(clienteDto);
		}
		
		clienteService.salvar(cliente);
		
		return ResponseEntity.ok(clienteDto);
	}

	private Cliente validar(@Valid ClienteDto clienteDto, BindingResult result) {
		Cliente cliente = new Cliente();
		cliente.setLimiteCredito(clienteDto.getLimite());
		cliente.setNome(clienteDto.getNome());
		
		if (clienteDto.getId() != null) {
			cliente.setId(clienteDto.getId());
		}
		
		if (EnumUtils.isValidEnum(Tipo.class, clienteDto.getTipo())) {
			cliente.setTipo(EnumUtils.getEnum(Tipo.class, clienteDto.getTipo()));
		}else {
			result.addError(new ObjectError("Tipo", "Tipo inválido."));
		}
		
		return cliente;
	}

}

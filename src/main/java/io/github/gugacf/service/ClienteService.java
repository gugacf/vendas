package io.github.gugacf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.gugacf.modelo.Cliente;
import io.github.gugacf.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void salvar(Cliente cliente) {
		validar(cliente);
		this.clienteRepository.persistir(cliente);
	}

	public void validar(Cliente cliente) {
		
	}
}

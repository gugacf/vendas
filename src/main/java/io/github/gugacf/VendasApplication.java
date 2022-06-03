package io.github.gugacf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gugacf.domain.entity.Cliente;
import io.github.gugacf.domain.repository.ClienteRepository;

@SpringBootApplication
@RestController
public class VendasApplication {
	
	@Value("${application.name}")
	private String applicationName;
		
	@GetMapping("/ola")
	public String olaMundo() {
		return applicationName;
	}
	
	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
		return args -> {
			System.out.println("Salvando");
			clienteRepository.save(new Cliente("Gustavo C Freitas"));
			clienteRepository.save(new Cliente("Maria Adélia"));
			
			List<Cliente> clientes = clienteRepository.findAll();			
			clientes.forEach(System.out::println);
			
			System.out.println("Atualizando");
			clientes.forEach(c -> {
				c.setNome(c.getNome() + " Atualizado");
				clienteRepository.save(c);
			});
			
			clientes = clienteRepository.findAll();			
			clientes.forEach(System.out::println);
			
			System.out.println("Buscando por nome");
			clienteRepository.findByNomeLike("Ma").forEach(System.out::println);
			
			System.out.println("Apagando");
			clienteRepository.findAll().forEach(c -> {
				clienteRepository.delete(c);
			});

			clientes = clienteRepository.findAll();
			if (clientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado");
			}
			else {
				clientes.forEach(System.out::println);
			}			

//			System.out.println("Salvando");
//			clienteRepository.salvar(new Cliente("Gustavo C Freitas"));
//			clienteRepository.salvar(new Cliente("Maria Adélia"));
//			
//			List<Cliente> clientes = clienteRepository.listarTodos();			
//			clientes.forEach(System.out::println);
//			
//			System.out.println("Atualizando");
//			clientes.forEach(c -> {
//				c.setNome(c.getNome() + " Atualizado");
//				clienteRepository.atualizar(c);
//			});
//			
//			clientes = clienteRepository.listarTodos();			
//			clientes.forEach(System.out::println);
//			
//			System.out.println("Buscando por nome");
//			clienteRepository.buscarPorNome("Ma").forEach(System.out::println);
//			
//			System.out.println("Apagando");
//			clienteRepository.listarTodos().forEach(c -> {
//				clienteRepository.deletar(c);
//			});
//
//			clientes = clienteRepository.listarTodos();
//			if (clientes.isEmpty()) {
//				System.out.println("Nenhum cliente encontrado");
//			}
//			else {
//				clientes.forEach(System.out::println);
//			}			
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}

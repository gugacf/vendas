package io.github.gugacf.domain.repository;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.gugacf.domain.entity.Cliente;

@Repository
public class ClienteRepository {
	
//	private static String INSERT = "insert into cliente (nome) values (?)";
//	private static String SELECT_ALL = "select * from cliente";
//	private static String UPDATE = "update cliente set nome = ? where id = ?";
//	private static String DELETE = "delete from cliente where id = ?";
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional //status transiente
	public Cliente salvar(Cliente cliente) {
		//jdbcTemplate.update(INSERT, new Object[] {cliente.getNome()});
		entityManager.persist(cliente);
		return cliente;
	}

	@Transactional //status manager
	public Cliente atualizar(Cliente cliente) {
//		jdbcTemplate.update(UPDATE, new Object[] {
//				cliente.getNome(),
//				cliente.getId()
//		});		
		entityManager.merge(cliente);
		return cliente;
	}
	
	@Transactional
	public void deletar(Cliente cliente) {
		//deletar(cliente.getId());
		if (!entityManager.contains(cliente)) {
			entityManager.merge(cliente);
		}
		entityManager.remove(cliente);
	}
	
	@Transactional
	public void deletar(Integer id) {
		//jdbcTemplate.update(DELETE, new Object[] {id});
		Cliente cliente = entityManager.find(Cliente.class, id);
		deletar(cliente);
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNome(String nome) {
//		return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"),
//				new Object[] {"%" + nome + "%"},
//				clienteMapper());
		String jpql = "select c from Cliente c where c.nome like :nome";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> listarTodos() {
		//return jdbcTemplate.query(SELECT_ALL, clienteMapper());
		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
	}

//	private RowMapper<Cliente> clienteMapper() {
//		return new RowMapper<Cliente>() {
//			@Override
//			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Integer id = rs.getInt("id");
//				String nome = rs.getString("nome");
//				return new Cliente(id, nome);
//			}
//		};
//	}
	
}

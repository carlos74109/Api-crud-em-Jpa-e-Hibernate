package teste.basico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Conexao {

	public void inserirDados(String nome, String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaJpa");
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = new Usuario(nome, email);
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	public void obterDados(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaJpa");
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, id);
		
		System.out.println("nome: " + usuario.getNome());
		System.out.println("Email: " + usuario.getEmail());
		
		em.close();
		emf.close();
	}
	
	public void obterVariosDados() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaJpa");
		EntityManager em = emf.createEntityManager();
		
		String jqpl = "select i from Usuario i";
		
		TypedQuery<Usuario> query = em.createQuery(jqpl, Usuario.class);
		
		List<Usuario> usuarios = query.getResultList();
		
		for(Usuario user : usuarios) {
			System.out.println("Id: " + user.getId() + ", Nome: " + user.getNome() + ", Email: " + user.getNome() );
			System.out.println("//////////////////////////////");
		}
		em.close();
		emf.close();
	}
	
	public void alterarDados(int id, String nome, String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaJpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, id);
		
		usuario.setEmail(email);
		usuario.setNome(nome);
		em.merge(usuario);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	public void removerDados(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaJpa");
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, id);
		
		if(usuario != null) {
			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
		
	}
	
}

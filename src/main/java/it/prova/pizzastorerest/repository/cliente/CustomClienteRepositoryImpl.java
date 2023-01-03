package it.prova.pizzastorerest.repository.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastorerest.model.Cliente;

public class CustomClienteRepositoryImpl implements CustomClienteRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cliente> findByExample(Cliente example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Cliente c left join fetch c.ordini o where c.id = c.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" c.nome like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" c.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getInidirizzo())) {
			whereClauses.add(" c.inidirizzo like :inidirizzo ");
			paramaterMap.put("inidirizzo", "%" + example.getInidirizzo() + "%");
		}
		if (example.isAttivo()) {
			whereClauses.add(" c.attivo like :attivo ");
			paramaterMap.put("attivo", example.isAttivo());
		}
		if (example.getOrdini() != null && example.getOrdini().size() > 0) {
			whereClauses.add(" o in :ordini ");
			paramaterMap.put("ordini", example.getOrdini());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}

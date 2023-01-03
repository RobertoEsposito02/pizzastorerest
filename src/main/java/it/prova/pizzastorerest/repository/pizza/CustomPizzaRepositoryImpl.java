package it.prova.pizzastorerest.repository.pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastorerest.model.Pizza;

public class CustomPizzaRepositoryImpl implements CustomPizzaRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Pizza> findByExample(Pizza example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select p from Pizza p where p.id = p.id ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" c.descrizione like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getIngredienti() != null && example.getIngredienti().length > 0) {
			whereClauses.add(" p.ingredienti in :ingredienti ");
			paramaterMap.put("ingredienti", example.getIngredienti());
		}
		if(example.getPrezzoBase() != null) {
			whereClauses.add(" p.prezzoBase >= :prezzo ");
			paramaterMap.put("prezzo", example.getPrezzoBase());
		}
		if (example.isAttivo()) {
			whereClauses.add(" c.attivo like :attivo ");
			paramaterMap.put("attivo", example.isAttivo());
		}

		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Pizza> typedQuery = entityManager.createQuery(queryBuilder.toString(), Pizza.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}

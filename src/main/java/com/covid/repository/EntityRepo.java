package com.covid.repository;

import static com.covid.util.CovidUtils.cast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import com.covid.model.BaseModel;
import com.covid.repository.Cond.Oper;
import com.covid.repository.Cond.Sort;
import com.covid.support.CopyHelper;



@Component("entityRepo")
@Transactional
@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE)
@RequestScope
public class EntityRepo {
	// private static final Logger log = LogManager.getLogger();

	@PersistenceContext
	private EntityManager em;

	public <T> Cond<T> get(Class<T> clazz) {
		return Cond.of(clazz);
	}

	public <T> T findByPrimaryKey(Class<T> entityClass, Object primaryKey) {
		return em.find(entityClass, primaryKey);
	}

	public <T> T execStoredProcedureQuery(Function<EntityManager, T> exec) {
		return exec.apply(em);

	}

	public <T> T execute(Function<EntityManager, T> module) {
		return module.apply(em);
	}

	@SuppressWarnings({ "rawtypes" })
	public<E extends BaseModel> SingularAttribute<E, Comparable> getAttr(Class<E> clazz, String fieldName) {
		ManagedType<E> data = em.getMetamodel().managedType(clazz);
		SingularAttribute<E, Comparable> info = cast(data.getSingularAttribute(fieldName));
		return info;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E extends BaseModel> List<E> findByExample(@NonNull E entity) {
		try {
			Map<String, Comparable> values = new HashMap<>();
			List<SingularAttribute<E, Comparable>> attrs = new ArrayList<>();
			CopyHelper.cp(cast(entity), cast(entity), (s, t) -> {
				Object value = t.getValue();
				if (value != null) {
					String name = s.getKey().getName();
					SingularAttribute<E, Comparable> att = cast(getAttr(entity.getClass(), name));
					if (att != null) {
						attrs.add(att);
						values.put(name, cast(value));
					}
				}
				return Boolean.FALSE;
			});


			Cond<E> cond = cast(Cond.of(entity.getClass()));
			for (SingularAttribute<E, Comparable> att : attrs) {
				if (att != null) {
					Comparable value = values.get(att.getName());
					cond.add(att, cast(value));
				}
			}

			return this.find(cond);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public <E> List<E> find(Cond<E> cond) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<E> query = cb.createQuery(cond.getEntityClass());
		Root<E> from = query.from(cond.getEntityClass());
		CriteriaQuery<E> select = query.select(from);

		// ManagedType<Account> o = em.getMetamodel().managedType(Account.class);
		// SingularAttribute<? super Account, ?> c =
		// o.getSingularAttribute(Account_.s_status);
		int index = 0;
		Predicate[] preds = new Predicate[cond.getConditions().size()];
		for (Triple<SingularAttribute<E, ?>, Oper, Comparable<?>> con : cond.getConditions()) {
			SingularAttribute<E, ?> column = con.getLeft();
			Comparable<?> value = con.getRight();
			Oper op = con.getMiddle();
			Predicate pred = op.getPredicate().exec(cb, from, column, value);
			preds[index++] = pred;
		}

		if (preds.length > 0) {
			query.where(preds);
		}

		if (cond.getOrderList().isEmpty() == false) {
			List<Order> orders = new ArrayList<>();
			for (Pair<SingularAttribute<E, ?>, Sort> item : cond.getOrderList()) {
				SingularAttribute<E, ?> column = item.getKey();
				Sort sort = item.getValue();
				Order order = sort.getOrder().exec(cb, from, column);
				orders.add(order);
			}
			select.orderBy(orders);
		}

		TypedQuery<E> typedQuery = em.createQuery(select);
		List<E> result = typedQuery.getResultList();

		return result;
	}

	public final <E> E findOne(Cond<E> conditions) {
		List<E> results = find(conditions);
		E result = null;
		if (results.isEmpty() == false) {
			result = results.get(0);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <E extends BaseModel<E, ?>> void save(E... entities) {
		for (E en : entities) {
			em.persist(en);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public <T> void delete(T... entities) {
		for (T en : entities) {
			if (en != null) {
				em.remove(en);
			}
		}
	}

	@PreDestroy
	@Transactional
	public void preDestory() {
		System.out.println("************** close ");
		em.flush();
		em.close();
	}


}

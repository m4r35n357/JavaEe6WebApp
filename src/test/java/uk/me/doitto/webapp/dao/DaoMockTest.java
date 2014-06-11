/**
 * Copyright (C) 2010 Ian C. Smith <m4r35n357@gmail.com>
 *
 * This file is part of JavaEE6Webapp.
 *
 *     JavaEE6Webapp is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     JavaEE6Webapp is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with JavaEE6Webapp.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 
 */
package uk.me.doitto.webapp.dao;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author ian
 *
 */
public class DaoMockTest extends EasyMockSupport {
	
	// UUT
	private SimpleDao dao;
	
	private SimpleEntity entity;
	
	private EntityManager em;
	
	private TypedQuery<SimpleEntity> tq;
	
	
	private CriteriaQuery<SimpleEntity> cq;
	
	private CriteriaQuery<Long> cqLong;
	
	private TypedQuery<Long> tqLong;
	
	private Expression<Long> expLong;
	
	private Expression<?> expression;
	
	private CriteriaBuilder cb;
	
	private Root<SimpleEntity> root;
	
	private Path<Date> path;
	
	private Predicate predicate;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		em = createStrictMock(EntityManager.class);
		cb = createStrictMock(CriteriaBuilder.class);
		cq = createStrictMock(CriteriaQuery.class);
		cqLong = createStrictMock(CriteriaQuery.class);
		root = createStrictMock(Root.class);
		tq = createStrictMock(TypedQuery.class);
		tqLong = createStrictMock(TypedQuery.class);
		expLong = createStrictMock(Expression.class);
		path = createStrictMock(Path.class);
		predicate = createStrictMock(Predicate.class);
		
		dao = new SimpleDao(em);
		entity = new SimpleEntity();
		entity.id = 1L;
		entity.version = 1;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#Crud(java.lang.Class)}.
	 */
	@Test
	public final void testCrud () {
		SimpleDao daoClass = new SimpleDao();
		assertNotSame("", this.dao, daoClass);
		assertNull("Injected field should be null here!", daoClass.getEm());
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#Crud(java.lang.Class, javax.persistence.EntityManager)}.
	 */
	@Test
	public final void testCrudEntityManager () {
		SimpleDao daoClass = new SimpleDao(em);
		assertNotSame("", this.dao, daoClass);
		assertSame("Injected field should be overridden by this constructor!", em, daoClass.getEm());
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#Crud(java.lang.Class, javax.persistence.EntityManager)}.
	 */
	@Test
	public final void testCrudNullEntityManager () {
		try {
			@SuppressWarnings("unused")
			SimpleDao daoClassNullEm = new SimpleDao(null);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#create(uk.me.doitto.webapp.dao.AbstractEntity)}.
	 */
	@Test
	public final void testCreate () {
		em.persist(EasyMock.isA(SimpleEntity.class));
		replayAll();
		dao.create(entity);
		verifyAll();
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#create(uk.me.doitto.webapp.dao.AbstractEntity)}.
	 */
	@Test
	public final void testCreateNull () {
		try {
			dao.create(null);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#delete(java.lang.Long)}.
	 */
	@Test
	public final void testDelete () {
		expect(em.find(EasyMock.eq(SimpleEntity.class), EasyMock.isA(Long.class))).andReturn(entity);
		em.remove(EasyMock.isA(SimpleEntity.class));
		replayAll();
		dao.remove(entity.getId());
		verifyAll();
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#delete(java.lang.Long)}.
	 */
	@Test
	public final void testDeleteNullBadId () {
		SimpleEntity entity = new SimpleEntity();
		try {
			dao.remove(entity.getId());
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		entity.id = -1L;
		try {
			dao.remove(entity.getId());
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#update(uk.me.doitto.webapp.dao.AbstractEntity)}.
	 */
	@Test
	public final void testUpdate () {
		expect(em.merge(EasyMock.isA(SimpleEntity.class))).andReturn(entity);
		replayAll();
		dao.update(entity);
		verifyAll();
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#update(uk.me.doitto.webapp.dao.AbstractEntity)}.
	 */
	@Test
	public final void testUpdateNull () {
		try {
			dao.update(null);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#find(java.lang.Long)}.
	 */
	@Test
	public final void testFind () {
		expect(em.find(EasyMock.eq(SimpleEntity.class), EasyMock.isA(Long.class))).andReturn(entity);
		replayAll();
		dao.find(entity.getId());
		verifyAll();
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#find(java.lang.Long)}.
	 */
	@Test
	public final void testFindNullBadId () {
		SimpleEntity entity = new SimpleEntity();
		try {
			dao.find(entity.getId());
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		entity.id = -1L;
		try {
			dao.find(entity.getId());
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#findAll()}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testFindAll () {
		expect(em.getCriteriaBuilder()).andReturn(cb);
		expect(cb.createQuery(SimpleEntity.class)).andReturn(cq);
		expect(cq.from(SimpleEntity.class)).andReturn(root);
		expect(cq.select(root)).andReturn(cq);
		expect(em.createQuery(EasyMock.isA(CriteriaQuery.class))).andReturn(tq);
		expect(tq.setFirstResult(0)).andReturn(tq);
		expect(tq.setMaxResults(0)).andReturn(tq);
		expect(tq.getResultList()).andReturn(new ArrayList<SimpleEntity>());
		replayAll();
		dao.findAll();
		verifyAll();
	}
	
	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#findAll()}.
	 */
	@Test
	public final void testFindAllBadParams () {
		try {
			dao.findAllRange(0, -1);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.findAllRange(-1, 0);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}
	
	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#findByNamedQuery(java.lang.String, java.util.Map, int, int)}.
	 */
	@Test
	public final void testFindByNamedQuery () {
		int firstResult = 1;
		int noOfResults = 100;
		Map<String, Object> parameters = new HashMap<String, Object>();
//		mockEm.checkOrder(mockEm, false);
		parameters.put("p1", "v1");
//		parameters.put("p2", "v2");
		expect(em.createNamedQuery(EasyMock.isA(String.class), EasyMock.eq(SimpleEntity.class))).andReturn(tq);
//		expect(mockTq.setFirstResult(firstResult)).andReturn(mockTq);
//		expect(mockTq.setMaxResults(noOfResults)).andReturn(mockTq);
		expect(tq.setParameter("p1", "v1")).andReturn(tq);
//		expect(mockTq.setParameter("p2", "v2")).andReturn(mockTq);
		expect(tq.setFirstResult(firstResult)).andReturn(tq);
		expect(tq.setMaxResults(noOfResults)).andReturn(tq);
		expect(tq.getResultList()).andReturn(new ArrayList<SimpleEntity>());
		replayAll();
		dao.findByNamedQueryRange("query string", parameters, firstResult, noOfResults);
		verifyAll();
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#findByNamedQuery(java.lang.String, java.util.Map, int, int)}.
	 */
	@Test
	public final void testFindByNamedQueryBadParams () {
		try {
			dao.findByNamedQueryRange(null, new HashMap<String, Object>(), 0, 0);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.findByNamedQueryRange("", new HashMap<String, Object>(), 0, 0);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.findByNamedQueryRange("query string", null, 0, 0);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.findByNamedQueryRange("query string", new HashMap<String, Object>(), -1, 0);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.findByNamedQueryRange("query string", new HashMap<String, Object>(), 0, -1);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	@Ignore
	@Test
	@SuppressWarnings("unchecked")
	public final void testBefore () {
		expect(em.getCriteriaBuilder()).andReturn(cb);
		expect(cb.createQuery(SimpleEntity.class)).andReturn(cq);
		expect(cq.from(SimpleEntity.class)).andReturn(root);
		expect(root.get(EasyMock.isA(SingularAttribute.class))).andReturn(path);
		expect(cb.lessThan(EasyMock.isA(Path.class), EasyMock.isA(Date.class))).andReturn(predicate);
		expect(cq.where(predicate)).andReturn(cq);
		expect(cq.select(root)).andReturn(cq);		
		expect(em.createQuery(EasyMock.isA(CriteriaQuery.class))).andReturn(tq);
		expect(tq.getResultList()).andReturn(new ArrayList<SimpleEntity>());
		replayAll();
		dao.before(TimeStamp.created, new Date());
		verifyAll();
	}
	
	@Test
	public final void testBeforeBadParams () {
		try {
			dao.before(null, new Date());
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.before(TimeStamp.created, null);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	@Test
	public final void testSinceBadParams () {
		try {
			dao.since(null, new Date());
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.since(TimeStamp.created, null);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	@Test
	public final void testDuringBadParams () {
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() + (1000 * 60));
		try {
			dao.during(null, date1, date2);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.during(TimeStamp.created, null, date2);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.during(TimeStamp.created, date1, null);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.during(TimeStamp.created, date2, date1);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	@Test
	public final void testNotDuringBadParams () {
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() + (1000 * 60));
		try {
			dao.notDuring(null, date1, date2);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.notDuring(TimeStamp.created, null, date2);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.notDuring(TimeStamp.created, date1, null);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
		try {
			dao.notDuring(TimeStamp.created, date2, date1);
			fail("Should not reach here!");
		} catch (AssertionError e) {
		}
	}

	/**
	 * Test method for {@link uk.me.doitto.webapp.dao.Crud#count()}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testCount () {
		expect(em.getCriteriaBuilder()).andReturn(cb);
		expect(cb.createQuery(Long.class)).andReturn(cqLong);
		expect(cqLong.from(SimpleEntity.class)).andReturn(root);
		expect(cb.count(root)).andReturn(expLong);
		expect(cqLong.select(expLong)).andReturn(cqLong);
		expect(em.createQuery(EasyMock.isA(CriteriaQuery.class))).andReturn(tqLong);
		expect(tqLong.getSingleResult()).andReturn(Long.valueOf(42));
		replayAll();
		dao.count();
		verifyAll();
	}
}

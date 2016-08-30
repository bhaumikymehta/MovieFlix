package com.bhaumik.movieFlix.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bhaumik.movieFlix.api.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> findAllUser() {

		TypedQuery<User> query=em.createQuery("SELECT u FROM User u ORDER BY u.email asc",User.class);
		return query.getResultList();
		
	}

	@Override
	public User createUser(User user) {

		em.persist(user);
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		TypedQuery<User> query=em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("uemail",email);
		List<User> users=query.getResultList();
		if(users.size()==1 && users!=null){
			return users.get(0);
		}
		return null;
	}

	@Override
	public User findUserById(String userId) {
	
	return em.find(User.class,userId);
	}

	@Override
	public User updateUser(User user) {

		return em.merge(user);
		
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		em.remove(em.find(User.class, userId));
		
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		TypedQuery<User> query=em.createNamedQuery("User.findByEmailAndPassword", User.class);
		query.setParameter("uemail",email);
		query.setParameter("upasswords",password);
		List<User> users=query.getResultList();
		if(users.size()==1 && users!=null){
			return users.get(0);
		}
		return null;
	}

}

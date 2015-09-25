package se.leanbit.ticketsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import se.leanbit.ticketsystem.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
	@Query("select u from User u where u.userID = ?1")
	User getUser(final String userID);

	@Query("select u from User u where u.userName = ?1")
	User getUserWithUsername(final String userID);

	@Query("select u from User u where u.firstName = ?1")
	List<User> getUsersWithFirstName(final String userID);

	@Query("select u from User u where u.lastName = ?1")
	List<User> getUsersWithLastName(final String userID);

	@Query("select u from User u where u.team.teamName = ?1")
	List<User> getUsersByTeamName(final String teamName);

	@Transactional
	@Modifying
	@Query("delete from User u where u.userID = ?1")
	void remove(final String userID);
}
package se.leanbit.ticketsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import se.leanbit.ticketsystem.model.Team;
import org.springframework.data.repository.CrudRepository;
import se.leanbit.ticketsystem.model.User;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
	@Query("select t from Team t where t.teamName = ?1")
	Team getTeam(final String teamName);

	@Query("select u from User u where u.team.teamName = ?1")
	List<User> getUsersFromTeam(final String teamName);

	@Transactional
	@Modifying
	@Query("delete from Team t where t.teamName = ?1")
	void removeTeam(final String teamName);
}
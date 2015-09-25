package se.leanbit.ticketsystem.service.interfaces;

import java.util.List;

import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;

public interface TeamServiceInterface {
	public Team addTeam(final Team team);

	public Team getTeam(final String teamName);

	public void removeTeam(final String teamName);

	public List<Team> getAllTeams();

	public List<User> getAllUsersFromTeam(final String teamName);
}

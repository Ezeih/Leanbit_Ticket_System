package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.exception.TeamServiceException;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.repository.TeamRepository;
import se.leanbit.ticketsystem.service.interfaces.TeamServiceInterface;

import java.util.List;

public class TeamService implements TeamServiceInterface {
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public Team addTeam(final Team team) {
		try {
			return teamRepository.save(team);
		} catch (final Exception exception) {
			throw new TeamServiceException("TeamService: Could not addTeam!", exception);
		}
	}

	@Override
	public Team getTeam(final String teamName) {
		try {
			return teamRepository.getTeam(teamName);
		} catch (final Exception exception) {
			throw new TeamServiceException("TeamService: Could not getTeam!", exception);
		}
	}

	@Override
	public void removeTeam(final String teamName) {
		try {
			teamRepository.removeTeam(teamName);
		} catch (final Exception exception) {
			throw new TeamServiceException("TeamService: Could not removeTeam!", exception);
		}
	}

	@Override
	public List<Team> getAllTeams() {
		try {
			return (List<Team>) teamRepository.findAll();
		} catch (final Exception exception) {
			throw new TeamServiceException("TeamService: Could not getAllTeams!", exception);
		}
	}

	@Override
	public List<User> getAllUsersFromTeam(String teamName) {
		try {
			return getAllUsersFromTeam(teamName);
		} catch (final Exception exception) {
			throw new TeamServiceException("TeamService: Could not getAllUsersFromTeam!", exception);
		}
	}

}

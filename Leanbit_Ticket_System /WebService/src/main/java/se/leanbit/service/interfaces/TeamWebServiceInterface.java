package se.leanbit.service.interfaces;

import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;

import javax.ws.rs.core.Response;
import java.util.List;

public interface TeamWebServiceInterface {
	public Response addTeam(final Team team);

	public Response getTeam(final String teamName);

	public Response removeTeam(final String teamName);

	public Response getAllTeams();

	public Response getAllUsersFromTeam(final String teamName);
}

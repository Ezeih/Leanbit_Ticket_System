package se.leanbit.service.interfaces;

import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;

import javax.ws.rs.core.Response;
import java.util.List;

public interface UserWebServiceInterface {
	public Response addUser(final User user);

	public Response updateUser(final User user);

	public Response removeUser(final String userID);

	public Response getUserWithID(final String userID);

	public Response getUserWithUserName(final String userName);

	public Response getUsersWithFirstName(final String firstName);

	public Response getUsersWithLastName(final String lastName);

	public Response getUsersByTeamName(final String teamName);

	public Response getWorkItemsFromUser(final String userID);

	public Response getUsersWithWorkItem(final String workItem);

	public Response getAllUsers();
}

package se.leanbit.service.interfaces;

import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import javax.ws.rs.core.Response;

public interface SystemServiceInterface extends UserWebServiceInterface, TeamWebServiceInterface,
		WorkItemWebServiceInterface, IssueWebServiceInterface {
	Response getWorkItemsFromTeam(final Team team);

	Response addWorkItemToUser(final WorkItem workItem, final String userID);
}

package se.leanbit.ticketsystem.service.interfaces;

import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;

import java.util.List;

public interface TicketSystemServiceInterface
		extends UserServiceInterface, TeamServiceInterface, WorkItemServiceInterface, IssueServiceInterface {
	List<WorkItem> getWorkItemsFromTeam(final Team team);

	User addWorkItemToUser(final String userID, final WorkItem workItem);
}

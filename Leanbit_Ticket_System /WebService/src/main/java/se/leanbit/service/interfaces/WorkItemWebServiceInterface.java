package se.leanbit.service.interfaces;

import se.leanbit.ticketsystem.model.WorkItem;

import javax.ws.rs.core.Response;
import java.util.List;

public interface WorkItemWebServiceInterface {
	public Response addWorkItem(final WorkItem workItem);

	public Response getWorkItem(final String name);

	public Response updateWorkItem(final WorkItem workItem);

	public Response removeWorkItem(final String workItem);

	public Response getAllWorkItems();

	public Response getWorkItemsWithIssue();

	public Response getAllWorkItemsWithStatus(final String status);

	public Response getWorkItemWithDescriptionLike(final String description);

	public Response changeWorkItemStatus(final String workItem, final String status);
}

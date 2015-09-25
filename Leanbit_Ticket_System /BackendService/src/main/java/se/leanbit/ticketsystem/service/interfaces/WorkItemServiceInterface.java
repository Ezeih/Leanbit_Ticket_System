package se.leanbit.ticketsystem.service.interfaces;

import java.util.List;

import se.leanbit.ticketsystem.model.WorkItem;

public interface WorkItemServiceInterface {
	public WorkItem addWorkItem(final WorkItem workItem);

	public WorkItem getWorkItem(final String name);

	public WorkItem updateWorkItem(final WorkItem workItem);

	public void removeWorkItem(final WorkItem workItem);

	public List<WorkItem> getAllWorkItems();

	public List<WorkItem> getWorkItemsWithIssue();

	public List<WorkItem> getAllWorkItemsWithStatus(final String status);

	public List<WorkItem> getWorkItemWithDescriptionLike(final String description);

	public WorkItem changeWorkItemStatus(WorkItem workItem, final String status);
}

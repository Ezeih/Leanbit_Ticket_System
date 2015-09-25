package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.exception.WorkItemServiceException;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.repository.WorkItemRepository;
import se.leanbit.ticketsystem.service.interfaces.WorkItemServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class WorkItemService implements WorkItemServiceInterface {
	@Autowired
	private WorkItemRepository workItemRepository;

	public WorkItem addWorkItem(final WorkItem workItem) {
		try {
			return workItemRepository.save(workItem);
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not addWorkItem!", exception);
		}

	}

	public WorkItem getWorkItem(final String name) {
		try {
			return workItemRepository.getWorkItem(name);
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not getWorkItem!", exception);
		}
	}

	public WorkItem updateWorkItem(final WorkItem workItem) {
		try {
			return workItemRepository.save(workItem);
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not updateWorkItem!", exception);
		}
	}

	public void removeWorkItem(final WorkItem workItem) {
		try {
			workItemRepository.removeWorkItem(workItem.getName());
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not removeWorkItem!", exception);
		}
	}

	public List<WorkItem> getAllWorkItems() {
		try {
			return (List<WorkItem>) workItemRepository.findAll();
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not getAllWorkItems!", exception);
		}
	}

	public List<WorkItem> getWorkItemsWithIssue() {
		try {
			List<WorkItem> allWorkItems = getAllWorkItems();
			List<WorkItem> workItemsWithIssue = new ArrayList<>();

			for (WorkItem currentWorkItem : allWorkItems) {
				if (currentWorkItem.hasIssue()) {
					workItemsWithIssue.add(currentWorkItem);
				}
			}
			return workItemsWithIssue;
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not getWorkItemsWithIssue!", exception);
		}

	}

	public List<WorkItem> getAllWorkItemsWithStatus(final String status) {
		try {
			List<WorkItem> allWorkItems = getAllWorkItems();
			List<WorkItem> workItemsWithStatus = new ArrayList<>();

			for (WorkItem currentWorkItem : allWorkItems) {
				if (currentWorkItem.getStatus().equals(status)) {
					workItemsWithStatus.add(currentWorkItem);
				}
			}
			return workItemsWithStatus;
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not getAllWorkItemsWithStatus!", exception);
		}
	}

	public List<WorkItem> getWorkItemWithDescriptionLike(final String description) {
		try {
			return workItemRepository.getWorkItemWithDescriptionLike(description);
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not getWorkItemsWithDescriptionLike!",
					exception);
		}
	}

	public WorkItem changeWorkItemStatus(WorkItem workItem, final String status) {
		try {
			workItem.setStatus(status);
			return updateWorkItem(workItem);
		} catch (final Exception exception) {
			throw new WorkItemServiceException("WorkItemService: Could not changeWorkItemStatus!", exception);
		}
	}

}

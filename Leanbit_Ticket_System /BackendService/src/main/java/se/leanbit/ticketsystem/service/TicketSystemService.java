package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.exception.*;
import se.leanbit.ticketsystem.model.Issue;
import se.leanbit.ticketsystem.model.Team;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.service.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class TicketSystemService implements TicketSystemServiceInterface {
	@Autowired
	UserService userService;
	@Autowired
	WorkItemService workItemService;
	@Autowired
	TeamService teamService;
	@Autowired
	IssueService issueService;

	public TicketSystemService() {
	}

	// User service methods
	@Override
	public User addUser(final User user) {
		if (null != user) {
			Team team = null;
			try {
				team = teamService.getTeam(user.getTeam().getTeamName());
			} catch (final UserServiceException exception) {
			}

			try {
				if (null == team) {
					team = addTeam(user.getTeam());
				}
				user.setTeam(team);
			} catch (final UserServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Could not addUser!", exception);
			}

			try {
				userService.addUser(user);
			} catch (final UserServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Could not addUser!", exception);
			}

			// Returning the addUser object ignores all DB relations??
			return getUserWithID(user.getUserID());
		} else {
			throw new TicketSystemServiceException("TicketSystemService: addUser: Cannot add an empty user!");
		}
	}

	@Override
	public User getUserWithID(final String userID) {
		if (!userID.isEmpty()) {
			try {
				return userService.getUserWithID(userID);
			} catch (final UserServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Cannot getUserWithID!", exception);
			}
		} else {
			throw new TicketSystemServiceException("TicketSystemService: Cannot get userWithID: empty string!");
		}
	}

	@Override
	public User updateUser(final User user) {
		if (null != user) {
			try {
				User newUser = getUserWithID(user.getUserID());
				newUser.setUserName(user.getUserName());
				newUser.setFirstName(user.getFirstName());
				newUser.setLastName(user.getLastName());
				newUser.setPassword(user.getPassword());
				if (null != user.getTeam()) {
					Team team = null;
					try {
						team = getTeam(user.getTeam().getTeamName());
					} catch (TeamServiceException e) {
					}

					if (null != team) {
						newUser.setTeam(team);
					} else {
						team = addTeam(user.getTeam());
						newUser.setTeam(team);
					}

				}
				return userService.updateUser(newUser);
			} catch (final UserServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Cannot updateUser!", exception);
			}
		} else {
			throw new TicketSystemServiceException("TicketSystemService: Cannot update a user with an empty object!");
		}
	}

	@Override
	public void removeUser(final String userID) {
		if (!userID.isEmpty()) {
			try {
				userService.removeUser(userID);
			} catch (final UserServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Cannot removeUser!", exception);
			}
		} else {
			throw new TicketSystemServiceException("TicketSystemService: Cannot remove user: Remove what?");
		}
	}

	@Override
	public User getUserWithUserName(final String userName) {
		if (!userName.isEmpty()) {
			try {
				return userService.getUserWithUserName(userName);
			} catch (final UserServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Cannot getUsersWithUserName!", exception);
			}
		} else {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getUserWithUserName: empty input!");
		}
	}

	@Override
	public List<User> getUsersWithFirstName(final String firstName) {
		try {
			return userService.getUsersWithFirstName(firstName);
		} catch (final UserServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getUsersWithFirstName!", exception);
		}
	}

	@Override
	public List<User> getUsersWithLastName(final String lastName) {
		try {
			return userService.getUsersWithLastName(lastName);
		} catch (final UserServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getUsersWithLastName!", exception);
		}
	}

	@Override
	public List<User> getUsersByTeamName(final String teamName) {
		try {
			return userService.getUsersByTeamName(teamName);
		} catch (final UserServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getUsersByTeamName!", exception);
		}
	}

	@Override
	public List<User> getAllUsers() {
		try {
			return userService.getAllUsers();
		} catch (final UserServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getAllUsers!", exception);
		}
	}

	@Override
	public List<User> getUsersWithWorkItem(WorkItem workItem) {
		try {
			return userService.getUsersWithWorkItem(workItem);
		} catch (final UserServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getUsersWithWorkItem!", exception);
		}
	}

	// Team service methods
	@Override
	public Team addTeam(final Team team) {
		if (null != team) {
			try {
				teamService.addTeam(team);
			} catch (final TeamServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Cannot addTeam!", exception);
			}

			return teamService.getTeam(team.getTeamName());
		} else {
			throw new TicketSystemServiceException("TicketSystemService: Cannot add Team: Cannot add an empty object!");
		}
	}

	@Override
	public Team getTeam(final String teamName) {
		if (!teamName.isEmpty()) {
			try {
				return teamService.getTeam(teamName);
			} catch (final TeamServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Cannot getTeam!", exception);
			}
		} else {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getTeam: Cannot get an empty object!");
		}
	}

	@Override
	public void removeTeam(final String teamName) {
		if (!teamName.isEmpty()) {
			try {
				List<User> teamMembers = userService.getUsersByTeamName(teamName);
				for (User user : teamMembers) {
					user.setTeam(null);
					userService.updateUser(user);
				}
				teamService.removeTeam(teamName);
			} catch (final TeamServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Cannot addTeam!", exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot remove Team: Cannot remove with an empty object!");
		}
	}

	@Override
	public List<Team> getAllTeams() {
		try {
			return teamService.getAllTeams();
		} catch (final TeamServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getAllTeams!", exception);
		}
	}

	@Override
	public List<User> getAllUsersFromTeam(final String teamName) {
		try {
			return teamService.getAllUsersFromTeam(teamName);
		} catch (final TeamServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Cannot getAllUsersFromTeam!", exception);
		}
	}

	// WorkItems service methods
	@Override
	public WorkItem addWorkItem(final WorkItem workItem) {
		if (null != workItem) {
			try {
				workItemService.addWorkItem(workItem);
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: cannot addWorkItem!", exception);
			}

			return workItemService.getWorkItem(workItem.getName());
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot add WorkItem: Cannot add an empty object!");
		}
	}

	@Override
	public WorkItem getWorkItem(final String workItemName) {
		if (!workItemName.isEmpty()) {
			try {
				return workItemService.getWorkItem(workItemName);
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: cannot getWorkItem!", exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot get WorkItem: Cannot get with an empty object!");
		}
	}

	@Override
	public WorkItem updateWorkItem(final WorkItem workItem) {
		if (null != workItem) {
			try {
				WorkItem updateWorkItem = workItemService.getWorkItem(workItem.getName());
				updateWorkItem.setDescription(workItem.getDescription());
				updateWorkItem.setIssue(workItem.getIssue());
				updateWorkItem.setName(workItem.getName());
				updateWorkItem.setPriority(workItem.getPriority());
				updateWorkItem.setStatus(workItem.getStatus());
				return workItemService.updateWorkItem(updateWorkItem);

			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: cannot updateWorkItem!", exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot update workItem: Cannot update WorkItem with an empty object!");
		}
	}

	@Override
	public void removeWorkItem(final WorkItem workItem) {
		if (null != workItem) {
			try {
				workItemService.getWorkItem(workItem.getName());
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: cannot removeWorkItem!", exception);
			}

			try {
				List<User> workUsers = userService.getUsersWithWorkItem(workItem);
				for (User user : workUsers) {
					user.removeWorkItem(workItem);
					userService.updateUser(user);
				}

				workItemService.removeWorkItem(workItem);
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: cannot removeWorkItem!", exception);
			}

		}
	}

	@Override
	public List<WorkItem> getWorkItemsFromTeam(final Team team) {
		if (null != team) {
			try {
				getTeam(team.getTeamName());
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: cannot getWorkItemsFromTeam!", exception);
			}

			try {
				List<User> users = getUsersByTeamName(team.getTeamName());
				List<WorkItem> workItems = new ArrayList<>();

				for (User currentUser : users) {
					workItems.addAll(currentUser.getAllWorkItems());
				}
				return workItems;
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: cannot getWorkItemsFromTeam!", exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"Cannot getWorkItems from team: Cannot get WorkItems from an empty Team!");
		}

	}

	@Override
	public User addWorkItemToUser(final String userID, final WorkItem workItem) {
		User user;
		if (null != userID) {
			if (null != workItem) {
				try {
					user = getUserWithID(userID);
					WorkItem newWorkItem = getWorkItem(workItem.getName());
					if (null == newWorkItem) {
						workItemService.addWorkItem(workItem);
						user.addWorkItem(workItem);
					} else {
						user.addWorkItem(newWorkItem);
					}
				} catch (final WorkItemServiceException exception) {
					throw new TicketSystemServiceException("TicketSystemService: addWorkItemToUser!", exception);
				}

				try {
					return userService.updateUser(user);
				} catch (final WorkItemServiceException exception) {
					throw new TicketSystemServiceException("TicketSystemService: addWorkItemToUser!", exception);
				}
			} else {
				throw new TicketSystemServiceException(
						"TicketSystemService: Cannot addWorkItemToUser: Cannot add an empty WorkItem!");
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot addWorkItemToUser: The given User object is empty!");
		}
	}

	@Override
	public List<WorkItem> getAllWorkItems() {
		try {
			return workItemService.getAllWorkItems();
		} catch (final WorkItemServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Could not getAllWorkItems!", exception);
		}

	}

	@Override
	public List<WorkItem> getWorkItemsWithIssue() {
		try {
			return workItemService.getWorkItemsWithIssue();
		} catch (final WorkItemServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Could not getWorkItemsWithIssue!", exception);
		}

	}

	@Override
	public List<WorkItem> getAllWorkItemsWithStatus(final String status) {
		if (!status.isEmpty()) {
			try {
				return workItemService.getAllWorkItemsWithStatus(status);
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Could not getAllWorkItemsWithStatus!",
						exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot getAllWorkItemsWithStatus: The given User object is empty!");
		}

	}

	@Override
	public List<WorkItem> getWorkItemWithDescriptionLike(final String description) {
		if (!description.isEmpty()) {
			try {
				return workItemService.getWorkItemWithDescriptionLike(description);
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Could not getWorkItemWithDescriptionLike!",
						exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Can't get workItem: can't get workItem with empty description");
		}
	}

	@Override
	public WorkItem changeWorkItemStatus(final WorkItem workItem, final String status) {
		if (null != workItem) {
			if (!status.isEmpty()) {
				try {
					return workItemService.changeWorkItemStatus(workItem, status);
				} catch (final WorkItemServiceException exception) {
					throw new TicketSystemServiceException("TicketSystemService: Could not changeWorkItemStatus!",
							exception);
				}
			} else {
				throw new TicketSystemServiceException(
						"TicketSystemService: Cannot changeWorkItemStatus: The given status object is empty!");
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot changeWorkItemStatus: Empty workItem given!");
		}

	}

	@Override
	public List<WorkItem> getWorkItemsFromUser(String userID) {
		if (null != userID) {
			try {
				return userService.getWorkItemsFromUser(userID);
			} catch (final WorkItemServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Could not getWorkItemsFromUser!",
						exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot getWorkItemsFromUser: cannot get WorkItems with an empty string!");
		}
	}

	// Issue service methods
	@Override
	public Issue addIssue(final Issue issue) {
		if (null != issue) {
			try {
				issueService.addIssue(issue);
			} catch (final IssueServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Could not addIssue!", exception);
			}

			return issueService.getIssue(issue.getId());
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot add issue: Cannot add issue with an empty object!");
		}
	}

	@Override
	public Issue getIssue(long id) {
		try {
			return issueService.getIssue(id);
		} catch (final IssueServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Could not getIssue!", exception);
		}
	}

	@Override
	public Issue updateIssue(Issue issue, Long issueID) {
		if (null != issue) {
			try {
				Issue updateIssue = issueService.getIssue(issueID);
				updateIssue.setDescription(issue.getDescription());
				updateIssue.setName(issue.getName());
				updateIssue.setPriority(issue.getPriority());
				return issueService.updateIssue(updateIssue);
			} catch (final IssueServiceException exception) {
				throw new TicketSystemServiceException("TicketSystemService: Could not updateIssue!", exception);
			}
		} else {
			throw new TicketSystemServiceException(
					"TicketSystemService: Cannot update Issue: Empty Issue object given!");
		}
	}

	@Override
	public void removeIssue(final long id) {
		try {
			issueService.removeIssue(id);
		} catch (final IssueServiceException exception) {
			throw new TicketSystemServiceException("TicketSystemService: Could not removeIssue!", exception);
		}
	}
}
package se.leanbit.ticketsystem.service.interfaces;

import java.util.List;

import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;

public interface UserServiceInterface {
	public User addUser(final User user);

	public User updateUser(final User user);

	public void removeUser(final String userID);

	public User getUserWithID(final String userID);

	public User getUserWithUserName(final String userName);

	public List<User> getUsersWithFirstName(final String firstName);

	public List<User> getUsersWithLastName(final String lastName);

	public List<User> getUsersByTeamName(final String teamName);

	public List<WorkItem> getWorkItemsFromUser(final String userID);

	public List<User> getUsersWithWorkItem(final WorkItem workItem);

	public List<User> getAllUsers();
}

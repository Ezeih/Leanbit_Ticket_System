package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.exception.UserServiceException;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;
import se.leanbit.ticketsystem.repository.UserRepository;
import se.leanbit.ticketsystem.service.interfaces.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {
	@Autowired
	private UserRepository userRepository;

	public User addUser(final User user) {
		try {
			return userRepository.save(user);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not add user!", exception);
		}
	}

	public User updateUser(final User user) {
		try {
			return userRepository.save(user);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not update user!", exception);
		}

	}

	public void removeUser(final String userID) {
		try {
			userRepository.remove(userID);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not removeUser!", exception);
		}

	}

	public User getUserWithID(final String userID) {
		try {
			return userRepository.getUser(userID);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getUserWithID!", exception);
		}
	}

	public User getUserWithUserName(final String userName) {
		try {
			return userRepository.getUserWithUsername(userName);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getUserWithUsername!", exception);
		}
	}

	public List<User> getUsersWithFirstName(final String firstName) {
		try {
			return userRepository.getUsersWithFirstName(firstName);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getUsersWithFirstName!", exception);
		}
	}

	public List<User> getUsersWithLastName(final String lastName) {
		try {
			return userRepository.getUsersWithLastName(lastName);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getUsersWithLastName!", exception);
		}
	}

	public List<User> getUsersByTeamName(final String teamName) {
		try {
			return userRepository.getUsersByTeamName(teamName);
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getUsersByTeamName!", exception);
		}
	}

	public List<WorkItem> getWorkItemsFromUser(final String userID) {
		try {
			return getUserWithID(userID).getAllWorkItems();
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getWorkItemsFromUser!", exception);
		}

	}

	public List<User> getUsersWithWorkItem(final WorkItem workItem) {
		try {
			List<User> users = getAllUsers();
			List<User> usersWithItem = new ArrayList<>();

			for (User user : users) {
				for (WorkItem currentWorkItem : user.getAllWorkItems()) {
					if (currentWorkItem.getName().equals(workItem.getName())) {
						usersWithItem.add(user);
					}
				}
			}
			return usersWithItem;
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getUsersWithWorkItem!", exception);
		}
	}

	public List<User> getAllUsers() {
		try {
			return (List<User>) userRepository.findAll();
		} catch (final Exception exception) {
			throw new UserServiceException("UserService: Could not getAllUsers!", exception);
		}
	}
}

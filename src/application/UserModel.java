package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModel {

	private static UserModel _modelInstance;

	/**
	 * A map that has players' names as keys, and lists of result pairs as values.
	 */
	private static Map<String, User> _users;

	/**
	 * Constructor
	 */
	private UserModel() {
		_users = new HashMap<String, User>();
		File folder = new File("usrRecords");

		// generate new User Objects for the local files existed
		File[] fileList = folder.listFiles();

		for (File file : fileList) {
			if (file.isFile()) {
				// read the user's name
				String fileName = file.getName();
				String userName = fileName.substring(0, fileName.lastIndexOf("."));
				_users.put(userName, new User(userName));
			}
		}
	}

	/**
	 * Get the instance of the singleton class QuestionModel
	 * 
	 * @return the only instance of QuestionModel
	 */
	public static UserModel getInstance() {
		if (_modelInstance == null) {
			_modelInstance = new UserModel();
		}
		return _modelInstance;
	}

	/**
	 * Add a record to the player's personal history and update both the personal
	 * history and global history.
	 * 
	 * @param userName
	 * @param gameMode
	 *            (either NORMALMATH or ENDLESSMATH)
	 * @param score
	 */
	public void appendRecord(String userName, Mode gameMode, int score) {
		if (!_users.containsKey(userName)) {
			User user = new User(userName);
			user.appendNewRecord(gameMode, score);
			_users.put(userName, user);
		} else {
			_users.get(userName).appendNewRecord(gameMode, score);
		}

	}

	public String getLatestGameMode(String userName) {
		User user = _users.get(userName);
		if (user == null) {
			return "No Record";
		} else {
			return user.reportLatestGameMode();
		}
	}

	public String getLatestGameScore(String userName) {
		User user = _users.get(userName);
		if (user == null) {
			return "No Record";
		} else {
			return user.reportLatestGameScore();
		}
	}

	/**
	 * 
	 * @param gameMode
	 * @param userName
	 * @return the string representation of the rank of the user in the given game
	 *         mode
	 */
	public String getRank(Mode gameMode, String userName) {
		if (_users.get(userName) == null) {
			return "No Record";
		}

		// calculate rank of the user in the given mode
		int rank = calculateRank(gameMode, userName);

		return "" + rank;

	}

	/**
	 * 
	 * @param gameMode
	 * @param userName
	 * @return the rank of the user in the given mode
	 */
	private int calculateRank(Mode gameMode, String userName) {
		int rank;
		Integer personalBest, usrBest;
		switch (gameMode) {
		case NORMALMATH:
			personalBest = _users.get(userName).getPersonalBest(Mode.NORMALMATH);
			rank = 1;
			for (User usr : _users.values()) {
				usrBest = usr.getPersonalBest(Mode.NORMALMATH);
				if (usrBest != null && usrBest > personalBest) {
					rank++;
				}
			}
			return rank;
		case ENDLESSMATH:
			personalBest = _users.get(userName).getPersonalBest(Mode.ENDLESSMATH);
			rank = 1;
			for (User usr : _users.values()) {
				usrBest = usr.getPersonalBest(Mode.ENDLESSMATH);
				if (usrBest != null && usrBest > personalBest) {
					rank++;
				}
			}
			return rank;
		default:
			throw new RuntimeException("Mode can only be NORMALMATH or ENDLESSMATH");
		}
	}

	/**
	 * 
	 * @param gameMode
	 * @param userName
	 * @return the string representation of the highest score of the user in the
	 *         given game mode
	 */
	public String getPersonalBest(Mode gameMode, String userName) {
		if (_users.get(userName) == null) {
			return "No Record";
		}

		// calculate personal best
		int personalBest = calculatePersonalBest(gameMode, userName);

		return "" + personalBest;
	}

	/**
	 * 
	 * @param gameMode
	 * @param userName
	 * @return the highest score of the user in the given game mode
	 */
	private int calculatePersonalBest(Mode gameMode, String userName) {
		int personalBest;
		switch (gameMode) {
		case NORMALMATH:
			personalBest = _users.get(userName).getPersonalBest(Mode.NORMALMATH);
			return personalBest;
		case ENDLESSMATH:
			personalBest = _users.get(userName).getPersonalBest(Mode.ENDLESSMATH);
			return personalBest;
		default:
			throw new RuntimeException("Mode can only be NORMALMATH or ENDLESSMATH");
		}
	}

	/**
	 * 
	 * @param gameMode
	 * @param userName
	 * @return the personal history of the user in the given game mode
	 */
	public int[] getPersonalHistory(Mode gameMode, String userName) {
		if (_users.get(userName) == null) {
			return null;
		}

		switch (gameMode) {
		case NORMALMATH:
			return _users.get(userName).getHistory(Mode.NORMALMATH);
		case ENDLESSMATH:
			return _users.get(userName).getHistory(Mode.ENDLESSMATH);
		default:
			throw new RuntimeException("Mode can only be NORMALMATH or ENDLESSMATH");
		}

	}

	public List<UserDataTuple> getRankingList(Mode gameMode) {
		// TODO Auto-generated method stub
		List<UserDataTuple> ranking = new ArrayList<UserDataTuple>();

		for (User usr : _users.values()) {
			String name = usr.getName();
			int rank = calculateRank(gameMode, name);
			int highestScore = calculatePersonalBest(gameMode, name);
			ranking.add(new UserDataTuple(name, rank, highestScore));
		}

		// sort the ranking list
		Collections.sort(ranking);

		return ranking;
	}

}

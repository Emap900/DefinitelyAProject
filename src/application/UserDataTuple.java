package application;

public class UserDataTuple implements Comparable<UserDataTuple> {

	private final String _name;
	private final int _rank;
	private final int _highestScore;

	public UserDataTuple(String userName, int rank, int highestScore) {
		_name = userName;
		_rank = rank;
		_highestScore = highestScore;
	}

	/**
	 * 
	 * @return name of the user stored in this data tuple
	 */
	public String getName() {
		return _name;
	}

	/**
	 * 
	 * @return rank of the user stored in this data tuple
	 */
	public int getRank() {
		return _rank;
	}

	/**
	 * 
	 * @return highest score of the user stored in this data tuple
	 */
	public int getHighestScore() {
		return _highestScore;
	}

	/**
	 * Comparing uses ranks of the users, e.g. rank of 5 will appear later in the
	 * list than rank of 1
	 */
	@Override
	public int compareTo(UserDataTuple other) {
		if (_rank == other.getRank()) {
			return 0;
		} else {
			return (_rank > other.getRank() ? 1 : -1);
		}

	}

	/**
	 * Either two user data tuple is the same is decided by the name of the user
	 * stored in the data tuple
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof UserDataTuple) {
			String otherName = ((UserDataTuple) obj)._name;
			return this._name.equals(otherName);
		} else {
			return super.equals(obj);
		}
	}

}

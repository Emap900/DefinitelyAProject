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

	public String getName() {
		return _name;
	}

	public int getRank() {
		return _rank;
	}

	public int getHighestScore() {
		return _highestScore;
	}

	@Override
	public int compareTo(UserDataTuple other) {
		// TODO Auto-generated method stub
		if (_rank == other.getRank()) {
			return 0;
		} else {
			return (_rank > other.getRank() ? 1 : -1);
		}

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub

		if (obj instanceof UserDataTuple) {
			String otherName = ((UserDataTuple) obj)._name;
			return this._name.equals(otherName);
		} else {
			return super.equals(obj);
		}
	}

}

package application;

import java.util.Comparator;

public class UserDataTuple {

	private final String _name;
	private final int _nRank;
	private final int _nHighest;
	private final int _eRank;
	private final int _eHighest;

	public UserDataTuple(String userName, int normalModeRank, int normalModeHighestScore, int endlessModeRank,
			int endlessModeHighestScore) {
		_name = userName;
		_nRank = normalModeRank;
		_nHighest = normalModeHighestScore;
		_eRank = endlessModeRank;
		_eHighest = endlessModeHighestScore;
	}

	public String getName() {
		return _name;
	}

	public int getNormalModeRank() {
		return _nRank;
	}

	public int getNormalModeHighestScore() {
		return _nHighest;
	}

	public int getEndlessModeRank() {
		return _eRank;
	}

	public int getEndlessModeHighestScore() {
		return _eHighest;
	}

	public class NormalModeRankComparator implements Comparator<UserDataTuple> {

		@Override
		public int compare(UserDataTuple u1, UserDataTuple u2) {
			// TODO Auto-generated method stub
			int u1Rank = u1.getNormalModeRank();
			int u2Rank = u2.getNormalModeRank();
			if (u1Rank == u2Rank) {
				return 0;
			} else {
				return (u1Rank > u2Rank ? 1 : -1);
			}

		}

	}

	public class EndlessModeRankComparator implements Comparator<UserDataTuple> {

		@Override
		public int compare(UserDataTuple u1, UserDataTuple u2) {
			// TODO Auto-generated method stub
			int u1Rank = u1.getEndlessModeRank();
			int u2Rank = u2.getEndlessModeRank();
			if (u1Rank == u2Rank) {
				return 0;
			} else {
				return (u1Rank > u2Rank ? 1 : -1);
			}

		}

	}

}

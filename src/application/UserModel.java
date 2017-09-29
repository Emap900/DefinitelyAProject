package application;

public class UserModel {

	private static UserModel _modelInstance;

	/**
	 * Constructor
	 */
	private UserModel() {

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

}

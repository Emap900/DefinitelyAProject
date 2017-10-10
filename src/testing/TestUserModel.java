package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import application.Mode;
import application.UserModel;

public class TestUserModel {

	@Test
	public void test() {
		UserModel model = UserModel.getInstance();
		model.appendRecord("abc", Mode.ENDLESSMATH, 110);
	}

}

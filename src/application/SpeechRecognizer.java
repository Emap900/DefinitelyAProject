package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public final class SpeechRecognizer {

	/**
	 * The Maori number dictionary from 1 to 99
	 */
	private static final HashMap<String, String> _dictionary = loadDictionary();
	// /**
	// * The only database for the whole program
	// */
	// private static final Database _database = MainController.getDatabase();

	public static boolean checkCorrectness() {
		return false;
		// //String numberValue = _database.currentNumber();
		// String numberValue = "0";
		// String recognizedWord;
		// try {
		// recognizedWord = recognizeRecording();
		// String correctWord = _dictionary.get(numberValue);
		// System.out.println(correctWord);
		// System.out.println(recognizedWord);
		// if (recognizedWord.equals(correctWord)) {
		// _database.updateResult(recognizedWord, true);
		// } else {
		// _database.updateResult(recognizedWord, false);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * This method uses a shell script which uses htk toolbox to do speech
	 * recognition. It will load the wav recording saved locally and return the
	 * recognized word.
	 * 
	 * @return recognized word from the locally saved (currentNumberValue).wav as a
	 *         string
	 * @throws IOException
	 */
	private static String recognizeRecording() throws IOException {
		return null;
		//
		// new BashProcess("./MagicStaff.sh", "speechRecognize",
		// _database.currentNumber());
		//
		// List<String> htkResult = Files.readAllLines(Paths.get("recout.mlf"));
		//
		// String result = "";
		// for (String s : htkResult) {
		// System.out.println(s);
		// if (!s.startsWith("#") && !s.startsWith("\"") && !s.startsWith(".") &&
		// !s.equals("sil")) {
		// result = result + s + " ";
		// }
		// }
		//
		// // remove the last space
		// if (result.endsWith(" ")) {
		// result = result.substring(0, result.length() - 1);
		// }
		//
		// return result;
	}

	/**
	 * Load the a dictionary file into a HashMap
	 * 
	 * @return HashMap of the Maori number dictionary
	 */
	public static HashMap<String, String> loadDictionary() {
		HashMap<String, String> dictionary = new HashMap<String, String>();
		try {
			List<String> entryList = Files.readAllLines(Paths.get("maoriDictionary.txt"));
			for (String s : entryList) {
				String[] entry = s.split("\t");
				dictionary.put(entry[0], entry[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dictionary;
	}

}

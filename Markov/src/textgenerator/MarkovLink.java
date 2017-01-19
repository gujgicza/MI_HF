package textgenerator;

import java.util.ArrayList;

public class MarkovLink {
	// A single link in a markov chain
	String prefix1 = "";
	String prefix2 = "";
	ArrayList<String> suffixes = new ArrayList<String>();
	ArrayList<Integer> multiplicity = new ArrayList<Integer>();

	public MarkovLink(String p1, String p2) {
		prefix1 = p1;
		prefix2 = p2;
	}

	public boolean suffixExists(String suffix) {
		return suffixes.contains(suffix);
	}

	public void addSuffix(String suffix) {
		if (suffixExists(suffix)) {
			int index = suffixes.indexOf(suffix);
			multiplicity.set(index, (multiplicity.get(index) + 1));
		} else {
			suffixes.add(suffix);
			multiplicity.add(1);
		}
	}

	public String[] getChoiceArray() {
		ArrayList<String> choices = new ArrayList<String>();
		String[] suf = suffixes.toArray(new String[suffixes.size()]);
		// int[] mul = multiplicity.toArray(new int[suffixes.size()]);
		for (int i = 0; i < suf.length; i++) {
			int count = (int) (multiplicity.get(i));
			while (count != 0) {
				choices.add(suf[i]);
				count = count - 1;
			}
		}
		return choices.toArray(new String[choices.size()]);
	}

	public String toString() {
		String str = "\"" + prefix1 + " " + prefix2 + "\"" + " : [";
		for (int i = 0; i < suffixes.size(); i++) {
			str = str + multiplicity.get(i) + "-" + suffixes.get(i);
			if (i + 1 != suffixes.size()) {
				str = str + ", ";
			}
		}
		str = str + "]";
		return str;
	}
}
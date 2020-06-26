package game.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MadLib {
	
	private List<String> templates = new ArrayList<>();
	
	public MadLib() {}
	
	public MadLib(String... templates) {
		this.templates.addAll(Arrays.asList(templates));
	}
	
	public MadLib addTemplate(String... templates) {
		this.templates.addAll(Arrays.asList(templates));
		return this;
	}
	
	public String getRandomTemplate(String[]... substitutes) {
		int i = (int) (Math.random() * templates.size());
		String k = templates.get(i);
		for (String[] sub : substitutes)
			k = k.replace(sub[0], sub[1]);
		return k;
	}
}

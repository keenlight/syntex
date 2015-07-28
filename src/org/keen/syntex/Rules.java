package org.keen.syntex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {

	private Map<String, List<String>> nameMap = new HashMap<String, List<String>>();
	private Map<String, List<Rule>> map = new HashMap<String, List<Rule>>();

	/**
	 * param_list -> id param_list_tail 
	 * param_list_tail -> , id param_list_tail
	 * param_list_tail -> ;
	 */
	public Rules() {
		nameMap.put("param_list_tail", Arrays.asList("param_list_tail_1", "param_list_tail_2"));
		List<Rule> rule = new ArrayList<Rule>(2);
		rule.add(new Rule(true, "id", TokenType.symbol));
		rule.add(new Rule(false, "param_list_tail", null));
		map.put("param_list", rule);

		rule = new ArrayList<Rule>(3);
		rule.add(new Rule(true, ",", TokenType.comma));
		rule.add(new Rule(true, "id", TokenType.symbol));
		rule.add(new Rule(false, "param_list_tail", null));
		map.put("param_list_tail_1", rule);

		rule = new ArrayList<Rule>(1);
		rule.add(new Rule(true, ";", TokenType.semicolon));
		map.put("param_list_tail_2", rule);
	}

	public List<Rule> selectedRule(String ruleName, TokenType type) {
		List<String> nameList = nameMap.get(ruleName);
		if (nameList == null) {
			return map.get(ruleName);
		}

		for (String actualName : nameList) {
			List<Rule> list = map.get(actualName);
			if (list != null && list.size() > 0) {
				if (list.get(0).getType() == type) {
					return list;
				}
			}
		}
		return null;
	}
}

class Rule {
	private boolean leaf;
	private String ruleName;
	private TokenType type;

	public Rule(boolean leaf, String ruleName, TokenType type) {
		this.leaf = leaf;
		this.ruleName = ruleName;
		this.type = type;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public String getRuleName() {
		return ruleName;
	}

	public TokenType getType() {
		return type;
	}

}

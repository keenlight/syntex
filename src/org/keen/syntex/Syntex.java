package org.keen.syntex;

import java.util.Iterator;
import java.util.List;

/**
 * 递归下降语法分析器
 * @author keenlight
 *
 */
public class Syntex {

	public static void main(String[] args) {
		String code = "a , b , c ;";
		try {
			new Syntex().func(code);
		} catch (SyntexError e) {
			e.printStackTrace();
		}
	}
	
	public void func(String code) throws SyntexError{
		Iterator<Token> iter = new TokenIterator(code);
		if(iter.hasNext()){
			Rules rules = new Rules();
			List<Rule> selectedRules = rules.selectedRule("param_list", iter.next().getType());
			func1(selectedRules, rules, iter);
			System.out.println("pass");
		}
		
	}

	private void func1(List<Rule> selectedRules, Rules rules, Iterator<Token> iter) throws SyntexError {
		for(int i = 1, size = selectedRules.size(); i < size; ++i){
			Rule rule = selectedRules.get(i);
			if(iter.hasNext()){
				Token next = iter.next();
				if(!rule.isLeaf()){
					List<Rule> selectedRule = rules.selectedRule(rule.getRuleName(), next.getType());
					if(selectedRule == null){
						throw new SyntexError(rule.getRuleName() + " not expect " + next.getType());
					}
					func1(selectedRule, rules, iter);
				}else{
					if(next.getType() != rule.getType()){
						throw new SyntexError("expected <" + rule.getType() + "> but "+next.getCode()+" is " + next.getType());
					}
				}
			}else{
				throw new SyntexError("expected <" + rule.getType() + ">");
			}
		}
	}
}
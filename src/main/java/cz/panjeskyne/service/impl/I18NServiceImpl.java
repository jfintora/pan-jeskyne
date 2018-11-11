package cz.panjeskyne.service.impl;

import java.util.HashMap;

public class I18NServiceImpl {

	private static final HashMap<String,String> map = new HashMap<>();

	static {
		add("exception.table.not_found", "Tabulka s id: \"%s\" nenalezena");
		add("exception.table.invalid_args_count", "Tabulka \"%s\" o�ek�v� tento po�et argument�: %s obdr�ela v�ak %s");
		add("exception.formula.bracket_too_much_arguments", "V zavork�ch nesm� b�t znak ','");
		add("exception.formula.operator_invalid_count_operands", "Nen� zad�n spr�vn� po�et operand� pro oper�tor '%s', o�ek�v�no: %s obdr�eno: %s");
		add("exception.function.not_found", "Zadan� funkce neexistuje (nen� zn�m typ): %s");
		add("exception.class.not_found", "Zadan� t��da nenalezena: %s");
		add("exception.method.not_found", "Zadan� metoda nenalezena: %s");
		add("exception.method.not_visible", "Zadan� metoda nen� p��stupn�: %s");
		add("exception.function.format_exception", "Neodpov�d� standartn�mu form�tu: %s");
		add("exception.method.bad_arguments", "Chybn� doru�en� argumenty: o�ek�v�no %s"); 
		add("exception.method.bad_invocation", "Nelze volat metodu: %s");
	}
	
	public static String getString(String codename) {
		return map.get(codename);
	}

	private static void add(String codename, String value) {
		map.put(codename, value);
	}

}
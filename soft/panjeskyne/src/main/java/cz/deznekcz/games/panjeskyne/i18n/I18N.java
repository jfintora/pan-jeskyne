package cz.deznekcz.games.panjeskyne.i18n;

import java.util.Arrays;

import cz.deznekcz.games.panjeskyne.service.I18NService;

public enum I18N implements I18NA {
	TABLE_NOT_FOUND("exception.table.not_found"), 
	TABLE_INVALID_ARGS_COUNT("exception.table.invalid_args_count"), 
	BRACKET_INVALID("exception.formula.bracket_too_much_arguments"), 
	OPERATOR_INVALID("exception.formula.operator_invalid_count_operands"), 
	FUNCTION_NOT_FOUND("exception.function.not_found"), 
	CLASS_NOT_FOUND("exception.class.not_found"), 
	METHOD_NOT_FOUND("exception.method.not_found"), 
	METHOD_NOT_VISIBLE("exception.method.not_visible"),
	NOT_MATCH_FORMAT("exception.function.format_exception"),
	METHOD_BAD_ARGUMENTS("exception.method.bad_arguments"), 
	METHOD_NOT_INVOCATED("exception.method.bad_invocation"),
	MISSING_OPENING_BRACKET("exception.formula.missing_opening_bracket"),
	CHILDREN_NOT_IMPLEMENTED("exception.formula.child_elements_not_implemented"),
	TOO_MUCH_OPERANDS("exception.formula.too_much_operands"),
	BRACKET_IS_CLOSED("exception.formula.bracket_is_closed"),
	HAS_PARENT_ELEMENT("exception.formula.has_no_parent"),
	DATA_NOT_FOUND("exception.data.not_found"),
	INVALID_ARGS_COUNT("exception.function.invalid_count_arguments"),
	
	_VOID("void"), 
	RULES_TAB("tabs.rules"), 
	;
	
	public static String LOCALE = "cs_CZ";
	
	private String codename;

	private I18N(String codename) {
		this.codename = codename;
	}
	
	public String getString(Object...args) {
		return String.format(I18NService.getString(LOCALE, codename), args);
	}

	/**
	 * Returns identifier like it is
	 * @param identifier
	 * @return
	 */
	public static I18NA id(CharSequence identifier) {
		return (args) -> identifier.toString();
	}

	/**
	 * Returns identifier like it is
	 * @param identifier
	 * @return
	 */
	public static I18NA id(char identifier) {
		return id(String.valueOf(identifier));
	}

	/**
	 * Returns number value
	 * @param identifier
	 * @return
	 */
	public static I18NA number(Number number) {
		return (NOARGS) -> String.valueOf(number);
	}
	
	public static I18NA argumented(I18NA origin, I18NA...args) {
		return (NOARGS) -> origin.getString(Arrays.asList(args).stream().map(I18NA::getString).toArray(Object[]::new));
	}

	public static I18NA string(String codename) {
		return (NOARGS) -> I18NService.getString(LOCALE, codename);
	}
}

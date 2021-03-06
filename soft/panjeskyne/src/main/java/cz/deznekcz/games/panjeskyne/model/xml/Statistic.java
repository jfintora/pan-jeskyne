package cz.deznekcz.games.panjeskyne.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import cz.deznekcz.util.xml.XMLElement;
import cz.deznekcz.util.xml.XMLPairTag;
import cz.deznekcz.util.xml.XMLRoot;
import cz.deznekcz.util.xml.XMLSingleTag;

@XmlRootElement(name="statistic")
@XmlAccessorType(XmlAccessType.NONE)
public class Statistic implements XmlSerialized {

	private static final long serialVersionUID = 8795269493281299844L;

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "groups", required = false)
	private String groups;

	@XmlAttribute(name = "formula", required = false)
	private String formula;

	@XmlAttribute(name = "desc", required = false)
	private String description;
	
	@XmlAttribute(name = "characterData", required = false)
	private boolean characterData;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodename() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setCodename(String codename) {
		this.id = codename;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroups() {
		return groups;
	}
	
	public void setGroups(String groups) {
		this.groups = groups;
	}
	
	public boolean hasGroups() {
		return getGroups() != null && getGroups().length() > 0;
	}
	
	public boolean hasFormula() {
		return getFormula() != null && getFormula().length() > 0;
	}

	public boolean isVoid() {
		return "void".equals(id);
	}
	
	public boolean isCharacterData() {
		return characterData;
	}
	
	@Override
	public String toString() {
		return id + (hasFormula() ? ("(" + formula + ")") : "");
	}

	public void write(XMLPairTag<?> stat) {
		stat.addAttribute("id", id);
		stat.addAttribute("name", name);
		stat.addAttribute("desc", description);
		if (hasFormula())
			stat.addAttribute("formula", formula);
	}
}

package cz.panjeskyne.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class LevelStatisticBonus implements XmlMappable<String, LevelStatisticBonus> {

	@XmlAttribute(name= "ref")
	private String statisticCodename;

	@XmlTransient
	private Kind kind;

	@XmlAttribute(name= "increase")
	private int bonusValue;

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public int getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(int value) {
		this.bonusValue = value;
	}

	@Override
	public String getKey() {
		return statisticCodename;
	}

	@Override
	public LevelStatisticBonus getValue() {
		return this;
	}

}

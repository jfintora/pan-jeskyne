package cz.deznekcz.games.panjeskyne.model.xml.skill;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cz.deznekcz.games.panjeskyne.model.xml.ListType;

@XmlRootElement(name="levels")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillLevels implements ListType<SkillLevel> {

	@XmlElement(name="level")
	private List<SkillLevel> levels;
	
	@Override
	public List<SkillLevel> getList() {
		return levels;
	}

	public List<SkillLevel> getLevels() {
		return levels;
	}
	
	@Override
	public void setList(List<SkillLevel> list) {
		this.levels = list;
	}
}

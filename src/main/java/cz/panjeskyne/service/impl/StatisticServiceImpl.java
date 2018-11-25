package cz.panjeskyne.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.ImmutableMap;

import cz.panjeskyne.model.db.Character;
import cz.panjeskyne.model.xml.Statistic;
import cz.panjeskyne.model.xml.Statistics;
import cz.panjeskyne.service.KindService;
import cz.panjeskyne.service.Result;
import cz.panjeskyne.service.StatisticService;
import cz.panjeskyne.service.formula.Formula;

@Service
public class StatisticServiceImpl implements StatisticService {

	private static final String STATS_XML = "classpath:moduleData/stats.xml";

	private ImmutableMap<String, Statistic> statistics;
	
	private ImmutableMap<String, List<Statistic>> dependents;

	@Autowired
	private KindService kindService;

	@PostConstruct
	private void init() {
		try {
			File file = ResourceUtils.getFile(STATS_XML);
			JAXBContext jc = JAXBContext.newInstance(Statistics.class);
            Unmarshaller u = jc.createUnmarshaller();
            
			Statistics stats = (Statistics)u.unmarshal(file);
			ImmutableMap.Builder<String, Statistic> builder = ImmutableMap.builder();
			for(Statistic stat : stats.getStatistics()) {
				builder.put(stat.getCodename(), stat);
			}
			statistics = builder.build();
		} catch (JAXBException | IOException e) {
			throw new IllegalStateException("Statistics could not be loaded from the XML.", e);
		}
		
		ImmutableMap.Builder<String, List<Statistic>> builderDep = ImmutableMap.builder();
		for(Statistic statistic : statistics.values()) {
			List<Statistic> dependents = new ArrayList<>();
			for(Statistic dependent : statistics.values()) {
				String forumula = dependent.getFormula();
				if(StringUtils.containsIgnoreCase(forumula, statistic.getCodename())) {
					dependents.add(dependent);
				}
			}
			builderDep.put(statistic.getCodename(), dependents);
		}
		dependents = builderDep.build();
	}
	
	public KindService getKindService() {
		return kindService;
	}

	@Override
	public Statistic getByCodename(String codename) {
		return statistics.get(codename);
	}

	@Override
	public List<Statistic> getDependentStatistics(String codename) {
		return dependents.get(codename);
	}
	
	@Override
	public Collection<Statistic> getAll() {
		return statistics.values();
	}

	@Override
	public Result getValue(Character character, Statistic statistic) {
		Result result = new Result(); 
		
		if (statistic.hasFormula()) {
			Result inMiddle = parseFormula(statistic);
			if (!inMiddle.isSuccessful())
				return inMiddle;
			
			inMiddle.applyFormula(this, character);
			result.setValue(inMiddle.getValue());
			result.setException(inMiddle.getException());
		}
		
		if (result.isSuccessful()) {
			result.increase(kindService.getCharactersKind(character).getStatisticBonus(statistic.getCodename()));
		}
		
		return result;
	}

	@Override
	public Result validateFormula(String formula) {
		Statistic statistic = new Statistic();
		statistic.setFormula(formula);
		return parseFormula(statistic);
	}

	private Result parseFormula(Statistic statistic) {
		return Formula.parse(this, statistic);
	}

	@Override
	public Result getFormulaValue(Character character, String formula) {
		Statistic statistic = new Statistic();
		statistic.setId("void");
		statistic.setFormula(formula);
		
		return getValue(character, statistic);
	}
}

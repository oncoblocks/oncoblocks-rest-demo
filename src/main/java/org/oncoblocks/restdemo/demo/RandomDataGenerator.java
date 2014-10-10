package org.oncoblocks.restdemo.demo;

import org.oncoblocks.restdemo.models.EntrezGene;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

/**
 * Created by woemler on 9/23/14.
 */
public class RandomDataGenerator {
	
	public static EntrezGene randomEntrezGene(){
		EntrezGene entrezGene = new EntrezGene();
		Random random = new Random();

		entrezGene.setEntrezGeneId(random.nextInt(100000));

		entrezGene.setTaxId(9606);

		int num = random.nextInt(24)+1;
		if (num == 23){
			entrezGene.setChromosome("X");
		} else if (num == 24){
			entrezGene.setChromosome("Y");
		} else {
			entrezGene.setChromosome(String.valueOf(num));
		}

		entrezGene.setLocusTag("-");

		num = random.nextInt(2);
		entrezGene.setChromosomeLocation(entrezGene.getChromosome()
				+ new String("pq").substring(num, num+1)
				+ String.valueOf(random.nextInt(28)+11));

		entrezGene.setPrimaryGeneSymbol(RandomStringUtils.random(random.nextInt(5) + 4, true, true).toUpperCase());

		entrezGene.setGeneType("protein-coding");

		Map<String,Object> refMap = new HashMap<>();
		refMap.put("HGNC", String.valueOf(random.nextInt(10000)));
		refMap.put("MIM", String.valueOf(random.nextInt(1000000)));
		refMap.put("Ensembl", "ENSG" + String.valueOf(random.nextInt(1000000000)));
		refMap.put("Vega", "OTTHUMG" + String.valueOf(random.nextInt(1000000000)));
		entrezGene.setDatabaseCrossReferences(refMap);

		Set<String> aliases = new HashSet<>();
		for (int i = 0; i < random.nextInt(4)+2; i++){
			aliases.add(RandomStringUtils.random(random.nextInt(5)+4, true, true).toUpperCase());
		}
		entrezGene.setGeneSymbolAliases(aliases);

		num = random.nextInt(2);
		entrezGene.setIsKinase(new String("YN").substring(num, num+1));
		num = random.nextInt(2);
		entrezGene.setIsCgcGene(new String("YN").substring(num, num+1));

		return entrezGene;
	}
	
}

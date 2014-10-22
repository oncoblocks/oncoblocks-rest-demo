package org.oncoblocks.restdemo.dao;

import org.oncoblocks.restdemo.models.EntrezGene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by woemler on 9/26/14.
 */

@Repository
public class EntrezGeneDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Returns a list of all EntrezGene records.
	 * @return
	 */
	public List<EntrezGene> findAllEntrezGenes(Integer limit, Integer offset){
		return jdbcTemplate.query("SELECT * FROM `ENTREZ_GENE`", new EntrezGeneRowMapper());
	}

	/**
	 * Fetches a single EntrezGene record by its primary key (entrez gene ID)
	 * @param id
	 * @return
	 */
	public EntrezGene findEntrezGeneById(Integer id){
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM `ENTREZ_GENE` WHERE ENTREZ_GENE_ID = ?", 
					new Object[]{id}, 
					new EntrezGeneRowMapper());
		} catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	// Find by attributes

	/**
	 * Fetches all EntrezGene records with the corresponding primary gene symbol
	 * @param geneSymbol
	 * @return
	 */
	public List<EntrezGene> findEntrezGenesByGeneSymbol(String geneSymbol, Integer limit, Integer offset){
		return jdbcTemplate.query(
				"SELECT * FROM `ENTREZ_GENE` WHERE PRIMARY_GENE_SYMBOL = ?", 
				new Object[]{geneSymbol},
				new EntrezGeneRowMapper()
		);
	}


	/**
	 * Adds a new EntrezGene record and returns the primary key
	 * @param entrezGene
	 * @return
	 */
	public Integer addEntrezGene(final EntrezGene entrezGene){
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws
						SQLException {
					PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO `ENTREZ_GENE` (ENTREZ_GENE_ID, TAX_ID, LOCUS_TAG, PRIMARY_GENE_SYMBOL," +
									"CHROMOSOME, CHROMOSOME_LOCATION, DESCRIPTION, GENE_TYPE, DB_XREFS, IS_CGC_GENE, IS_KINASE) " +
									"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
					);
					ps.setInt(1, entrezGene.getEntrezGeneId());
					ps.setInt(2, entrezGene.getTaxId());
					ps.setString(3, entrezGene.getLocusTag());
					ps.setString(4, entrezGene.getPrimaryGeneSymbol());
					ps.setString(5, entrezGene.getChromosome());
					ps.setString(6, entrezGene.getChromosomeLocation());
					ps.setString(7, entrezGene.getDescription());
					ps.setString(8, entrezGene.getGeneType());
				/*ps.setString(9, entrezGene.getDatabaseCrossReferences());
				ps.setString(10, entrezGene.getIsCgcGene());
				ps.setString(11, entrezGene.getIsKinase());*/
					return ps;
				}
			
			}
		);
		
		return entrezGene.getEntrezGeneId();
	}

	/**
	 * Updates an existing EntrezGene record, referenced by primary key ID (entrez gene ID)
	 * @param entrezGene
	 * @return
	 */
	public Integer updateEntrezGene(final EntrezGene entrezGene){
		return jdbcTemplate.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws
							SQLException {
						PreparedStatement ps = connection.prepareStatement(
								"UPDATE `ENTREZ_GENE` SET ENTREZ_GENE_ID = ?, TAX_ID = ?, LOCUS_TAG = ?, " +
										" PRIMARY_GENE_SYMBOL = ?, CHROMOSOME = ?, CHROMOSOME_LOCATION = ?, " +
										" DESCRIPTION = ?, GENE_TYPE = ?, DB_XREFS = ?, IS_CGC_GENE = ?, IS_KINASE = ? " +
										" WHERE ENTREZ_GENE_ID = ? "
						);
						ps.setInt(1, entrezGene.getEntrezGeneId());
						ps.setInt(2, entrezGene.getTaxId());
						ps.setString(3, entrezGene.getLocusTag());
						ps.setString(4, entrezGene.getPrimaryGeneSymbol());
						ps.setString(5, entrezGene.getChromosome());
						ps.setString(6, entrezGene.getChromosomeLocation());
						ps.setString(7, entrezGene.getDescription());
						ps.setString(8, entrezGene.getGeneType());
					/*ps.setString(9, entrezGene.getDatabaseCrossReferences());
					ps.setString(10, entrezGene.getIsCgcGene());
					ps.setString(11, entrezGene.getIsKinase());*/
						ps.setInt(12, entrezGene.getEntrezGeneId());
						return ps;
					}

				}
		);
	}

	/**
	 * Deletes a single EntrezGene record, identified by the primary key ID (entrez gene ID)
	 * @param id
	 * @return
	 */
	public Integer deleteEntrezGene(Integer id){
		return jdbcTemplate.update("DELETE FROM `ENTREZ_GENE` WHERE ENTREZ_GENE_ID = ?", id);
	}
	
	// Row mapper
	public class EntrezGeneRowMapper implements RowMapper<EntrezGene>{
		@Override public EntrezGene mapRow(ResultSet resultSet, int i) throws SQLException {
			EntrezGene gene = new EntrezGene();

			gene.setEntrezGeneId(resultSet.getInt("entrez_gene_id"));
			gene.setTaxId(resultSet.getInt("tax_id"));
			gene.setLocusTag(resultSet.getString("locus_tag"));
			gene.setPrimaryGeneSymbol(resultSet.getString("primary_gene_symbol"));
			gene.setChromosome(resultSet.getString("chromosome"));
			gene.setChromosomeLocation(resultSet.getString("chromosome_location"));
			gene.setDescription(resultSet.getString("description"));
			gene.setGeneType(resultSet.getString("gene_type"));
			gene.setIsKinase(resultSet.getString("is_kinase"));
			gene.setIsCgcGene(resultSet.getString("is_cgc_gene"));

			Map<String, Object> crossReferenceMap = new HashMap<>();
			for (String ref: resultSet.getString("db_xrefs").split("\\|")){
				String[] bits = ref.split(":");
				if (bits.length == 2) {
					crossReferenceMap.put(bits[0].trim(), bits[1].trim());
				}
			}
			gene.setDatabaseCrossReferences(crossReferenceMap);

			Set<String> geneSymbolAliases = new HashSet<>();
			for (String alias: resultSet.getString("gene_symbol_aliases").split(",")){
				if (alias.trim() != null && !alias.equals("")){
					geneSymbolAliases.add(alias);
				}
			}
			gene.setGeneSymbolAliases(geneSymbolAliases);

			return gene;
		}
	}
	
}

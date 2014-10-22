package org.oncoblocks.restdemo.dao;

import org.oncoblocks.restdemo.models.RnaSeqGeneExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by woemler on 10/2/14.
 */

@Repository
public class RnaSeqGeneExpressionDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// Get by ID
	public RnaSeqGeneExpression findRnaSeqGeneExpressionById(Integer id){
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM `RNASEQ_GENE_EXPRESSION` WHERE RNASEQ_GENE_EXPRESSION_ID = ?",
					new Object[]{id},
					new RnaSeqGeneExpressionRowMapper());
		} catch (EmptyResultDataAccessException e){
			return null;
		} 
	}

	// Find all
	public List<RnaSeqGeneExpression> findAllRnaSeqGeneExpression(Integer limit, Integer offset){
		return jdbcTemplate.query("SELECT * FROM `RNASEQ_GENE_EXPRESSION`", new RnaSeqGeneExpressionRowMapper());
	}

	// Find by attributes

	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByCellLine(Integer cellLineId, Integer limit, Integer offset){
		return jdbcTemplate.query(
				"SELECT * FROM `RNASEQ_GENE_EXPRESSION` WHERE CELL_LINE_ID = ?",
				new Object[] {cellLineId},
				new RnaSeqGeneExpressionRowMapper()
		);
	}

	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByGene(Integer entrezGeneId, Integer limit, Integer offset){
		return jdbcTemplate.query(
				"SELECT * FROM `RNASEQ_GENE_EXPRESSION` WHERE ENTREZ_GENE_ID = ?",
				new Object[] {entrezGeneId},
				new RnaSeqGeneExpressionRowMapper()
		);
	}

	// Add rnaseq gene expression data
	public Integer addRnaSeqGeneExpression(final RnaSeqGeneExpression rnaSeqGeneExpression){
		return jdbcTemplate.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws
							SQLException {
						PreparedStatement ps = connection.prepareStatement(
								"INSERT INTO `RNASEQ_GENE_EXPRESSION` (CELL_LINE_ID, ENTREZ_GENE_ID, ACCESSION," +
										"`VALUE` VALUES (?, ?, ?, ?);"
						);
						ps.setInt(1, rnaSeqGeneExpression.getCellLineId());
						ps.setInt(2, rnaSeqGeneExpression.getEntrezGeneId());
						ps.setString(3, rnaSeqGeneExpression.getAccession());
						ps.setDouble(4, rnaSeqGeneExpression.getValue());
						return ps;
					}

				}
		);
	}
	
	public class RnaSeqGeneExpressionRowMapper implements RowMapper<RnaSeqGeneExpression> {
		@Override public RnaSeqGeneExpression mapRow(ResultSet resultSet, int i) throws SQLException {
			
			RnaSeqGeneExpression rnaSeqGeneExpression = new RnaSeqGeneExpression();
			
			rnaSeqGeneExpression.setRnaSeqGeneExpressionId(resultSet.getInt("rnaseq_gene_expression_id"));
			rnaSeqGeneExpression.setCellLineId(resultSet.getInt("cell_line_id"));
			rnaSeqGeneExpression.setEntrezGeneId(resultSet.getInt("entrez_gene_id"));
			rnaSeqGeneExpression.setAccession(resultSet.getString("accession"));
			rnaSeqGeneExpression.setValue(resultSet.getDouble("value"));
			
			return rnaSeqGeneExpression;
			
		}
	}
	
}

package org.oncoblocks.restdemo.dao;

import org.oncoblocks.restdemo.models.CellLine;
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
import java.util.List;

/**
 * Created by woemler on 10/2/14.
 */

@Repository
public class CellLineDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Find all
	public List<CellLine> findAllCellLines(){
		return jdbcTemplate.query("SELECT * FROM `CELL_LINES`", new CellLineRowMapper());
	}

	// Find by ID
	public CellLine findCellLineById(Integer id){
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM `CELL_LINES` WHERE CELL_LINE_ID = ?",
					new Object[]{id},
					new CellLineRowMapper());
		} catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	// Find by attributes

	public List<CellLine> findCellLineByCcleName(String ccleName){
		return jdbcTemplate.query(
				"SELECT * FROM `CELL_LINES` WHERE CCLE_NAME = ?",
				new Object[]{ccleName},
				new CellLineRowMapper()
		);
	}

	/**
	 * Adds a new cell line and returns the primary key value
	 * @param cellLine
	 * @return
	 */
	public Integer addCellLine(final CellLine cellLine){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws
						SQLException {
					PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO `CELL_LINES` (CCLE_NAME, PRIMARY_NAME, SITE_PRIMARY," +
									"HISTOLOGY_PRIMARY, HISTOLOGY_SUBTYPE, GENDER, NOTES, SOURCE) " +
									"VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
					);
					ps.setString(1, cellLine.getCcleName());
					ps.setString(2, cellLine.getPrimaryName());
					ps.setString(3, cellLine.getPrimarySite());
					ps.setString(4, cellLine.getPrimaryHistology());
					ps.setString(5, cellLine.getHistologySubtype());
					ps.setString(6, cellLine.getGender());
					ps.setString(7, cellLine.getNotes());
					ps.setString(8, cellLine.getSource());
					return ps;
				}

			},
			keyHolder
		);
		return keyHolder.getKey().intValue();
	}

	// Update cell line
	public Integer updateCellLine(final CellLine cellLine){
		return jdbcTemplate.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws
							SQLException {
						PreparedStatement ps = connection.prepareStatement(
								"UPDATE `CELL_LINES` SET CCLE_NAME = ?, PRIMARY_NAME = ?, SITE_PRIMARY = ?, " +
										" HISTOLOGY_PRIMARY = ?, HISTOLOGY_SUBTYPE = ?, GENDER = ?, " +
										" NOTES = ?, SOURCE = ? " +
										" WHERE CELL_LINE_ID = ? "
						);
						ps.setString(1, cellLine.getCcleName());
						ps.setString(2, cellLine.getPrimaryName());
						ps.setString(3, cellLine.getPrimarySite());
						ps.setString(4, cellLine.getPrimaryHistology());
						ps.setString(5, cellLine.getHistologySubtype());
						ps.setString(6, cellLine.getGender());
						ps.setString(7, cellLine.getNotes());
						ps.setString(8, cellLine.getSource());
						ps.setInt(9, cellLine.getCellLineId());
						return ps;
					}

				}
		);
	}

	// Delete cell line
	public Integer deleteCellLine(Integer id){
		return jdbcTemplate.update("DELETE FROM `CELL_LINES` WHERE CELL_LINE_ID = ?", id);
	}
	
	public class CellLineRowMapper implements RowMapper<CellLine> {
		@Override public CellLine mapRow(ResultSet resultSet, int i) throws SQLException {
			
			CellLine cellLine = new CellLine();
			
			cellLine.setCellLineId(resultSet.getInt("cell_line_id"));
			cellLine.setCcleName(resultSet.getString("ccle_name"));
			cellLine.setPrimarySite(resultSet.getString("primary_name"));
			cellLine.setPrimarySite(resultSet.getString("site_primary"));
			cellLine.setPrimaryHistology(resultSet.getString("histology_primary"));
			cellLine.setHistologySubtype(resultSet.getString("histology_subtype"));
			cellLine.setGender(resultSet.getString("gender"));
			cellLine.setNotes(resultSet.getString("notes"));
			cellLine.setSource(resultSet.getString("source"));
			
			return cellLine;
			
		}
	}
	
}

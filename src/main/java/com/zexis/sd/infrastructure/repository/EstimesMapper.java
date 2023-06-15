package com.zexis.sd.infrastructure.repository;

import static com.zexis.sd.infrastructure.repository.EstimesDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.zexis.sd.infrastructure.entity.Estimes;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface EstimesMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Estimes>, CommonUpdateMapper {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	BasicColumn[] selectList = BasicColumn.columnList(projectId, number, theme, function, charge, design,
			implementation, unit, binding);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "EstimesResult", value = {
			@Result(column = "project_id", property = "projectId", jdbcType = JdbcType.INTEGER),
			@Result(column = "number", property = "number", jdbcType = JdbcType.INTEGER),
			@Result(column = "theme", property = "theme", jdbcType = JdbcType.VARCHAR),
			@Result(column = "function", property = "function", jdbcType = JdbcType.VARCHAR),
			@Result(column = "charge", property = "charge", jdbcType = JdbcType.INTEGER),
			@Result(column = "design", property = "design", jdbcType = JdbcType.VARCHAR),
			@Result(column = "implementation", property = "implementation", jdbcType = JdbcType.VARCHAR),
			@Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
			@Result(column = "binding", property = "binding", jdbcType = JdbcType.VARCHAR) })
	List<Estimes> selectMany(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("EstimesResult")
	Optional<Estimes> selectOne(SelectStatementProvider selectStatement);

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default long count(CountDSLCompleter completer) {
		return MyBatis3Utils.countFrom(this::count, estimes, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default int delete(DeleteDSLCompleter completer) {
		return MyBatis3Utils.deleteFrom(this::delete, estimes, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default int insert(Estimes row) {
		return MyBatis3Utils.insert(this::insert, row, estimes,
				c -> c.map(projectId).toProperty("projectId").map(number).toProperty("number").map(theme)
						.toProperty("theme").map(function).toProperty("function").map(charge).toProperty("charge")
						.map(design).toProperty("design").map(implementation).toProperty("implementation").map(unit)
						.toProperty("unit").map(binding).toProperty("binding"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default int insertMultiple(Collection<Estimes> records) {
		return MyBatis3Utils.insertMultiple(this::insertMultiple, records, estimes,
				c -> c.map(projectId).toProperty("projectId").map(number).toProperty("number").map(theme)
						.toProperty("theme").map(function).toProperty("function").map(charge).toProperty("charge")
						.map(design).toProperty("design").map(implementation).toProperty("implementation").map(unit)
						.toProperty("unit").map(binding).toProperty("binding"));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default int insertSelective(Estimes row) {
		return MyBatis3Utils.insert(this::insert, row, estimes,
				c -> c.map(projectId).toPropertyWhenPresent("projectId", row::getProjectId).map(number)
						.toPropertyWhenPresent("number", row::getNumber).map(theme)
						.toPropertyWhenPresent("theme", row::getTheme).map(function)
						.toPropertyWhenPresent("function", row::getFunction).map(charge)
						.toPropertyWhenPresent("charge", row::getCharge).map(design)
						.toPropertyWhenPresent("design", row::getDesign).map(implementation)
						.toPropertyWhenPresent("implementation", row::getImplementation).map(unit)
						.toPropertyWhenPresent("unit", row::getUnit).map(binding)
						.toPropertyWhenPresent("binding", row::getBinding));
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default Optional<Estimes> selectOne(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectOne(this::selectOne, selectList, estimes, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default List<Estimes> select(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectList(this::selectMany, selectList, estimes, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default List<Estimes> selectDistinct(SelectDSLCompleter completer) {
		return MyBatis3Utils.selectDistinct(this::selectMany, selectList, estimes, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	default int update(UpdateDSLCompleter completer) {
		return MyBatis3Utils.update(this::update, estimes, completer);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	static UpdateDSL<UpdateModel> updateAllColumns(Estimes row, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(projectId).equalTo(row::getProjectId).set(number).equalTo(row::getNumber).set(theme)
				.equalTo(row::getTheme).set(function).equalTo(row::getFunction).set(charge).equalTo(row::getCharge)
				.set(design).equalTo(row::getDesign).set(implementation).equalTo(row::getImplementation).set(unit)
				.equalTo(row::getUnit).set(binding).equalTo(row::getBinding);
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	static UpdateDSL<UpdateModel> updateSelectiveColumns(Estimes row, UpdateDSL<UpdateModel> dsl) {
		return dsl.set(projectId).equalToWhenPresent(row::getProjectId).set(number).equalToWhenPresent(row::getNumber)
				.set(theme).equalToWhenPresent(row::getTheme).set(function).equalToWhenPresent(row::getFunction)
				.set(charge).equalToWhenPresent(row::getCharge).set(design).equalToWhenPresent(row::getDesign)
				.set(implementation).equalToWhenPresent(row::getImplementation).set(unit)
				.equalToWhenPresent(row::getUnit).set(binding).equalToWhenPresent(row::getBinding);
	}
}
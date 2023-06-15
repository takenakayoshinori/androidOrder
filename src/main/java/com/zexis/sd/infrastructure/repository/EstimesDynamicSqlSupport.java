package com.zexis.sd.infrastructure.repository;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class EstimesDynamicSqlSupport {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	public static final Estimes estimes = new Estimes();
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.project_id")
	public static final SqlColumn<Integer> projectId = estimes.projectId;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.number")
	public static final SqlColumn<Integer> number = estimes.number;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.theme")
	public static final SqlColumn<String> theme = estimes.theme;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.function")
	public static final SqlColumn<String> function = estimes.function;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.charge")
	public static final SqlColumn<Integer> charge = estimes.charge;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.design")
	public static final SqlColumn<String> design = estimes.design;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.implementation")
	public static final SqlColumn<String> implementation = estimes.implementation;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.unit")
	public static final SqlColumn<String> unit = estimes.unit;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.binding")
	public static final SqlColumn<String> binding = estimes.binding;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	public static final class Estimes extends AliasableSqlTable<Estimes> {
		public final SqlColumn<Integer> projectId = column("project_id", JDBCType.INTEGER);
		public final SqlColumn<Integer> number = column("number", JDBCType.INTEGER);
		public final SqlColumn<String> theme = column("theme", JDBCType.VARCHAR);
		public final SqlColumn<String> function = column("function", JDBCType.VARCHAR);
		public final SqlColumn<Integer> charge = column("charge", JDBCType.INTEGER);
		public final SqlColumn<String> design = column("design", JDBCType.VARCHAR);
		public final SqlColumn<String> implementation = column("implementation", JDBCType.VARCHAR);
		public final SqlColumn<String> unit = column("unit", JDBCType.VARCHAR);
		public final SqlColumn<String> binding = column("binding", JDBCType.VARCHAR);

		public Estimes() {
			super("public.estimes", Estimes::new);
		}
	}
}
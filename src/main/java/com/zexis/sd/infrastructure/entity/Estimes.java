package com.zexis.sd.infrastructure.entity;

import java.io.Serializable;
import javax.annotation.Generated;

public class Estimes implements Serializable {

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.project_id")
	private Integer projectId;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.number")
	private Integer number;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.theme")
	private String theme;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.function")
	private String function;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.charge")
	private Integer charge;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.design")
	private String design;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.implementation")
	private String implementation;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.unit")
	private String unit;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.binding")
	private String binding;
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	private static final long serialVersionUID = 1L;

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.project_id")
	public Integer getProjectId() {
		return projectId;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.project_id")
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.number")
	public Integer getNumber() {
		return number;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.number")
	public void setNumber(Integer number) {
		this.number = number;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.theme")
	public String getTheme() {
		return theme;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.theme")
	public void setTheme(String theme) {
		this.theme = theme == null ? null : theme.trim();
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.function")
	public String getFunction() {
		return function;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.function")
	public void setFunction(String function) {
		this.function = function == null ? null : function.trim();
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.charge")
	public Integer getCharge() {
		return charge;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.charge")
	public void setCharge(Integer charge) {
		this.charge = charge;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.design")
	public String getDesign() {
		return design;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.design")
	public void setDesign(String design) {
		this.design = design == null ? null : design.trim();
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.implementation")
	public String getImplementation() {
		return implementation;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.implementation")
	public void setImplementation(String implementation) {
		this.implementation = implementation == null ? null : implementation.trim();
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.unit")
	public String getUnit() {
		return unit;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.unit")
	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.binding")
	public String getBinding() {
		return binding;
	}

	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: public.estimes.binding")
	public void setBinding(String binding) {
		this.binding = binding == null ? null : binding.trim();
	}

	@Override
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", projectId=").append(projectId);
		sb.append(", number=").append(number);
		sb.append(", theme=").append(theme);
		sb.append(", function=").append(function);
		sb.append(", charge=").append(charge);
		sb.append(", design=").append(design);
		sb.append(", implementation=").append(implementation);
		sb.append(", unit=").append(unit);
		sb.append(", binding=").append(binding);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	@Override
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		Estimes other = (Estimes) that;
		return (this.getProjectId() == null ? other.getProjectId() == null
				: this.getProjectId().equals(other.getProjectId()))
				&& (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
				&& (this.getTheme() == null ? other.getTheme() == null : this.getTheme().equals(other.getTheme()))
				&& (this.getFunction() == null ? other.getFunction() == null
						: this.getFunction().equals(other.getFunction()))
				&& (this.getCharge() == null ? other.getCharge() == null : this.getCharge().equals(other.getCharge()))
				&& (this.getDesign() == null ? other.getDesign() == null : this.getDesign().equals(other.getDesign()))
				&& (this.getImplementation() == null ? other.getImplementation() == null
						: this.getImplementation().equals(other.getImplementation()))
				&& (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
				&& (this.getBinding() == null ? other.getBinding() == null
						: this.getBinding().equals(other.getBinding()));
	}

	@Override
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: public.estimes")
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
		result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
		result = prime * result + ((getTheme() == null) ? 0 : getTheme().hashCode());
		result = prime * result + ((getFunction() == null) ? 0 : getFunction().hashCode());
		result = prime * result + ((getCharge() == null) ? 0 : getCharge().hashCode());
		result = prime * result + ((getDesign() == null) ? 0 : getDesign().hashCode());
		result = prime * result + ((getImplementation() == null) ? 0 : getImplementation().hashCode());
		result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
		result = prime * result + ((getBinding() == null) ? 0 : getBinding().hashCode());
		return result;
	}
}
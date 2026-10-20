package com.medaware.model;
public class Condition {
	private int conditionId;
	private String conditionName;
	private String description;
	//Default constructor
	public Condition()
	{
		
	}
	//parameterized constructor
	public Condition(int conditionId,String conditionName,String description)
	{
	 this.conditionId=conditionId;
	 this.conditionName=conditionName;
	 this.description=description;
     }
		//Getter and Setter methods
	public int getConditionId()
	{
		return conditionId;
	}
 	public void setConditionId(int conditionId)
	{
	  this.conditionId=conditionId;
	}
	public String getConditionName()
	{
		return conditionName;
	}
	public void setConditionName(String conditionName)
	{
		this.conditionName=conditionName;
	}
	public String	 getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}
}

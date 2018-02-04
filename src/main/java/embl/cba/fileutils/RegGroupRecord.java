package embl.cba.fileutils;

public class RegGroupRecord {
	String[] groupValues;
	
	public RegGroupRecord(String[] newValues) {
		groupValues=newValues;
	}

	public String[] getValues(){
		return groupValues;
	}
	
	public String getValueByIndex(int index){
		return groupValues[index];
	}
	
	public int getNValues(){
		return groupValues.length;
	}
	
	
	@Override
	public boolean equals(Object anotherValue){
		RegGroupRecord castedValue=(RegGroupRecord) anotherValue;
		
		int nGroups=getNValues();
		if (castedValue.getNValues()!=nGroups) 
			return false;
		
		for (int groupIndex=0;groupIndex<nGroups;groupIndex++){
			if (!this.getValueByIndex(groupIndex).equals(castedValue.getValueByIndex(groupIndex)))
				return false;
		}
		
		
		return true;
	}
}

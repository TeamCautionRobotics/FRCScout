package nedearb.takp.frcscout;


public class ScoutData extends DataSet {
	
	private static final long serialVersionUID = 7862607414576482217L;

	public ScoutData(){
		addValue("teamNumber", new ValueInt(R.id.editTextScoutTeamNumber));
		addValue("teamName", new ValueString(R.id.editTextScoutTeamName));
		addValue("working", new ValueBool(R.id.checkBoxScoutWorking));
		addValue("driveType", new ValueString(R.id.editTextScoutDriveType));
		addValue("auto", new ValueBool(R.id.checkBoxScoutAutonomous));
		addValue("autoHigh", new ValueBool(R.id.checkBoxScoutAutoHigh));
		addValue("autoLow", new ValueBool(R.id.checkBoxScoutAutoLow));
		addValue("autoHot", new ValueBool(R.id.checkBoxScoutDetectHot));
		addValue("autoMobile", new ValueBool(R.id.checkBoxScoutAutoMobile));
		addValue("autoMultiple", new ValueBool(R.id.checkBoxScoutAutoMultiple));
		addValue("autoPoints", new ValueInt(R.id.editTextScoutAutoPoints));
		addValue("teleOffense", new ValueBool(R.id.checkBoxScoutOffense));
		addValue("teleDefense", new ValueBool(R.id.checkBoxScoutDefense));
		addValue("teleTruss", new ValueBool(R.id.checkBoxScoutTruss));
		addValue("teleCatch", new ValueBool(R.id.checkBoxScoutCatch));
		addValue("telePass", new ValueBool(R.id.checkBoxScoutPass));
		addValue("telem2m", new ValueBool(R.id.checkBoxScoutM2M));
		addValue("notes", new ValueString(R.id.editTextScoutNotes));
	}
	
	@Override
	public String getMapName() {
		return values.get("teamNumber").toString()+"";
	}

	@Override
	public String getFileName() {
		return "scout_"+getMapName()+".dat";
	}

}

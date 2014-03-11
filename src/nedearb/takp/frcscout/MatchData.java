package nedearb.takp.frcscout;

public class MatchData extends DataSet {

	private static final long serialVersionUID = 7768409756221273829L;
	

	public MatchData(){
		addValue("teamNumber", new ValueInt(R.id.editTextMatchTeamNumber));
		addValue("matchNumber", new ValueInt(R.id.editTextMatchMatchNumber));
		addValue("isRed", new ValueBool(R.id.toggleButtonMatchIsRed));
		addValue("auto", new ValueBool(R.id.checkBoxMatchAutonomous));
		addValue("autoHigh", new ValueBool(R.id.checkBoxMatchAutoHigh));
		addValue("autoLow", new ValueBool(R.id.checkBoxMatchAutoLow));
		addValue("autoHot", new ValueBool(R.id.checkBoxMatchDetectHot));
		addValue("autoMobile", new ValueBool(R.id.checkBoxMatchAutoMobile));
		addValue("autoPoints", new ValueInt(R.id.editTextMatchAutoPoints));
		addValue("autoMultiple", new ValueBool(R.id.checkBoxMatchAutoMultiple));
		addValue("teleOffense", new ValueBool(R.id.checkBoxMatchOffense));
		addValue("teleDefence", new ValueBool(R.id.checkBoxMatchDefense));
		addValue("teleTruss", new ValueBool(R.id.checkBoxMatchTruss));
		addValue("teleCatch", new ValueBool(R.id.checkBoxMatchCatch));
		addValue("teleHigh", new ValueBool(R.id.checkBoxMatchHigh));
		addValue("teleLow", new ValueBool(R.id.checkBoxMatchLow));
		addValue("telePass", new ValueBool(R.id.checkBoxMatchPass));
		addValue("teleHuman", new ValueBool(R.id.checkBoxMatchHuman));
		addValue("teleAssists", new ValueInt(R.id.editTextMatchAssists));
		addValue("win", new ValueBool(R.id.checkBoxMatchWin));
		addValue("matchPoints", new ValueInt(R.id.editTextMatchPoints));
		addValue("penalties", new ValueInt(R.id.editTextMatchPenalties));
		addValue("m2m", new ValueBool(R.id.checkBoxMatchM2M));
		addValue("notes", new ValueString(R.id.editTextMatchNotes));
	}

	@Override
	public String getMapName() {
		return values.get("matchNumber").toString()+"_"+values.get("teamNumber").toString();
	}

	@Override
	public String getFileName() {
		return getMapName()+".dat";
	}
}

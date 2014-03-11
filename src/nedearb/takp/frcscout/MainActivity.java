package nedearb.takp.frcscout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener {

	public static final int SCOUTING = 1;
	public static final int MATCHES = 2;
	
	private static final int REQUEST_ENABLE_BT = 1;

	public static HashMap<String, ScoutData> scoutMap = new HashMap<String, ScoutData>();
	public static HashMap<String, MatchData> matchMap = new HashMap<String, MatchData>();
	
	public static File folder;
	private Builder requireSDCardDialog;
	private Builder CSVSuccessDialog;
	private Builder CSVFailureDialog;
	public static MainActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((Button)findViewById(R.id.ScoutingButton)).setOnClickListener(this);
		((Button)findViewById(R.id.MatchesButton)).setOnClickListener(this);
		((Button)findViewById(R.id.SendBluetoothZipButton)).setOnClickListener(this);
		((Button)findViewById(R.id.ToCSVButton)).setOnClickListener(this);
		
		requireSDCardDialog = new AlertDialog.Builder(this).setTitle("SD Card Access Disabled").setMessage("You will be unable to save any files until you enable SD card access.").setCancelable(false).setPositiveButton("Okay", this);
		
		CSVSuccessDialog = new AlertDialog.Builder(this).setTitle("CSV Files Saved").setMessage("Successfully created CSV Files").setCancelable(false).setPositiveButton("Okay", this);
		CSVFailureDialog = new AlertDialog.Builder(this).setTitle("CSV Files Save Failed").setMessage("Failed to create CSV Files").setCancelable(false).setPositiveButton("Okay", this);
		
		
		
		instance = this;
		
		load();
	}

	private void load() {
		folder = new File(Environment.getExternalStorageDirectory()+"/frcScout/");
	
		folder.mkdirs();
	
		Log.d("External Storage Writable? ", isExternalStorageWritable()+"");
		
		if(!isExternalStorageWritable()){
			requireSDCardDialog.show();
			return;
		}
		
		Log.d("Loaded Files? ", loadFiles()+"");
		/*
		ScoutData sd = new ScoutData("1492");
		sd.autoDetectHot = false;
		sd.autonomous = true;
		sd.autoQty = 1;
		sd.autoThrowHigh = true;
		sd.autoThrowLow = true;
		sd.defense = false;
		sd.driveType = "mecanum";
		sd.notes = "Go team caution!";
		sd.offense = true;
		sd.teamName = "Team Caution";
		sd.working = true;
		if(!scoutMap.containsKey(sd.getMapName())){
			scoutMap.put(sd.getMapName(), sd);
		}
		Log.d("Saved File? ", saveFile(sd)+"");
		
		MatchData md = new MatchData("4", "1492");
		md.assists = 1;
		md.colorIsRed = false;
		md.defense = false;
		md.goals = 5;
		md.matchPoints = 46;
		md.notes = "Notes lalala";
		md.offense = true;
		md.penalties = 0;
		md.truss = 0;
		md.win = true;
		if(!matchMap.containsKey(md.getMapName())){
			matchMap.put(md.getMapName(), md);
		}
		Log.d("Saved File? ", saveFile(md)+"");
		
		Log.d("Loaded Files? ", loadFiles()+"");
		*/
	}

	public boolean saveFile(DataSet f) {
		
		if(!isExternalStorageWritable()){
			requireSDCardDialog.show();
			return false;
		}
		
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(new File(folder, f.getFileName())));
			f.saveData(pw);
			pw.flush();
			pw.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.ScoutingButton){
			Intent i = new Intent(this, ListMenu.class);
			load();
			Log.d("onClick", "Scouting");
			i.putExtra("type", SCOUTING);
			startActivity(i);
		}
		if(v.getId()==R.id.MatchesButton){
			Intent i = new Intent(this, ListMenu.class);
			load();
			Log.d("onClick", "Matches");
			i.putExtra("type", MATCHES);
			startActivity(i);
		}
		

		if(v.getId()==R.id.ToCSVButton){
			Log.d("onClick", "To CSV");

			try {
				{
					PrintWriter pw = new PrintWriter(new FileOutputStream(new File(folder, "scoutData.csv")));
					ScoutData dummy = new ScoutData();
					boolean first = true;
					
					for(Entry<String, Value> a : dummy.values.entrySet()){
						if(first){
							first = false;
						}else{
							pw.print(",");
						}
						pw.print(a.getValue().name);
					}

					pw.println();
					
					for(Entry<String, ScoutData> e : scoutMap.entrySet()){
						ScoutData d = e.getValue();
	
						first = true;
						for(Entry<String, Value> a : d.values.entrySet()){
							if(first){
								first = false;
							}else{
								pw.print(",");
							}
							pw.print(a.getValue().toString());
						}
						pw.println();
						
					}
					
					pw.flush();
					pw.close();
				}
				{
					PrintWriter pw = new PrintWriter(new FileOutputStream(new File(folder, "matchData.csv")));
					MatchData dummy = new MatchData();
					boolean first = true;
					
					for(Entry<String, Value> a : dummy.values.entrySet()){
						if(first){
							first = false;
						}else{
							pw.print(",");
						}
						pw.print(a.getValue().name);
					}

					pw.println();
					
					for(Entry<String, MatchData> e : matchMap.entrySet()){
						MatchData d = e.getValue();
						
						first = true;
	
						for(Entry<String, Value> a : d.values.entrySet()){
							if(first){
								first = false;
							}else{
								pw.print(",");
							}
							pw.print(a.getValue().toString());
						}
						pw.println();
						
					}
					
					pw.flush();
					pw.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if(v.getId()==R.id.SendBluetoothZipButton || v.getId()==R.id.ToCSVButton){
			Log.d("onClick", "Send Data via Bluetooth");
			
			BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if (mBluetoothAdapter == null) {
			    Log.d("onClick", "Bluetooth not supported.");
			}else{
			    Log.d("onClick", "Bluetooth is supported.");
			    if (!mBluetoothAdapter.isEnabled()) {
				    Log.d("onCreate", "Bluetooth is not enabled.");
			        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			    }
			    if(mBluetoothAdapter.isEnabled()){
				    Log.d("onClick", "Enabled bluetooth.");
				    
				    if(v.getId()==R.id.SendBluetoothZipButton){
						//Compress File
						new Compress(folder.listFiles(), folder+"/allData.zip").zip();
						//Send File
						File file = new File(folder, "allData.zip");
						sendFile(file, "application/zip");
				    }else{
				    	//Compress File
						new Compress(new File[]{new File(folder, "scoutData.csv"), new File(folder, "matchData.csv")}, folder+"/CSVs.zip").zip();
						//Send File
						File file = new File(folder, "CSVs.zip");
						sendFile(file, "application/zip");
				    }
					
					
			    }else{
				    Log.d("onClick", "Failed to enable bluetooth.");
			    }
			    
			}
		}
	}

	public boolean loadFiles() {
		
		if(!isExternalStorageWritable()){
			requireSDCardDialog.show();
			return false;
		}
		
		
		Log.d("File", folder.getAbsolutePath());
		try {
			
			File[] files = folder.listFiles();
			for(int i=0;i<files.length;i++){
				File file = files[i];
				Log.d("Found File: ", file.getName());
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				loadData(file.getName(), inputStream);
				inputStream.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void loadData(String file, BufferedReader inputStream) {
		file = file.split("\\.", 2)[0];
		String[] split = file.split("_", 2);
		
		if(split[0].equals("scout")){
			try{
				if(!MainActivity.scoutMap.containsKey(split[1])){
					MainActivity.scoutMap.put(split[1], new ScoutData());
				}
				MainActivity.scoutMap.get(split[1]).loadData(inputStream);
			}catch(Exception e){
				
			}
		}else{
			try{
				if(!MainActivity.matchMap.containsKey(file)){
					MainActivity.matchMap.put(file, new MatchData());
				}
				MainActivity.matchMap.get(file).loadData(inputStream);
			}catch(Exception e){
				
			}
		}
		
	}

	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		
	}

	public void deleteDataFile(DataSet data) {
		File f = new File(folder, data.getFileName());
		f.delete();
	}

	private void sendFile(File file, String type) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType(type);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        startActivity(intent);
	}

}

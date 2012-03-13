import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
* BoarderLimit v1.x
* Copyright (C) 2012 Visual Illusions Entertainment
* @author darkdiplomat <darkdiplomat@visualillusionsent.net>
* 
* This file is part of BoarderLimit
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see http://www.gnu.org/copyleft/gpl.html.
*/

public class BoarderProps {
	private final String PropDir = "plugins/config/BoarderLimit/";
	private final String PropFile = "BLProp.ini";
	private PropertiesFile BLPF;
	
	Logger log = Logger.getLogger("Minecraft");
	
	private int NorPosX = 4000, NorNegX = -4000, NorPosZ = 4000, NorNegZ = -4000;
	private int NetPosX = 4000, NetNegX = -4000, NetPosZ = 4000, NetNegZ = -4000;
	private int EndPosX = 4000, EndNegX = -4000, EndPosZ = 4000, EndNegZ = -4000;
	
	public BoarderProps(BoarderLimit BL){
		Load();
	}
	
	private void Load(){
		File PD = new File(PropDir);
		File PF = new File(PropDir+PropFile);
		
		if(!PD.exists()){
			PD.mkdirs();
			try {
				PF.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BLPF = new PropertiesFile(PropDir+PropFile);
			BLPF.setInt("Normal-X", NorPosX);
			BLPF.setInt("Normal-Z", NorPosZ);
			BLPF.setInt("Nether-X", NetPosX);
			BLPF.setInt("Nether-Z", NetPosZ);
			BLPF.setInt("End-X", EndPosX);
			BLPF.setInt("End-Z", EndPosZ);
		}
		else{
			BLPF = new PropertiesFile(PropDir+PropFile);
			if(!BLPF.containsKey("Normal-X")){ BLPF.setInt("Normal-X", NorPosX); }
			NorPosX = BLPF.getInt("Normal-X");
			NorNegX = NorPosX - (NorPosX*2);
			if(!BLPF.containsKey("Normal-Z")){ BLPF.setInt("Normal-Z", NorPosZ); }
			NorPosZ = BLPF.getInt("Normal-Z");
			NorNegZ = NorPosZ - (NorPosZ*2);
			if(!BLPF.containsKey("Nether-X")){ BLPF.setInt("Nether-X", NetPosX); }
			NetPosX = BLPF.getInt("Nether-X");
			NetNegX = NetPosX - (NetPosZ*2);
			if(!BLPF.containsKey("Nether-Z")){ BLPF.setInt("Nether-Z", NetPosZ); }
			NetPosZ = BLPF.getInt("Nether-Z");
			NetNegZ = NetPosZ - (NetPosZ*2);
			if(!BLPF.containsKey("End-X")){ BLPF.setInt("End-X", EndPosX); }
			EndPosX = BLPF.getInt("End-X");
			EndNegX = EndPosX - (EndPosX*2);
			if(!BLPF.containsKey("End-Z")){ BLPF.setInt("End-Z", EndPosZ); }
			EndPosZ = BLPF.getInt("End-Z");
			EndNegZ = EndPosZ - (EndPosZ*2);
		}
	}
	
	public int getPosNorX(){
		return NorPosX;
	}
	
	public int getNegNorX(){
		return NorNegX;
	}
	
	public int getPosNorZ(){
		return NorPosZ;
	}
	
	public int getNegNorZ(){
		return NorNegZ;
	}
	
	public int getPosNetX(){
		return NetPosX;
	}
	
	public int getNegNetX(){
		return NetNegX;
	}
	
	public int getPosNetZ(){
		return NetPosZ;
	}
	
	public int getNegNetZ(){
		return NetNegZ;
	}
	
	public int getPosEndX(){
		return EndPosX;
	}
	
	public int getNegEndX(){
		return EndNegX;
	}
	
	public int getPosEndZ(){
		return EndPosZ;
	}
	
	public int getNegEndZ(){
		return EndNegZ;
	}
}

/*******************************************************************************\
* BoarderLimit 1.x                                                              *
* Copyright (C) 2012 Visual Illusions Entertainment                             *
* @author darkdiplomat <darkdiplomat@visualillusionsent.net>                    *
*                                                                               *
* This file is part of BoarderLimit                                             *
*                                                                               *
* This program is free software: you can redistribute it and/or modify          *
* it under the terms of the GNU General Public License as published by          *
* the Free Software Foundation, either version 3 of the License, or             *
* (at your option) any later version.                                           *
*                                                                               *
* This program is distributed in the hope that it will be useful,               *
* but WITHOUT ANY WARRANTY; without even the implied warranty of                *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                          *
* See the GNU General Public License for more details.                          *
*                                                                               *
* You should have received a copy of the GNU General Public License             *
* along with this program.  If not, see http://www.gnu.org/licenses/gpl.html    *
*                                                                               *
\*******************************************************************************/
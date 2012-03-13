import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class BoarderLimit extends Plugin{
	Logger log = Logger.getLogger("Minecraft");
	public final String name = "BoarderLimit";
	public final String version = "1.1";
	public String currver = version;
	private BoarderListener bl;
	private BoarderProps bp;
		
	public void enable(){
		log.info(name + " v"+version+" by DarkDiplomat Enabled!");
		if(!isLatest()){
			log.info("There is an update available! v"+currver);
		}
	}
	
	public void disable(){
		log.info(name +" v"+version+" Disabled!");
	}

	public void initialize(){
		bp = new BoarderProps(this);
		bl = new BoarderListener(this, bp);
		etc.getLoader().addListener(PluginLoader.Hook.TELEPORT, bl, this, PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener(PluginLoader.Hook.LOGIN, bl, this, PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener(PluginLoader.Hook.PLAYER_MOVE, bl, this, PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener(PluginLoader.Hook.COMMAND, bl, this, PluginListener.Priority.HIGH);
	}
	
	public boolean isLatest(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://visualillusionsent.net/cmod_plugins/versions.php?plugin="+name).openStream()));
            String inputLine;
            if ((inputLine = in.readLine()) != null) {
                currver = inputLine;
            }
            in.close();
            return Float.valueOf(version.replace("_", "")) >= Float.valueOf(currver.replace("_", ""));
        } 
        catch (Exception E) {
        }
        return true;
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
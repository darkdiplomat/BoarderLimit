
public class BoarderListener extends PluginListener{
    private BoarderLimit BL;
	private BoarderProps BP;
	private Location spawn = etc.getServer().getWorld(0).getSpawnLocation();
	private int posX, negX, posZ, negZ; //NORMAL WORLD
	private int posNX, negNX, posNZ, negNZ; //NETHER WORLD
	private int posEX, negEX, posEZ, negEZ; //END WORLD
	
	public BoarderListener(BoarderLimit BL, BoarderProps BP){
	    this.BL = BL;
		this.BP = BP;
		setBoarder();
	}
	
	private void setBoarder(){
		//Normal
		posX = BP.getPosNorX();
		negX = BP.getNegNorX();
		posZ = BP.getPosNorZ();
		negZ = BP.getNegNorZ();
		//Nether
		posNX = BP.getPosNetX();
		negNX = BP.getNegNetX();
		posNZ = BP.getPosNetZ();
		negNZ = BP.getNegNetZ();
		//END
		posEX = BP.getPosEndX();
		negEX = BP.getNegEndX();
		posEZ = BP.getPosEndZ();
		negEZ = BP.getNegEndZ();
	}
	
	
	public void onPlayerMove(Player player, Location from, Location to){
	    Location relocate = new Location(player.getWorld(), player.getX(), player.getY(), player.getZ(), player.getRotation(), player.getPitch());
	    boolean moved = false;
		if(player.getLocation().dimension == 0){ //NORMAL WORLD
			if(relocate.x <= negX-1 || relocate.x >= posX+1 || relocate.z >= posZ+1 || relocate.z <= negZ-1){
			    relocate = getRelocation(relocate);
			    moved = true;
			}
		}
		else if(player.getLocation().dimension == -1){ //NETHER WORLD
		    if(relocate.x <= negNX-1 || relocate.x >= posNX+1 || relocate.z >= posNZ+1 || relocate.z <= negNZ-1){
		        relocate = getRelocation(relocate);
		        moved = true;
            }
		}
		else if(player.getLocation().dimension == 1){ //END WORLD
		    if(relocate.x <= negEX-1 || relocate.x >= posEX+1 || relocate.z >= posEZ+1 || relocate.z <= negEZ-1){
		        relocate = getRelocation(relocate);
                moved = true;
            }
		}
		if(moved){
		    player.notify("A mysterious force pushes you...");
            player.teleportTo(relocate);
		}
	}
	
	public void onLogin(Player player){
	    boolean moved = false;
	    Location relocate = new Location(player.getWorld(), player.getX(), player.getY(), player.getZ(), player.getRotation(), player.getPitch());
		if(player.getLocation().dimension == 0){ //NORMAL WORLD
			if(relocate.z > posZ || relocate.z < negZ || relocate.z > posX || relocate.x < negX){
			    moved = true;
			    relocate = getRelocation(relocate);
			}
		}
		else if(player.getLocation().dimension == -1){ //NETHER WORLD
		    if(relocate.z > posNZ || relocate.z < negNZ || relocate.z > posNX || relocate.x < negNX){
                moved = true;
                relocate = getRelocation(relocate);
            }
		}
		else if(player.getLocation().dimension == 1){ //END WORLD
		    if(relocate.z > posEZ || relocate.z < negEZ || relocate.z > posEX || relocate.x < negEX){
                moved = true;
                relocate = getRelocation(relocate);
            }
		}
		if(moved){
		    player.notify("A mysterious force pushes you...");
            player.teleportTo(spawn);
		}
	}
	
	public boolean onTeleport(Player player, Location from, Location to){
		if(player.getLocation().dimension == 0){ //NORMAL WORLD
			if(to.x < negX || to.x > posX || to.z < negZ || to.z > posZ){
				player.notify("A mysterious force keeps you from going there...");
				return true;
			}
		}
		else if (player.getLocation().dimension == -1){ //NETHER WORLD
			if(to.x < negNX || to.x > posNX || to.z < negNZ || to.z > posNZ){
				player.notify("A mysterious force keeps you from going there...");
				return true;
			}
		}
		else if (player.getLocation().dimension == 1){ //END WORLD
			if(to.x < negEX || to.x > posEX || to.z < negEZ || to.z > posEZ){
				player.notify("A mysterious force keeps you from going there...");
				return true;
			}
		}
		return false;
	}
	
	public boolean onCommand(Player player, String[] args){
	    if(args[0].equalsIgnoreCase("/boarderlimit")){
	        player.sendMessage("§9--§6 "+BL.name+" v"+BL.version+" by §aDarkDiplomat §9--");
	        player.sendMessage((!BL.isLatest() ? "§9--§6 There is a new version availible! v"+BL.currver+" §9--" : "§9--§6 Latest Version Installed! §9--"));
	        player.sendMessage("§9--Nether Limit §6+X: §e"+posNX+"§6 -X: §e"+negNX+"§6 +Z: §e"+posNZ+"§6 -Z:§e "+negNZ);
	        player.sendMessage("§9--Normal Limit §6+X: §e"+posX+"§6 -X: §e"+negX+"§6 +Z: §e"+posZ+"§6 -Z:§e "+negZ);
	        player.sendMessage("§9--End Limit §6+X: §e"+posEX+"§6 -X: §e"+negEX+"§6 +Z: §e"+posEZ+"§6 -Z:§e "+negEZ);
	        return true;
	    }
	    return false;
	}
	
	private Location getRelocation(Location current){
	    while(current.x <= negX-1 || current.x >= posX+1 || current.z >= posZ+1 || current.z <= negZ-1){
            if(current.x <= negX-1){
                current.x += 3;
            }
            else if(current.x >= posX+1){
                current.x -= 3;
            }
            if(current.z >= posZ+1){
                current.z -= 3;
            }
            else if(current.z <= negZ-1){
                current.z += 3;
            }
	    }
	    return current;
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
package fr.skyost.hungergames.utils.borders.types;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.patterns.SingleBlockPattern;

import fr.skyost.hungergames.utils.borders.BorderParams;
import fr.skyost.hungergames.utils.borders.WorldEditBorder;

public class Cylinder extends WorldEditBorder {
	
	@Override
	protected void createBorder(BorderParams bp, EditSession es) throws MaxChangedBlocksException {
		es.makeCylinder(new Vector(bp.getX(), 0, bp.getZ()), new SingleBlockPattern(new BaseBlock(bp.getBlockID(), bp.getBlockMeta())), bp.getRadius(), bp.getRadius(), 256, false);
	}
	
	@Override
	public String getID() {
		return "Cylinder";
	}
	
	@Override
	public String getDescription() {
		return "Creates a cylinder wall around the map";
	}
}

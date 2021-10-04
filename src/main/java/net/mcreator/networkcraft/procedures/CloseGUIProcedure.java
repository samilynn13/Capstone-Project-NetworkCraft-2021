package net.mcreator.networkcraft.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.networkcraft.NetworkcraftModElements;

import java.util.Map;

@NetworkcraftModElements.ModElement.Tag
public class CloseGUIProcedure extends NetworkcraftModElements.ModElement {
	public CloseGUIProcedure(NetworkcraftModElements instance) {
		super(instance, 9);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure CloseGUI!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}

package net.mcreator.networkcraft.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.networkcraft.block.SwitchBlockBlock;
import net.mcreator.networkcraft.block.ComputerMonitorOffBlockBlock;
import net.mcreator.networkcraft.NetworkcraftModVariables;
import net.mcreator.networkcraft.NetworkcraftModElements;

import java.util.Map;
import java.util.HashMap;

@NetworkcraftModElements.ModElement.Tag
public class SpawnWithItemNewWorldProcedure extends NetworkcraftModElements.ModElement {
	public SpawnWithItemNewWorldProcedure(NetworkcraftModElements instance) {
		super(instance, 1);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SpawnWithItemNewWorld!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SpawnWithItemNewWorld!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(NetworkcraftModVariables.MapVariables.get(world).PlayerJoinedWorldBefore))) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(ComputerMonitorOffBlockBlock.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(SwitchBlockBlock.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			NetworkcraftModVariables.MapVariables.get(world).PlayerJoinedWorldBefore = (boolean) (true);
			NetworkcraftModVariables.MapVariables.get(world).syncData(world);
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", entity.getPosX());
		dependencies.put("y", entity.getPosY());
		dependencies.put("z", entity.getPosZ());
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}

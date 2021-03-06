package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityMechFall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class MechFallRenderer extends Render<EntityMechFall> {
	private final ResourceLocation texture;

	public MechFallRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityMechFall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 5, 1).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMechFall entity) {
		return texture;
	}
}

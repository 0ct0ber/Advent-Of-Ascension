package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class ConcussionStaff extends BaseStaff {
	public ConcussionStaff(int durability) {
		super(durability);
		setTranslationKey("ConcussionStaff");
		setRegistryName("aoa3:concussion_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.staffConcussion;
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		List<EntityMob> list = caster.world.getEntitiesWithinAABB(EntityMob.class, caster.getEntityBoundingBox().grow(8));

		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.runePower, 4);
		runes.put(ItemRegister.runeStorm, 4);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		for (EntityMob e : (List<EntityMob>)args) {
			EntityUtil.pushEntityAway(caster, e, 3f);
			WorldUtil.createExplosion(caster, e.world, e.posX, e.posY + e.height + 0.5, e.posZ, 2.3f);
			e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 25, 10, true, true));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ConcussionStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}

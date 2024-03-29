package moCreatures.entities;

import moCreatures.mod_MoCreatures;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityPolarBear extends EntityBear
{
	public static int counterEntity;

	public EntityPolarBear(World world)
	{
		super(world);
		texture = "/mob/polarbear.png";
		attackRange = 1.0D;
		health = 30;
	}

	protected Entity findPlayerToAttack()
	{
		if(worldObj.difficultySetting > 0)
		{
			EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, attackRange);
			if(entityplayer != null && worldObj.difficultySetting > 0)
				return entityplayer;
			if(rand.nextInt(20) == 0)
			{
				EntityLiving entityliving = getClosestTarget(this, 8.0D);
				return entityliving;
			}
		}
		return null;
	}

	public void onLivingUpdate()
	{
		if(worldObj.difficultySetting == 1)
		{
			attackRange = 5.0D;
			force = 3;
		} else if(worldObj.difficultySetting > 1)
		{
			attackRange = 8.0D;
			force = 5;
		}
		super.onLivingUpdate();
	}

	public int getMaxSpawnedInChunk()
	{
		return 2;
	}

	public void setEntityDead()
	{
		counterEntity--;
		super.setEntityDead();
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("CounterEntity", counterEntity);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		counterEntity = nbttagcompound.getInteger("CounterEntity");
	}

	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(posY);
		int j = MathHelper.floor_double(boundingBox.minY);
		int k = MathHelper.floor_double(motionX);
		if((worldObj.getBlockId(i, j - 1, k) == Block.snow.blockID || worldObj.getBlockId(i, j, k) == Block.snow.blockID) && super.getCanSpawnHere2())
		{
			if(counterEntity >= mod_MoCreatures.maxPolarBears)
				return false;
			counterEntity++;
			return true;
		} 
		return false;
	}
}

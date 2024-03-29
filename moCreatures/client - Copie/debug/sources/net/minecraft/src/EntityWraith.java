package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class EntityWraith extends EntityFlyerMob
{

    public EntityWraith(World world)
    {
        super(world);
        texture = "/mob/wraith.png";
        setSize(1.5F, 1.5F);
        isImmuneToFire = false;
        c = 3;
        health = 10;
        moveSpeed = 1.3F;
    }

    protected String getLivingSound()
    {
        return "wraith";
    }

    protected String getHurtSound()
    {
        return "wraithhurt";
    }

    protected String getDeathSound()
    {
        return "wraithdying";
    }

    protected int getDropItemId()
    {
        return Item.gunpowder.shiftedIndex;
    }

    public void onLivingUpdate()
    {
        if(worldObj.difficultySetting == 1)
        {
            c = 2;
        } else
        if(worldObj.difficultySetting > 1)
        {
            c = 3;
        }
        if(worldObj.isDaytime())
        {
            float f = getEntityBrightness(1.0F);
            if(f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                fire = 300;
            }
        }
        super.onLivingUpdate();
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
        if(worldObj.difficultySetting >= mod_mocreatures.wraithSpawnDifficulty.get() + 1 && rand.nextInt(2) == 0 && super.getCanSpawnHere())
        {
            if(counterEntity >= mod_mocreatures.maxWraithS.get())
            {
                return false;
            } else
            {
                counterEntity++;
                return true;
            }
        } else
        {
            return false;
        }
    }

    public boolean c2()
    {
        return super.getCanSpawnHere();
    }

    public static int counterEntity;
}

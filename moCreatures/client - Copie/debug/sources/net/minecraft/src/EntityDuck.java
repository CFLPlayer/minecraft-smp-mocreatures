package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class EntityDuck extends EntityChicken
{

    public EntityDuck(World world)
    {
        super(world);
        texture = "/mob/duck.png";
        setSize(0.3F, 0.4F);
        health = 4;
        timeUntilNextEgg = rand.nextInt(6000) + 6000;
    }

    protected String getLivingSound()
    {
        return "duck";
    }

    protected String getHurtSound()
    {
        return "duckhurt";
    }

    protected String getDeathSound()
    {
        return "duckhurt";
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
        if(super.getCanSpawnHere())
        {
            if(counterEntity >= mod_mocreatures.maxDuckS.get())
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

    public static int counterEntity;
}

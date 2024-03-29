package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ModelBird extends ModelBase
{

    public ModelBird()
    {
        byte byte0 = 16;
        head = new ModelRenderer(0, 0);
        head.addBox(-1.5F, -3F, -2F, 3, 3, 3, 0.0F);
        head.setPosition(0.0F, -1 + byte0, -4F);
        beak = new ModelRenderer(14, 0);
        beak.addBox(-2F, -1.5F, -3F, 1, 1, 2, 0.0F);
        beak.setPosition(1.5F, -1 + byte0, -4F);
        body = new ModelRenderer(0, 9);
        body.addBox(-2F, -4F, -3F, 4, 8, 4, 0.0F);
        body.setPosition(0.0F, 0 + byte0, 0.0F);
        body.rotateAngleX = 1.047198F;
        leftleg = new ModelRenderer(26, 0);
        leftleg.addBox(-1F, 0.0F, -4F, 3, 4, 3);
        leftleg.setPosition(-2F, 3 + byte0, 1.0F);
        rightleg = new ModelRenderer(26, 0);
        rightleg.addBox(-1F, 0.0F, -4F, 3, 4, 3);
        rightleg.setPosition(1.0F, 3 + byte0, 1.0F);
        rwing = new ModelRenderer(24, 13);
        rwing.addBox(-1F, 0.0F, -3F, 1, 5, 5);
        rwing.setPosition(-2F, -2 + byte0, 0.0F);
        lwing = new ModelRenderer(24, 13);
        lwing.addBox(0.0F, 0.0F, -3F, 1, 5, 5);
        lwing.setPosition(2.0F, -2 + byte0, 0.0F);
        tail = new ModelRenderer(0, 23);
        tail.addBox(-6F, 5F, 2.0F, 4, 1, 4, 0.0F);
        tail.setPosition(4F, -3 + byte0, 0.0F);
        tail.rotateAngleX = 0.261799F;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        head.render(f5);
        beak.render(f5);
        body.render(f5);
        leftleg.render(f5);
        rightleg.render(f5);
        rwing.render(f5);
        lwing.render(f5);
        tail.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.rotateAngleX = -(f4 / 2.0F / 57.29578F);
        head.rotateAngleY = f3 / 2.0F / 57.29578F;
        beak.rotateAngleY = head.rotateAngleY;
        leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * f1;
        rwing.rotateAngleZ = f2;
        lwing.rotateAngleZ = -f2;
    }

    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer leftleg;
    public ModelRenderer rightleg;
    public ModelRenderer rwing;
    public ModelRenderer lwing;
    public ModelRenderer beak;
    public ModelRenderer tail;
}

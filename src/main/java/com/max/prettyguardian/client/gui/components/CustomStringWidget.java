//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.max.prettyguardian.client.gui.components;

import java.util.Objects;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractStringWidget;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomStringWidget extends AbstractStringWidget {
    private float alignX;

    public CustomStringWidget(Component p_268211_, Font p_267963_) {
        this(0, 0, p_267963_.width(p_268211_.getVisualOrderText()), 9, p_268211_, p_267963_);
        Objects.requireNonNull(p_267963_);
    }

    public CustomStringWidget(int p_268183_, int p_268082_, Component p_268069_, Font p_268121_) {
        this(0, 0, p_268183_, p_268082_, p_268069_, p_268121_);
    }

    public CustomStringWidget(int p_268199_, int p_268137_, int p_268178_, int p_268169_, Component p_268285_, Font p_268047_) {
        super(p_268199_, p_268137_, p_268178_, p_268169_, p_268285_, p_268047_);
        this.alignX = 0.5F;
        this.active = false;
    }

    public CustomStringWidget setColor(int p_270680_) {
        super.setColor(p_270680_);
        return this;
    }

    private CustomStringWidget horizontalAlignment(float p_267947_) {
        this.alignX = p_267947_;
        return this;
    }

    public CustomStringWidget alignLeft() {
        return this.horizontalAlignment(0.0F);
    }

    public CustomStringWidget alignCenter() {
        return this.horizontalAlignment(0.5F);
    }

    public CustomStringWidget alignRight() {
        return this.horizontalAlignment(1.0F);
    }

    public void renderWidget(GuiGraphics p_281367_, int p_268221_, int p_268001_, float p_268214_) {
        Component $$4 = this.getMessage();
        Font $$5 = this.getFont();
        int $$6 = this.getX() + Math.round(this.alignX * (float)(this.getWidth() - $$5.width($$4)));
        int var10000 = this.getY();
        int var10001 = this.getHeight();
        Objects.requireNonNull($$5);
        int $$7 = var10000 + (var10001 - 9) / 2;
        p_281367_.drawString($$5, $$4, $$6, $$7, this.getColor(), false);
    }
}

package com.max.prettyguardian.client.gui.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class CustomAbstractScrollWidget extends AbstractWidget implements Renderable, GuiEventListener {
    private static final int BORDER_COLOR_FOCUSED = -1;
    private static final int BORDER_COLOR = -6845336;
    private static final int BACKGROUND_COLOR = 1;
    private static final int INNER_PADDING = 4;
    private double scrollAmount;
    private boolean scrolling;

    public CustomAbstractScrollWidget(int p_240025_, int p_240026_, int p_240027_, int p_240028_, Component p_240029_) {
        super(p_240025_, p_240026_, p_240027_, p_240028_, p_240029_);
    }

    public boolean mouseClicked(double p_240170_, double p_240171_, int p_240172_) {
        if (!this.visible) {
            return false;
        } else {
            boolean $$3 = this.withinContentAreaPoint(p_240170_, p_240171_);
            boolean $$4 = this.scrollbarVisible() && p_240170_ >= (double)(this.getX() + this.width) && p_240170_ <= (double)(this.getX() + this.width + 8) && p_240171_ >= (double)this.getY() && p_240171_ < (double)(this.getY() + this.height);
            if ($$4 && p_240172_ == 0) {
                this.scrolling = true;
                return true;
            } else {
                return $$3 || $$4;
            }
        }
    }

    public boolean mouseReleased(double p_239063_, double p_239064_, int p_239065_) {
        if (p_239065_ == 0) {
            this.scrolling = false;
        }

        return super.mouseReleased(p_239063_, p_239064_, p_239065_);
    }

    public boolean mouseDragged(double p_239639_, double p_239640_, int p_239641_, double p_239642_, double p_239643_) {
        if (this.visible && this.isFocused() && this.scrolling) {
            if (p_239640_ < (double)this.getY()) {
                this.setScrollAmount(0.0);
            } else if (p_239640_ > (double)(this.getY() + this.height)) {
                this.setScrollAmount((double)this.getMaxScrollAmount());
            } else {
                int $$5 = this.getScrollBarHeight();
                double $$6 = (double)Math.max(1, this.getMaxScrollAmount() / (this.height - $$5));
                this.setScrollAmount(this.scrollAmount + p_239643_ * $$6);
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean mouseScrolled(double p_239308_, double p_239309_, double p_239310_) {
        if (!this.visible) {
            return false;
        } else {
            this.setScrollAmount(this.scrollAmount - p_239310_ * this.scrollRate());
            return true;
        }
    }

    public boolean keyPressed(int p_276060_, int p_276046_, int p_276030_) {
        boolean $$3 = p_276060_ == 265;
        boolean $$4 = p_276060_ == 264;
        if ($$3 || $$4) {
            double $$5 = this.scrollAmount;
            this.setScrollAmount(this.scrollAmount + (double)($$3 ? -1 : 1) * this.scrollRate());
            if ($$5 != this.scrollAmount) {
                return true;
            }
        }

        return super.keyPressed(p_276060_, p_276046_, p_276030_);
    }

    public void renderWidget(GuiGraphics p_282213_, int p_282468_, int p_282209_, float p_283300_) {
        if (this.visible) {
            this.renderBackground(p_282213_);
            p_282213_.enableScissor(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1);
            p_282213_.pose().pushPose();
            p_282213_.pose().translate(0.0, -this.scrollAmount, 0.0);
            this.renderContents(p_282213_, p_282468_, p_282209_, p_283300_);
            p_282213_.pose().popPose();
            p_282213_.disableScissor();
            this.renderDecorations(p_282213_);
        }
    }

    private int getScrollBarHeight() {
        return Mth.clamp((int)((float)(this.height * this.height) / (float)this.getContentHeight()), 32, this.height);
    }

    protected void renderDecorations(GuiGraphics p_283178_) {
        if (this.scrollbarVisible()) {
            this.renderScrollBar(p_283178_);
        }

    }

    protected int innerPadding() {
        return 4;
    }

    protected int totalInnerPadding() {
        return this.innerPadding() * 2;
    }

    protected double scrollAmount() {
        return this.scrollAmount;
    }

    protected void setScrollAmount(double p_240207_) {
        this.scrollAmount = Mth.clamp(p_240207_, 0.0, (double)this.getMaxScrollAmount());
    }

    protected int getMaxScrollAmount() {
        return Math.max(0, this.getContentHeight() - (this.height - 4));
    }

    private int getContentHeight() {
        return this.getInnerHeight() + 4;
    }

    protected void renderBackground(GuiGraphics p_282207_) {
        this.renderBorder(p_282207_, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    protected void renderBorder(GuiGraphics p_289776_, int p_289792_, int p_289795_, int p_289775_, int p_289762_) {
//        int $$5 = this.isFocused() ? BORDER_COLOR_FOCUSED : BORDER_COLOR;
//        p_289776_.fill(p_289792_, p_289795_, p_289792_ + p_289775_, p_289795_ + p_289762_, $$5);
//        p_289776_.fill(p_289792_ + 1, p_289795_ + 1, p_289792_ + p_289775_ - 1, p_289795_ + p_289762_ - 1, BACKGROUND_COLOR);
    }

    private void renderScrollBar(GuiGraphics p_282305_) {
        int $$1 = this.getScrollBarHeight();
        int $$2 = this.getX() + this.width;
        int $$3 = this.getX() + this.width + 8;
        int $$4 = Math.max(this.getY(), (int)this.scrollAmount * (this.height - $$1) / this.getMaxScrollAmount() + this.getY());
        int $$5 = $$4 + $$1;
        p_282305_.fill($$2, $$4, $$3, $$5, -8355712);
        p_282305_.fill($$2, $$4, $$3 - 1, $$5 - 1, -4144960);
    }

    protected boolean withinContentAreaTopBottom(int p_239943_, int p_239944_) {
        return (double)p_239944_ - this.scrollAmount >= (double)this.getY() && (double)p_239943_ - this.scrollAmount <= (double)(this.getY() + this.height);
    }

    protected boolean withinContentAreaPoint(double p_239607_, double p_239608_) {
        return p_239607_ >= (double)this.getX() && p_239607_ < (double)(this.getX() + this.width) && p_239608_ >= (double)this.getY() && p_239608_ < (double)(this.getY() + this.height);
    }

    protected boolean scrollbarVisible() {
        return this.getInnerHeight() > this.getHeight();
    }

    protected abstract int getInnerHeight();

    protected abstract double scrollRate();

    protected abstract void renderContents(GuiGraphics var1, int var2, int var3, float var4);
}

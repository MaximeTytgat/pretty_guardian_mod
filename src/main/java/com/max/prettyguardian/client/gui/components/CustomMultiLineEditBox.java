package com.max.prettyguardian.client.gui.components;

import net.minecraft.SharedConstants;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class CustomMultiLineEditBox extends CustomAbstractScrollWidget {
    private static final int CURSOR_INSERT_WIDTH = 1;
    private static final int CURSOR_INSERT_COLOR = 16777215;
    private static final String CURSOR_APPEND_CHARACTER = "_";
    private static final int TEXT_COLOR = 3539535;
    private static final int PLACEHOLDER_TEXT_COLOR = 3539535;
    private final Font font;
    private final Component placeholder;
    private final CustomMultilineTextField textField;
    private int frame;

    public CustomMultiLineEditBox(Font p_239008_, int p_239009_, int p_239010_, int p_239011_, int p_239012_, Component p_239013_, Component p_239014_) {
        super(p_239009_, p_239010_, p_239011_, p_239012_, p_239014_);
        this.font = p_239008_;
        this.placeholder = p_239013_;
        this.textField = new CustomMultilineTextField(p_239008_, p_239011_ - this.totalInnerPadding());
        this.textField.setCursorListener(this::scrollToCursor);
    }

    public void setCharacterLimit(int p_239314_) {
        this.textField.setCharacterLimit(p_239314_);
    }

    public void setValueListener(Consumer<String> p_239274_) {
        this.textField.setValueListener(p_239274_);
    }

    public void setValue(String p_240160_) {
        this.textField.setValue(p_240160_);
    }

    public String getValue() {
        return this.textField.value();
    }

    public void tick() {
        ++this.frame;
    }

    public void updateWidgetNarration(NarrationElementOutput p_259393_) {
        p_259393_.add(NarratedElementType.TITLE, Component.translatable("gui.narrate.editBox", new Object[]{this.getMessage(), this.getValue()}));
    }

    public boolean mouseClicked(double p_239101_, double p_239102_, int p_239103_) {
        if (super.mouseClicked(p_239101_, p_239102_, p_239103_)) {
            return true;
        } else if (this.withinContentAreaPoint(p_239101_, p_239102_) && p_239103_ == 0) {
            this.textField.setSelecting(Screen.hasShiftDown());
            this.seekCursorScreen(p_239101_, p_239102_);
            return true;
        } else {
            return false;
        }
    }

    public boolean mouseDragged(double p_238978_, double p_238979_, int p_238980_, double p_238981_, double p_238982_) {
        if (super.mouseDragged(p_238978_, p_238979_, p_238980_, p_238981_, p_238982_)) {
            return true;
        } else if (this.withinContentAreaPoint(p_238978_, p_238979_) && p_238980_ == 0) {
            this.textField.setSelecting(true);
            this.seekCursorScreen(p_238978_, p_238979_);
            this.textField.setSelecting(Screen.hasShiftDown());
            return true;
        } else {
            return false;
        }
    }

    public boolean keyPressed(int p_239433_, int p_239434_, int p_239435_) {
        return this.textField.keyPressed(p_239433_);
    }

    public boolean charTyped(char p_239387_, int p_239388_) {
        if (this.visible && this.isFocused() && SharedConstants.isAllowedChatCharacter(p_239387_)) {
            this.textField.insertText(Character.toString(p_239387_));
            return true;
        } else {
            return false;
        }
    }

    protected void renderContents(GuiGraphics p_283676_, int p_281538_, int p_283033_, float p_281767_) {
        String $$4 = this.textField.value();
        if ($$4.isEmpty() && !this.isFocused()) {
            p_283676_.drawWordWrap(this.font, this.placeholder, this.getX() + this.innerPadding(), this.getY() + this.innerPadding(), this.width - this.totalInnerPadding(), PLACEHOLDER_TEXT_COLOR);
        } else {
            int $$5 = this.textField.cursor();
            boolean $$6 = this.isFocused() && this.frame / 6 % 2 == 0;
            boolean $$7 = $$5 < $$4.length();
            int $$8 = 0;
            int $$9 = 0;
            int $$10 = this.getY() + this.innerPadding();

            int var10002;
            int var10004;
            for(Iterator var12 = this.textField.iterateLines().iterator(); var12.hasNext(); $$10 += 9) {
                CustomMultilineTextField.StringView $$11 = (CustomMultilineTextField.StringView)var12.next();
                Objects.requireNonNull(this.font);
                boolean $$12 = this.withinContentAreaTopBottom($$10, $$10 + 9);
                if ($$6 && $$7 && $$5 >= $$11.beginIndex() && $$5 <= $$11.endIndex()) {
                    if ($$12) {
                        $$8 = p_283676_.drawString(this.font, $$4.substring($$11.beginIndex(), $$5), this.getX() + this.innerPadding(), $$10, TEXT_COLOR) - 1;
                        var10002 = $$10 - 1;
                        int var10003 = $$8 + 1;
                        var10004 = $$10 + 1;
                        Objects.requireNonNull(this.font);
                        p_283676_.fill($$8, var10002, var10003, var10004 + 9, CURSOR_INSERT_COLOR);
                        p_283676_.drawString(this.font, $$4.substring($$5, $$11.endIndex()), $$8, $$10, TEXT_COLOR);
                    }
                } else {
                    if ($$12) {
                        $$8 = p_283676_.drawString(this.font, $$4.substring($$11.beginIndex(), $$11.endIndex()), this.getX() + this.innerPadding(), $$10, TEXT_COLOR) - 1;
                    }

                    $$9 = $$10;
                }

                Objects.requireNonNull(this.font);
            }

            if ($$6 && !$$7) {
                Objects.requireNonNull(this.font);
                if (this.withinContentAreaTopBottom($$9, $$9 + 9)) {
                    p_283676_.drawString(this.font, CURSOR_APPEND_CHARACTER, $$8, $$9, CURSOR_INSERT_COLOR);
                }
            }

            if (this.textField.hasSelection()) {
                CustomMultilineTextField.StringView $$13 = this.textField.getSelected();
                int $$14 = this.getX() + this.innerPadding();
                $$10 = this.getY() + this.innerPadding();
                Iterator var20 = this.textField.iterateLines().iterator();

                while(var20.hasNext()) {
                    CustomMultilineTextField.StringView $$15 = (CustomMultilineTextField.StringView)var20.next();
                    if ($$13.beginIndex() > $$15.endIndex()) {
                        Objects.requireNonNull(this.font);
                        $$10 += 9;
                    } else {
                        if ($$15.beginIndex() > $$13.endIndex()) {
                            break;
                        }

                        Objects.requireNonNull(this.font);
                        if (this.withinContentAreaTopBottom($$10, $$10 + 9)) {
                            int $$16 = this.font.width($$4.substring($$15.beginIndex(), Math.max($$13.beginIndex(), $$15.beginIndex())));
                            int $$18;
                            if ($$13.endIndex() > $$15.endIndex()) {
                                $$18 = this.width - this.innerPadding();
                            } else {
                                $$18 = this.font.width($$4.substring($$15.beginIndex(), $$13.endIndex()));
                            }

                            var10002 = $$14 + $$16;
                            var10004 = $$14 + $$18;
                            Objects.requireNonNull(this.font);
                            this.renderHighlight(p_283676_, var10002, $$10, var10004, $$10 + 9);
                        }

                        Objects.requireNonNull(this.font);
                        $$10 += 9;
                    }
                }
            }

        }
    }

    protected void renderDecorations(GuiGraphics p_282551_) {
        super.renderDecorations(p_282551_);
        if (this.textField.hasCharacterLimit()) {
            int $$1 = this.textField.characterLimit();
            Component $$2 = Component.translatable("gui.multiLineEditBox.character_limit", new Object[]{this.textField.value().length(), $$1});
            p_282551_.drawString(this.font, $$2, this.getX() + this.width - this.font.width($$2), this.getY() + this.height + 4, 10526880);
        }

    }

    public int getInnerHeight() {
        Objects.requireNonNull(this.font);
        return 9 * this.textField.getLineCount();
    }

    protected boolean scrollbarVisible() {
        return (double)this.textField.getLineCount() > this.getDisplayableLineCount();
    }

    protected double scrollRate() {
        Objects.requireNonNull(this.font);
        return 9.0 / 2.0;
    }

    private void renderHighlight(GuiGraphics p_282092_, int p_282814_, int p_282908_, int p_281451_, int p_281765_) {
        p_282092_.fill(RenderType.guiTextHighlight(), p_282814_, p_282908_, p_281451_, p_281765_, -16776961);
    }

    private void scrollToCursor() {
        double $$0 = this.scrollAmount();
        CustomMultilineTextField var10000 = this.textField;
        Objects.requireNonNull(this.font);
        CustomMultilineTextField.StringView $$1 = var10000.getLineView((int)($$0 / 9.0));
        int var5;
        if (this.textField.cursor() <= $$1.beginIndex()) {
            var5 = this.textField.getLineAtCursor();
            Objects.requireNonNull(this.font);
            $$0 = (double)(var5 * 9);
        } else {
            var10000 = this.textField;
            double var10001 = $$0 + (double)this.height;
            Objects.requireNonNull(this.font);
            CustomMultilineTextField.StringView $$2 = var10000.getLineView((int)(var10001 / 9.0) - 1);
            if (this.textField.cursor() > $$2.endIndex()) {
                var5 = this.textField.getLineAtCursor();
                Objects.requireNonNull(this.font);
                var5 = var5 * 9 - this.height;
                Objects.requireNonNull(this.font);
                $$0 = (double)(var5 + 9 + this.totalInnerPadding());
            }
        }

        this.setScrollAmount($$0);
    }

    private double getDisplayableLineCount() {
        double var10000 = (double)(this.height - this.totalInnerPadding());
        Objects.requireNonNull(this.font);
        return var10000 / 9.0;
    }

    private void seekCursorScreen(double p_239276_, double p_239277_) {
        double $$2 = p_239276_ - (double)this.getX() - (double)this.innerPadding();
        double $$3 = p_239277_ - (double)this.getY() - (double)this.innerPadding() + this.scrollAmount();
        this.textField.seekCursorToPoint($$2, $$3);
    }
}
